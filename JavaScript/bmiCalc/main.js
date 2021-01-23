let weight = $('#weightInput');
let height = $('#heightInput');

let result = $('#result');

function calculate() {
    let kg = parseInt(weight.val()) * 0.453592;
    let m = parseInt(height.val()) * 0.0254;
    let bmi = (kg / (m * m));

    let weightClass;

    if (bmi > 30) { weightClass = 'obese' }
    else if (bmi >= 25) { weightClass = 'overweight' }
    else if (bmi >= 18.5) { weightClass = 'healthy' }
    else if (bmi < 18.5) { weightClass = 'lightweight' }

    result.text(`${bmi.toFixed(1)} (${weightClass})`);
}