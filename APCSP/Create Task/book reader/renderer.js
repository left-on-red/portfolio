// renderer.js
var renderer = {
    mode: 'light',
    factor: 1,
    refreshValue: 200,
    currentPage: 0,
    element: document.getElementsByTagName('div')[0],
    object: new Object(),
    text: {
        // a function that uses regex to determine whether or not a provided url is valid
        validateURL: function(string) {
            pattern =  /^(?:(?:https?|ftp):\/\/)?(?:(?!(?:10|127)(?:\.\d{1,3}){3})(?!(?:169\.254|192\.168)(?:\.\d{1,3}){2})(?!172\.(?:1[6-9]|2\d|3[0-1])(?:\.\d{1,3}){2})(?:[1-9]\d?|1\d\d|2[01]\d|22[0-3])(?:\.(?:1?\d{1,2}|2[0-4]\d|25[0-5])){2}(?:\.(?:[1-9]\d?|1\d\d|2[0-4]\d|25[0-4]))|(?:(?:[a-z\u00a1-\uffff0-9]-*)*[a-z\u00a1-\uffff0-9]+)(?:\.(?:[a-z\u00a1-\uffff0-9]-*)*[a-z\u00a1-\uffff0-9]+)*(?:\.(?:[a-z\u00a1-\uffff]{2,})))(?::\d{2,5})?(?:\/\S*)?$/;
            if (pattern.test(string)) {
                return true;
            }

            else {
                return false;
            }
        }
    },

    // contains all the methods that's needed to manipulate colors
    color: {
        // inverts the given hex color
        invert: function(hex) {
            function padZero(string, length) {
                length = length || 2;
                var zeros = new Array(length).join('0');
                return (zeros + string).slice(-length);
            }

            if (hex.indexOf('#') === 0) {
                hex = hex.slice(1);
            }

            if (hex.length === 3) {
                hex = hex[0] + hex[0] + hex[1] + hex[1] + hex[2] + hex[2];
            }

            if (hex.length !== 6) {
                console.log(hex);
                console.error('invalid hex value');
            }

            var rgb = [
                (255 - parseInt(hex.slice(0, 2), 16)).toString(16),
                (255 - parseInt(hex.slice(2, 4), 16)).toString(16),
                (255 - parseInt(hex.slice(4, 6), 16)).toString(16)
            ]

            return '#' + padZero(rgb[0]) + padZero(rgb[1]) + padZero(rgb[2]);
        },

        // converts a standard rgb css color attribute into a usable hexadecimal number
        toHex: function(rgb) {
            var hexDigits = new Array('0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'); 
            function hex(x) {
                return isNaN(x) ? "00" : hexDigits[(x - x % 16) / 16] + hexDigits[x % 16];
            }
            rgb = rgb.match(/^rgb\((\d+),\s*(\d+),\s*(\d+)\)$/);
            return "#" + hex(rgb[1]) + hex(rgb[2]) + hex(rgb[3]);
        }
    },

    // contains all the methods required for loading urls (most are asynchronous)
    image: {
        // an asynchronous function that retrieves the base64 value of an image using the provided image uel
        url: function(input, callback) {
            var img = new Image();
            img.crossOrigin = 'Anonymous';
            img.onload = function() {
                var canvas = document.createElement('canvas');
                var context = canvas.getContext('2d');
                var dataURL;
                canvas.width = this.width;
                canvas.height = this.height;
                context.drawImage(this, 0, 0);
                dataURL = canvas.toDataURL();
                callback(dataURL);
                canvas = null;
            };

            img.src = input;
        },

        file: function(input) {
            
        }
    },

    // this toggles between "light" and "night" mode which just changes the styling of certain elements
    toggleMode: function() {
        var navChildren = document.getElementById('nav').children;
        if (renderer.mode == 'light') {
            renderer.mode = 'night';
            document.getElementsByTagName('body')[0].style.backgroundColor = '#111111';
            document.getElementById('nav').style.backgroundColor = '#222222';
            renderer.element.style.backgroundColor = "#333333";
            
            for (var c = 0; c < navChildren.length; c++) {
                navChildren[c].style.color = '#DDDDDD';
            }
        }

        else if (renderer.mode == 'night') {
            renderer.mode = 'light';
            document.getElementsByTagName('body')[0].style.backgroundColor = '#DDDDDD';
            document.getElementById('nav').style.backgroundColor = '#EEEEEE';
            renderer.element.style.backgroundColor = '#FFFFFF';
            
            for (var c = 0; c < navChildren.length; c++) {
                navChildren[c].style.color = '#222222';
            }
        }

        //renderer.element.style.backgroundColor = renderer.color.invert(renderer.color.toHex(renderer.element.style.backgroundColor));
        for (var i = 0; i < renderer.element.children.length; i++) {
            if (renderer.element.children[i].localName == 'p') {
                renderer.element.children[i].style.color = renderer.color.invert(renderer.color.toHex(renderer.element.children[i].style.color));
            }
        }
    },

    refreshNav: function() {
        if (renderer.refreshValue < 200) {
            renderer.refreshValue = 200;
        }

        else {
            renderer.refreshValue = 0;
        }
    },

    nextPage: function() {
        if (renderer.object.pages[renderer.currentPage + 1] != undefined) {
            renderer.currentPage++;

            while (renderer.element.lastChild.id != 'nav') {
                renderer.element.removeChild(renderer.element.lastChild);
            }

            renderer.parse(renderer.object);
        }
    },

    previousPage: function() {
        if (renderer.object.pages[renderer.currentPage - 1] != undefined) {
            renderer.currentPage--;
        }

        while (renderer.element.lastChild.id != 'nav') {
            renderer.element.removeChild(renderer.element.lastChild);
        }

        renderer.parse(renderer.object);
    },

    // this method is triggered every time the window is resized; it basically proportionally resizes
    // the page to match the window dimensions to get rid of the scroll bar that would normally be visable
    resize: function() {
        var windowSize = [
            window.innerWidth,
            window.innerHeight
        ]

        pageSize = renderer.object.meta.size;

        if (windowSize[0] < pageSize[0] || windowSize[1] < pageSize[1]) {
            if (windowSize[0] < pageSize[0] && windowSize[1] < pageSize[1]) {
                var biggest;
                var page;

                if (windowSize[0] > windowSize[1]) {
                    biggest = windowSize[0];
                    page = pageSize[0];
                }

                else if (windowSize[0] < windowSize[1]) {
                    biggest = windowSize[1];
                    page = pageSize[1];
                }

                renderer.factor = page/biggest;
            }

            else if (windowSize[0] < pageSize[0] && !(windowSize[1] < pageSize[1])) {
                renderer.factor = pageSize[0]/windowSize[0];
            }

            else if (windowSize[1] < pageSize[1] && !(windowSize[0] < pageSize[0])) {
                renderer.factor = pageSize[1]/windowSize[1];
            }
        }

        renderer.element.style.width = renderer.object.meta.size[0]/renderer.factor + 'px';
        renderer.element.style.height = renderer.object.meta.size[1]/renderer.factor + 'px';

        for (var c = 0; c < renderer.element.children.length; c++) {
            var pageItem = renderer.object.pages[renderer.currentPage][c];
            if (renderer.element.children[c].getAttribute('id') != 'nav' && pageItem != undefined) {
                if (pageItem.type == 'text') {
                    renderer.element.children[c].style.fontSize = pageItem.font.size/renderer.factor + 'px';

                    if (pageItem.size != undefined) {
                        var width = '';
                        var height = '';

                        if (pageItem.size[0] != undefined) {
                            width = pageItem.size[0]/renderer.factor + 'px';
                        }

                        if (pageItem.size[1] != undefined) {
                            height = pageItem.size[1]/renderer.factor + 'px';
                        }

                        renderer.element.children[c].style.width = width;
                        renderer.element.children[c].style.height = height;
                    }
                }

                if (pageItem.type == 'image') {
                    if (pageItem.size.mode == 'proportional') {
                        renderer.element.children[c].style.width = (pageItem.size.dimensions[0] * pageItem.size.scale)/renderer.factor + 'px';
                        renderer.element.children[c].style.height = (pageItem.size.dimensions[1] * pageItem.size.scale)/renderer.factor + 'px';
                    }
                }

                renderer.element.children[c].style.left = (pageItem.position[0]/renderer.factor) + 'px';
                renderer.element.children[c].style.top = (pageItem.position[1]/renderer.factor) + 'px';
            }
        }
    },

    // this parses the entire "bookObject" which is the json data that builds the displayed book
    parse: function(bookObj) {
        renderer.object = bookObj;
        renderer.resize();
        var size = renderer.object.meta.size;

        renderer.element.style.width = size[0]/renderer.factor + 'px';
        renderer.element.style.height = size[1]/renderer.factor + 'px';
        var currentPage = renderer.object.pages[renderer.currentPage];
        for (var i = 0; i < currentPage.length; i++) {
            var element;

            var index = i + 1;

            if (currentPage[index] != undefined) {
                if (currentPage[index].type == 'text') {
                    element = document.createElement('p');
                    element.innerHTML = currentPage[index].value;

                    element.style.fontFamily = currentPage[index].font.family;
                    element.style.fontSize = currentPage[index].font.size/renderer.factor + 'px';

                    if (currentPage[index].size != undefined) {
                        element.style.display = 'block';
                        var width = '';
                        var height = '';
                        if (currentPage[index].size[0] != undefined) {
                            width = currentPage[index].size[0]/renderer.factor + 'px';
                        }

                        if (currentPage[index].size[1] != undefined) {
                            height = currentPage[index].size[1]/renderer.factor + 'px';
                        }

                        if (currentPage[index].size.length == 2) {
                            element.style.overflow = 'hidden';
                        }

                        element.style.width = width;
                        element.style.height = height;
                    }

                    if (currentPage[index].color != undefined) {
                        element.style.color = currentPage[index].color;
                    }

                    if (currentPage[index].tags != undefined) {
                        for (t = 0; t < currentPage[index].tags.length; t++) {
                            if (currentPage[index].tags[t] == 'centered') {
                                element.style.width = '100%';
                                element.style.textAlign = 'center';
                            }
                        }
                    }
                }

                else if (currentPage[index].type == 'image') {
                    element = document.createElement('img');
                    if (currentPage[index].size.mode == 'proportional') {
                        if (renderer.text.validateURL(currentPage[index].source)) {
                            element.setAttribute('src', currentPage[index].source);

                            var tempImage = new Image();
                            tempImage.addEventListener('load', function() {
                                currentPage[index-1].size.dimensions = [
                                    this.naturalWidth,
                                    this.naturalHeight
                                ]

                                element.style.width = (this.naturalWidth * currentPage[index-1].size.scale)/renderer.factor + 'px';
                                element.style.height = (this.naturalHeight * currentPage[index-1].size.scale)/renderer.factor + 'px';
                            });

                            tempImage.src = currentPage[index].source;
                        }
                    }
                }

                if (currentPage[index].transformations != undefined && currentPage[index].transformations.length != 0) {
                    for (var t = 0; t < currentPage[index].transformations.length; t++) {
                        if (currentPage[index].transformations[t].type == 'rotate') {
                            element.style.transform += 'rotate(' + currentPage[index].transformations[t].value + 'deg)';
                        }
                    }
                }

                element.style.position = 'absolute';
                element.style.left = (currentPage[index].position[0]/renderer.factor) + 'px';
                element.style.top = (currentPage[index].position[1]/renderer.factor) + 'px';
                element.style.transitionDuration = '1s';

                renderer.element.appendChild(element);
            }
        }

        if (renderer.mode == 'night') {
            for (var i = 0; i < renderer.element.children.length; i++) {
                if (renderer.element.children[i].localName == 'p') {
                    renderer.element.children[i].style.color = renderer.color.invert(renderer.color.toHex(renderer.element.children[i].style.color));
                }
            }
        }
    }
}

window.setInterval(function() {
    var children = document.getElementById('nav').children;

    for (c = 0; c < children.length; c++) {
        children[c].style.fontSize = document.getElementById('nav').offsetHeight/1.2 + 'px';
    }
    if (renderer.refreshValue < 200) {
        renderer.refreshValue++;
        document.getElementById('nav').style.top = '0';
    }

    else {
        document.getElementById('nav').style.top = '-' + document.getElementById('nav').offsetHeight + 'px';
    }
}, 20);