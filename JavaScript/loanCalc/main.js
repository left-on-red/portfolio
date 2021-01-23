$(function() {

    let ended = false;

    let submit = $('#submit');
    let buttons = $('#buttons');

    let inputSlide = $('#input');
    let resultSlide = $('#result');

    let resultText = $('#resultText');

    let salary = $('#salary');
    let credit = $('#credit');
    let months = $('#months');

    submit.click(function() { if (!submit.prop('disabled')) { ended = true; render(); } })

    // make-shift form validation for delivery information
    salary.keyup(check);
    salary.change(check);

    credit.keyup(check);
    credit.change(check);

    months.keyup(check);
    months.change(check);

    function check() { if (salary.val() == '' || credit.val() == '' || months.val() == '') { submit.prop('disabled', true) } else { submit.prop('disabled', false) } }

    function render() {

        inputSlide.hide();
        resultSlide.hide();

        check();

        if (ended) {

            let approved = false;

            let s = parseInt(salary.val());
            let c = parseInt(credit.val());
            let m = parseInt(months.val());

            if (s >= 40000) {
                if (c >= 600) { approved = true }
                else if (m > 12) { approved = true }
            }

            else if (c >= 600 && m > 12) { approved = true }

            approved ? resultText.text('loan approved!') : resultText.text('loan denied!');

            resultSlide.show();
            buttons.hide();

            // adds the "progressing" class to the bar div a little bit after the view is loaded
            // (it doesn't do the css transition if the class is applied immediately)
            window.setTimeout(function() { $('#bar').addClass('progressing') }, 100);

            // and finally reloads the page after 5 seconds
            window.setTimeout(function() { location.reload() }, 5000);
        }

        else {
            inputSlide.show();
            buttons.show();
        }
    }

    render();
});