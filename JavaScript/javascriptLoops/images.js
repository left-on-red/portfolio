$(document).ready(function () {

    $("button").click(runLoops);


    function runLoops() {
        // Clear out all existing images
        $(".imageHolder").empty();

        // Get the number from the box
        var num = parseInt($("#numPics").val());

        // Print the desired number of images for each loop
        $("#while-num").text(num + 1);
        $("#do-while-num").text(num - 1);
        $("#for-num").text(num);


        /*
         Create a while loop that runs "num + 1" times.

         The body of the loop should contain the statement:
         addImageToDiv("#while-loop");
        */

        let w = 0;
        while (w < num + 1) {
            addImageToDiv('#while-loop');
            w++;
        }

        /*
         Create a do-while loop that runs "num - 1" times.

         The body of the loop should contain the statement:
         addImageToDiv("#do-while-loop");
        */

        let d = 0;
        do {
            addImageToDiv('#do-while-loop');
            d++;
        }

        while (d < num - 1);

        /*
         Create a for loop that runs exactly "num" times.

         The body of the loop should contain the statement:
         addImageToDiv("#for-loop");
        */

        for (let f = 0; f < num; f++) { addImageToDiv('#for-loop') }

        // out of all of my time doing javascript development, I have never had a legitimate
        // reason to use any loop other than a for loop. it just does everything that every
        // other loop does but does it "better." declaration; condition; accumulator;
        // simple, clear, concise, and gets the job done perfectly
    }

    function addImageToDiv(divId) {
        var fileName = $(":radio[name='pictureSelect']:checked").val();
        // Create a new image element
        var imageElement = $("<img>");
        // Set its source attribute
        imageElement.attr("src", `img/${fileName}`);
        // Add it as a child of another element
        $(divId).append(imageElement);
    }

});