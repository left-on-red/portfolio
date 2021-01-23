$(document).ready(
    function () {
        // event handlers
        $("#tabs a").click(showTab);
        $("#animateButton").click(animateSandwich);


        // other functions
        function showTab(event) {
            event.preventDefault();
            $(this).tab("show");
        }

        function animateSandwich() {
            // Fade to 0% opacity over 750 milliseconds
            // Wait 250 milliseconds
            // Fade to 100% opacity over 750 milliseconds
            // Hide the sandwich by shrinking its height over 750 ms
            // Wait 250 ms
            // Show the sandwich by increasing its height over 750 ms
            $("#sandwich").fadeTo(750, 0).delay(250).fadeTo(750, 1)
                .slideUp(750).delay(250).slideDown(750);
        }
    });