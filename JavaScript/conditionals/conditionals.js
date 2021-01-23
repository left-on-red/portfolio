$(document).ready(function () {
    // add the functions as event listeners
    // to the forms in the HTML

    $('#clickCounter').submit(countClick);

	function countClick(event) {
        event.preventDefault();
		// Increment a variable that tracks the
        // number of button clicks
        let out = $('#clickCountOutput');
        out.text(Number(out.text()) + 1);

		// Print the current number of clicks to the
		// <p> with ID "clickCountOutput"

        console.log(Number(out.text()));

		// When the count gets to 10, reset it to 0

        if (Number(out.text()) >= 10) { out.text(0) }
	}

    $('#ageValidator').submit(checkAge);

    function checkAge(event) {
        event.preventDefault();

        // Get the user's birth year from the text
        // box with ID of "birthYear"

        let year = Number($('#birthYear').val());

        // If they are currently under 18, print "Child"
        // to the <p> with ID of "birthYearOutput"

        if ((new Date()).getFullYear() - year < 18) { $('#birthYearOutput').text('Child') }

        // If they are 18 or over, print "Adult" instead
        
        else { $('#birthYearOutput').text('Adult') }

    }

    $('#salesTax').submit(calcSalesTax);

    function calcSalesTax(event) {
        event.preventDefault();

        // Get the purchase amount from the text
        // box with ID of "purchaseAmount"

        let amount = Number($('#purchaseAmount').val());

        // Get the state from the text box with ID "state"

        let tax = 0;
        switch($('#state').val()) {

        // Tax rates are: WI 5%, IL 8%, MN 7.5%, MI 5.5%

            case 'WI': tax = 0.05; break;
            case 'IL': tax = 0.08; break;
            case 'MN': tax = 0.075; break;
            case 'MI': tax = 0.055; break;

        }

        // Calculate the sales tax amount and print to
        // the <p> with ID of "salesTaxOutput"

        if (tax > 0) { $('#salesTaxOutput').text(`$${(tax*amount).toFixed(2)}`) }

        // If the user enters a state not listed above,
        // print "Error" instead

        else { $('#salesTaxOutput').text('Error') }

    }

    $('#catFood').submit(recommendFood);

    function recommendFood(event) {
        event.preventDefault();

        // Get the cat's age from the text box with
        // ID of "catAge"

        let age = Number($('#catAge').val());

        // Cats under 2 get "kitten chow", between 2 and 10
        // get "adult chow", and over 10 get "senior chow"
        
        let food;

        if (age < 2) { food = 'kitten chow' }
        else if (age >= 2 && age <= 10) { food = 'adult chow' }
        else if (age > 10) { food = 'senior chow' }

        // Print the food recommendation to the <p> with
        // ID of "catAgeOutput"
        
        $('#catAgeOutput').text(food);

    }

    $('#randomCard').submit(drawCard);

    function drawCard(event) {
        event.preventDefault();

        // Generate a random card face value (1 - 13)
        var faceValue = Math.floor(Math.random() * 13) + 1;

        // Generate a random suit (1 - 4)
        var suit = Math.floor(Math.random() * 4) + 1;

        // Create the description of the card, for example
        // "King of Spades" or "2 of Hearts"
        var description;

        // For face values 2 - 10, you can just print the number
        // Face value 1 is "Ace", 11 is "Jack", 12 is "Queen",
        // and 13 is "King"

        switch(faceValue) {
            case 1: description = 'Ace'; break;
            case 11: description = 'Jack'; break;
            case 12: description = 'Queen'; break;
            case 13: description = 'King'; break;
            default: description = `${faceValue}`; break;
        }

        // For the suits, 1 is "Clubs", 2 is "Spades",
        // 3 is "Hearts", 4 is "Diamonds"

        switch(suit) {
            case 1: description = `${description} of Clubs`; break;
            case 2: description = `${description} of Spades`; break;
            case 3: description = `${description} of Hearts`; break;
            case 4: description = `${description} of Diamonds`; break;
        }

        // Print the card's description to the <p> with
        // ID of "drawCardOutput"

        $('#drawCardOutput').text(description);

    }

});