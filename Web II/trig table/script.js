var table = document.getElementsByTagName('table')[0];

for (var r = 0; r < 25; r++) {
    var degrees = 15 * r;
    var row = document.createElement('tr');
    var sin = Math.sin(degrees);
    var cos = Math.cos(degrees);
    
    var degreesCell = document.createElement('td');
    degreesCell.innerHTML = degrees;

    var sinCell = document.createElement('td');
    sinCell.innerHTML = sin;

    var cosCell = document.createElement('td');
    cosCell.innerHTML = cos;

    row.appendChild(degreesCell);
    row.appendChild(sinCell);
    row.appendChild(cosCell);

    table.appendChild(row);
}