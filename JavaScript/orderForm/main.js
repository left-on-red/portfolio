let input1 = $('#input1');
let input2 = $('#input2');

let total1 = $('#total1');
let total2 = $('#total2');
let total3 = $('#total3');

function calculateTotal(x, y) {
    let total = 0;
    total += (x*39.99);
    total += (y*14.99);

    total1.text(`$${(x*39.99).toFixed(2)}`);
    total2.text(`$${(y*14.99).toFixed(2)}`);

    total3.text(`$${total.toFixed(2)}`);
}

input1.on('input', function() { calculateTotal(input1.val(), input2.val()) });
input2.on('input', function() { calculateTotal(input1.val(), input2.val()) });

window.onload = function() { calculateTotal(1, 2) }