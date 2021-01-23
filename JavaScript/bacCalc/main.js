let beer = $('#beerInput');
let wine = $('#wineInput');
let shots = $('#shotsInput');
let weight = $('#weightInput');
let hours = $('#hoursInput');

let result = $('#result');

function calculate() {
    let alcohol = 0;

    alcohol += (beer.val() * 0.54);
    alcohol += (wine.val() * 0.6);
    alcohol += (shots.val() * 0.6);

    alcohol *= 7.5;
    alcohol /= weight.val();
    alcohol -= (0.015 * hours.val());
    alcohol = alcohol.toFixed(3);

    result.text(`${alcohol}%`);
}