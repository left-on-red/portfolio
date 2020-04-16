var madlibs = `
    When I go to the arcade with my [pn] there are lots of games to play. 
    I spend lots of time there with my friends. 
    In "Xmen" you can be different [pn] . 
    The point of the game is to [v] every robot. 
    You also need to save people, and then you can go to the next level. 
    In "Star Wars" you are Luke Skywalker and you try to destroy every [n] . 
    In a car racing / motorcycle racing game you need to beat every computerized vehicle that you are [ving] against. 
    There are a whole lot of other cool games. 
    When you play some games you win [pn] for certain scores. 
    Once you're done you can cash in your tickets to get a big [n] . 
    You can save your [pn] for another time. 
    When I went to this arcade I didn't believe how much fun it would be. 
    You might annoy your parents by asking them over and over if you can go back to there. 
    So far I have had a lot of fun every time I've been to this great arcade!
`;

var element = document.createElement('div');

var color = '#FF0000';

var picker = new CP(document.querySelector('input[type="text"]'));

picker.target.value = color;
document.getElementsByTagName('input')[0].style.backgroundColor = color;

picker.on('drag', function(hex) {
    this.target.value = '#' + hex;
    color = '#' + hex;
    document.getElementsByTagName('input')[0].style.backgroundColor = color;
});

function parse(input) {
    var items = input.split(' ');
    for (var i = 0; i < items.length; i++) {
        var span = document.createElement('span');
        span.style.color = color;
        if (items[i] == '[n]') {
            var noun = prompt('enter a noun:');
            span.innerHTML = noun;
        }

        else if (items[i] == '[pn]') {
            var pronoun = prompt('enter a pronoun:');
            span.innerHTML = pronoun;
        }

        else if (items[i] == '[v]') {
            var verb = prompt('enter a verb:');
            span.innerHTML = verb;
        }

        else if (items[i] == '[ving]') {
            var ingverb = prompt('enter a verb that ends in "ing":');
            span.innerHTML = ingverb;
        }

        else {
            var textNode;

            if (i == 0) {
                textNode = document.createTextNode(items[i] + ' ');
            }

            else if (i == items.length - 1) {
                textNode = document.createTextNode(' ' + items[i]);
            }

            else {
                textNode = document.createTextNode(' ' + items[i] + ' ');
            }

            element.appendChild(textNode);
        }

        if (span.innerHTML != '') {
            element.appendChild(span);
        }
    }

    document.body.appendChild(element);
}