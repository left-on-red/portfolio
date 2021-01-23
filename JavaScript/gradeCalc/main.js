$(function() {

    let ended = false;

    let submit = $('#submit');
    let buttons = $('#buttons');

    let inputSlide = $('#input');
    let resultSlide = $('#result');

    let first = $('#first');
    let last = $('#last');
    let earned = $('#earned');
    let possible = $('#possible');

    let name = $('#name-out');
    let percentage = $('#percentage');
    let grade = $('#grade');

    submit.click(function() { if (!submit.prop('disabled')) { ended = true; render(); } })

    // make-shift form validation for delivery information
    first.keyup(check);
    first.change(check);

    last.keyup(check);
    last.change(check);

    earned.keyup(check);
    earned.change(check);

    possible.keyup(check);
    possible.change(check);

    function check() { if (first.val() == '' || last.val() == '' || earned.val() == '' || possible.val() == '') { submit.prop('disabled', true) } else { submit.prop('disabled', false) } }

    function render() {

        inputSlide.hide();
        resultSlide.hide();

        check();

        if (ended) {
            resultSlide.show();
            buttons.hide();

            let obj = {
                first: first.val(),
                last: last.val(),
                earned: parseFloat(earned.val()),
                possible: parseFloat(possible.val())
            }

            name.text(`grade for ${obj.first} ${obj.last}`);
            let calc = (obj.earned / obj.possible) * 100;
            percentage.text(`${calc.toFixed(2)}%`);

            let letter = '';

            // pulled directly from the syllabus
            if (calc >= 93) { letter = 'A' }
            else if (calc >= 90) { letter = 'A-' }
            else if (calc >= 87) { letter = 'B+' }
            else if (calc >= 83) { letter = 'B' }
            else if (calc >= 80) { letter = 'B-' }
            else if (calc >= 77) { letter = 'C+' }
            else if (calc >= 73) { letter = 'C' }
            else if (calc >= 70) { letter = 'C-' }
            else if (calc >= 67) { letter = 'D+' }
            else if (calc >= 63) { letter = 'D' }
            else if (calc >= 60) { letter = 'D-' }
            else { letter = 'F' }

            grade.text(letter);
        }

        else {
            inputSlide.show();
            buttons.show();
        }
    }

    render();
});