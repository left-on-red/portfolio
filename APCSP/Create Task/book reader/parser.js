// parser.js
var parser = {
    parse: function(file) {
        file = file[0];

        var reader = new FileReader();
        reader.addEventListener('load', function() {
            var bookObject;
            var error = false;

            try {
                bookObject = JSON.parse(reader.result);
            }

            catch(err) {
                error = true;
            }

            if (!error) {
                renderer.parse(bookObject);
            }

            else {
                console.error('file not formatted correctly');
            }
        });

        reader.readAsText(file);
    }
}