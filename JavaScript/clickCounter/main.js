let element = document.getElementById('clicks');
let score = 0;

if (localStorage.getItem('score')) { score = parseInt(localStorage.getItem('score')); display(); }

function add() {
    score++;

    // I wasn't sure if this would break if I specified the value as a non-string so I just wrapped it as a template literal
    localStorage.setItem('score', `${score}`);
    display();
}

function reset() { localStorage.clear(); window.location.reload(); }
function display() { element.innerText = `${score} clicks` }