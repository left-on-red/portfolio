let books = $('#bookInput');
let dvds = $('#dvdInput');
let overdue = $('#overdueInput');

let result = $('#result');

$('#calculateBtn').click(function() {
    let total = 0;

    total += (books.val() * 0.25);
    total += (dvds.val() * 0.50);
    total *= overdue.val();

    total = total.toFixed(2);

    result.text(`$${total}`);
});