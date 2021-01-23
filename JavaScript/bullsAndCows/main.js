$(function() {
    let list = $('#list');
    let form = $('#form');
    let input = $('#input');
    let submit = $('#submit');
    
    let counter = 0;

    // the values in text fields seem to be cached between page refreshes
    input.val('');

    function generate() {
        let arr = [];

        // probably not the most efficient method,
        // but it made the most sense in my head
        while (arr.length < 4) {
            let num = Math.floor(Math.random() * 9) + 1;
            if (!arr.includes(num)) { arr.push(num) }
        }

        // returns a string rather than a number because all the comparisons
        // will be between 2 strings. not worth needing to convert back and forth
        return arr.join('');
    }

    let secret = generate();

    function validate() {
        let str = input.val();

        if (str.length != 4) { return false }
        if (isNaN(str)) { return false }
        if (str.includes('0')) { return false }

        // once again, probably not the most efficient way,
        // but this made the most sense in my head for checking for duplicates
        let obj = {};
        for (let i = 0; i < str.length; i++) {
            if (obj[str[i]]) { return false }
            else { obj[str[i]] = true }
        }

        // if it made it past all the early returns, the input is valid
        return true;
    }

    function validateButton() {
        let valid = validate();
        if (valid) { submit.prop('disabled', false) }
        else { submit.prop('disabled', true) }
    }

    form.submit(function(event) {
        event.preventDefault();

        if (!validate()) { return }
        let str = input.val();
        input.val('');

        counter++;

        let out = `guess #${counter}: ${str} > `;

        // if the user guessed the 4 digit number
        if (str == secret) { out += 'correct answer!' }

        // turned out to be a lot easier than I thought it was going to be
        else {
            let bulls = 0;
            let cows = 0;
    
            for (let i = 0; i < str.length; i++) {
                if (str[i] == secret[i]) { bulls++ }
                else if (secret.includes(str[i])) { cows++ }
            }
    
            out += `${bulls} bull${bulls != 1 ? 's' : ''} and ${cows} cow${cows != 1 ? 's' : ''}`;
        }

        // normally it's not a good idea to insert an arbitrary string directly
        // into the html, but the text is only being formatted with calculated numbers and strings
        // so it should be fine
        list.append(`<li>${out}</li>`);
        list.scrollTop(list.prop('scrollHeight'));
    });

    input.keyup(validateButton);
    input.submit(validateButton);

    validateButton();
});