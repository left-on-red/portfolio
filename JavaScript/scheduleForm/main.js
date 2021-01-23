$(document).ready(function() {
    let radios = [];
    for (let i = 0; i < 7; i++) { radios.push($(`#radio${i}`)) }

    let result = $('#result');
    
    // mon, tues, wed, thurs, fri, sat, sun
    let schedule = [
        [
            [ 'class zoom meeting', '8:00 AM - 10:00 AM' ],
            [ 'personal projects', '3:00 PM - 6:00 PM' ]
        ],

        [
            [ 'schoolwork', '9:00 AM - 11:00 AM' ],
            [ 'video games', '4:00 PM - 8:00 PM' ]
        ],

        [
            [ 'schoolwork', '9:00 AM - 11:00 AM' ],
            [ 'chores', '12:00 PM - 2:00 PM' ]
        ],

        [
            [ 'personal projects', '10:00 AM - 2:00 PM' ],
            [ 'video games', '3:00 PM - 9:00 PM' ]
        ],

        [
            [ 'schoolwork', '10:00 AM - 2:00 PM' ],
            [ 'personal projects', '3:00 PM - 6:00 PM' ]
        ],

        [
            [ 'personal projects', '12:00 PM - 4:00 PM' ],
            [ 'video games', '5:00 PM - 8:00 PM' ]
        ],

        [
            [ 'personal projects', '11:00 AM - 4:00 PM' ],
            [ 'schoolwork', '5:00 PM - 8:00 PM' ]
        ]
    ]

    $(':radio').change(function() {
        let index = parseInt($(`input[name='day']:checked`).val());
        result.html(schedule[index].map(function(act) { return act.join(' -------------- ') }).join('<br>'));
    });
});