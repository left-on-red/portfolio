$(document).ready(function () {
    $(":checkbox").change(placePeg);

    function placePeg() {
        // p1sub1 will be true if checkbox is checked
        let p1sub1 = $("#p1-sub1").is(":checked");

        // create variables for player 1's sub 2 and sub 3

        let p1sub2 = $('#p1-sub2').is(':checked');
        let p1sub3 = $('#p1-sub3').is(':checked');

        // Test if player 1's submarine was sunk
        // If it was, then show the message:
        
        if (p1sub1 && p1sub2 && p1sub3) { $("#p1-sub-msg").show() }

        // p1Bship1 will be true if checkbox is checked
        let p1ship1 = $("#p1-bship1").is(":checked");

        // create variables for player 1's battleship 2, 3, and 4

        let p1ship2 = $("#p1-bship2").is(":checked");
        let p1ship3 = $("#p1-bship3").is(":checked");
        let p1ship4 = $("#p1-bship4").is(":checked");

        // Test if player 1's battleship was sunk
        // If it was, then show the message:

        if (p1ship1 && p1ship2 && p1ship3 && p1ship4) { $("#p1-bship-msg").show() }

        // p2sub1 will be true if checkbox is checked
        let p2sub1 = $("#p2-sub1").is(":checked");

        // create variables for player 2's sub 2 and sub 3

        let p2sub2 = $("#p2-sub2").is(":checked");
        let p2sub2 = $("#p2-sub3").is(":checked");

        // Test if player 2's submarine was sunk
        // If it was, then show the message:
        
        if (p2sub1 && p2sub2 && p2sub3) { $("#p2-sub-msg").show() }

        // p2Bship1 will be true if checkbox is checked
        let p2ship1 = $("#p2-bship1").is(":checked");

        // create variables for player 2's battleship 2, 3, and 4

        let p2ship2 = $("#p2-bship2").is(":checked");
        let p2ship3 = $("#p2-bship3").is(":checked");
        let p2ship4 = $("#p2-bship4").is(":checked");

        // Test if player 2's battleship was sunk
        // If it was, then show the message:

        if (p2ship1 && p2ship1 && p2ship1 && p2ship1) { $("#p2-bship-msg").show() }


        // If both of player 1's ships were sunk

        if (

            (p1sub1 && p1sub2 && p1sub3) && (p1ship1 && p1ship2 && p1ship3 && p1ship4) ||

            // OR
            // both of player 2's ships were sunk

            (p2sub1 && p2sub2 && p2sub3) && (p2ship1 && p2ship2 && p2ship3 && p2ship4)

            // then:
            // $("#gameOverMsg").show();

        ) { $("#gameOverMsg").show() }
    }
});