$(function() {

    // the index for the current div associated with the current step
    let step = 0;

    let nextBtn = $('#next');
    let previousBtn = $('#previous');
    let buttons = $('#buttons');

    let pizzaSize = $('#pizzaSize');
    let pizzaSizes = [ { name: 'small', price: 7 }, { name: 'medium', price: 9 }, { name: 'large', price: 12 } ];

    let pizzaCrust = $('#pizzaCrust');
    let pizzaCrusts = [ 'thin', 'pan', 'gluten free' ];

    // array of meat elements (checkboxes) with parallel label names
    let meats = [ $('#meat1'), $('#meat2'), $('#meat3') ];
    let meatNames = [ 'pepperoni', 'sausage', 'chicken' ];

    // array of veggie elements (checkboxes) with parallel label names
    let veggies = [ $('#veggie1'), $('#veggie2'), $('#veggie3') ];
    let veggieNames = [ 'onions', 'peppers', 'jalapeños' ];

    // delivery information
    let name = $('#name');
    let nameReflect = $('#nameReflect');

    let address = $('#address');
    let addressReflect = $('#addressReflect');

    let phone = $('#phone');
    let phoneReflect = $('#phoneReflect');

    // elements related to the final order preview
    let sizeSelect = $('#sizeSelect');
    let sizeCost = $('#sizeCost');

    let crustSelect = $('#crustSelect');

    let toppingsSelect = $('#toppingsSelect');
    let toppingsCost = $('#toppingsCost');

    let tax = $('#tax');
    let subtotal = $('#subtotal');
    let grandtotal = $('#grandtotal');

    // steps[step] is the step div that is currently being viewed
    let steps = [ $('#step1'), $('#step2'), $('#step3'), $('#step4') ]

    nextBtn.click(function() { if (!nextBtn.prop('disabled')) { step += 1; render(); }});
    previousBtn.click(function() { if (!previousBtn.prop('disabled') && step > 0) { step -= 1; render(); }});

    // make-shift form validation for delivery information
    name.keyup(checkDelivery);
    address.keyup(checkDelivery);
    phone.keyup(checkDelivery);

    function checkDelivery() { if (name.val() == '' || address.val() == '' || phone.val() == '') { nextBtn.prop('disabled', true) } else { nextBtn.prop('disabled', false) } }

    function render() {

        // un-disables both buttons every render
        nextBtn.prop('disabled', false);
        previousBtn.prop('disabled', false);

        nextBtn.text('next');

        // and then disables the previous button if the user is on the first step
        if (step == 0) { previousBtn.prop('disabled', true) }

        // hides all of the step divs every render
        for (let s = 0; s < steps.length; s++) { steps[s].hide() }

        // delivery information
        if (step == 1) { checkDelivery() }

        // order confirmation
        if (step == 2) {
            let sub = 0;

            nextBtn.text('submit');

            nameReflect.text(name.val());
            addressReflect.text(address.val());
            phoneReflect.text(phone.val());

            let sizeIndex = parseInt(pizzaSize.val());
            sizeSelect.text(pizzaSizes[sizeIndex].name);
            sizeCost.text(`$${pizzaSizes[sizeIndex].price.toFixed(2)}`);
            sub += pizzaSizes[sizeIndex].price;

            crustSelect.text(pizzaCrusts[parseInt(pizzaCrust.val())]);

            // compiles a list of toppings as well as the total for toppings
            let toppingsArr = [];
            let toppingTotal = 0;

            for (let m = 0; m < meats.length; m++) { if (meats[m].prop('checked')) { toppingsArr.push(meatNames[m]); toppingTotal += 1.50; } }
            for (let v = 0; v < veggies.length; v++) { if (veggies[v].prop('checked')) { toppingsArr.push(veggieNames[v]); toppingTotal += 1.00; } }

            toppingsSelect.text(toppingsArr.join(', '));
            toppingsCost.text(`$${toppingTotal.toFixed(2)}`);
            sub += toppingTotal;

            subtotal.text(`$${sub.toFixed(2)}`);

            let taxTotal = sub * 0.051;
            tax.text(`$${taxTotal.toFixed(2)}`);

            let grand = sub + taxTotal + 2.00;
            grandtotal.text(`$${grand.toFixed(2)}`);
            console.log(pizzaSize.val());
        }

        // final submit
        if (step == 3) {
            // hides the navigation buttons while at the final success view
            buttons.hide();

            // adds the "progressing" class to the bar div a little bit after the view is loaded
            // (it doesn't do the css transition if the class is applied immediately)
            window.setTimeout(function() { $('#bar').addClass('progressing') }, 100);

            // and finally reloads the page after 5 seconds
            window.setTimeout(function() { location.reload() }, 5000);
        }

        // and then just shows the div associated with the current step
        steps[step].show();
    }

    render();
});