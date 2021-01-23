$(document).ready(function () {
    // Object containing the validation rules
    let rules = {
        firstName: 'required',
        grade: {
            required: true,
            min: 5,
            max: 8
        }
    }

    // Object containing the error messages
    let messages = {
        firstName: 'please enter the first name of the child',
        grade: 'please enter a grade 5-8 for the child'
    }

    // Pass the configuration to the form's validate() method
    // Needs submitHandler, rules, and messages properties
    $("form").validate({ rules: rules, messages: messages, submitHandler: run });

    function run(form) {
        // Change the text of the <p> with ID of "message" to
        // state, for example, "You have registered Jane for grade 6 camp!"
        // Use the name and grade the user provided in the form.

        let name = $('#firstName').val();
        let grade = $('#grade').val();

        $('#message').text(`you have registered ${name} for grade ${grade} camp!`);
    }
});