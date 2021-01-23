let firstname = $('#firstNameInput');
let lastname = $('#lastNameInput');
let rank = $('#rankInput');

let result = $('#result');

let colors = [
    '#000000',
    '#FF0000',
    '#FFFF00',
    '#00FF00',
    '#00FFFF',
    '#0000FF',
    '#FF00FF'
];

$('#submitBtn').click(function() {
    result.text(`Hello, ${rank.val()}. ${firstname.val()} ${lastname.val()}`);
    result.css('color', colors[rank[0].selectedIndex]);
});