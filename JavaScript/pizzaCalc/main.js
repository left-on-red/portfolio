let people = $('#peopleInput');
let pizzas = $('#pizzaInput');
let toppings = $('#toppingInput');

let result = $('#result');

$('#calculateBtn').click(function() {
    let total = 0;

    total += (pizzas.val() * 15);
    total += (toppings.val() * 1.25);
    total /= people.val();

    total = total.toFixed(2);

    result.text(`$${total} ($${(total * people.val()).toFixed(2)} total)`);
});