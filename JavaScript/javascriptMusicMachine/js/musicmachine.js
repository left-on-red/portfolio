$(document).ready(function () {
    // Uses the audiosynth library to create an instrument
    // Other options include "piano", "acoustic", and "edm"
    var organ = Synth.createInstrument("organ");
    // Colors for the keys
    var allColors = ["#99CC00", "#0099FF", "#9933CC", "#CC0066", "#CC0033", "#FF3300", "#FF6600"];
    // Notes in the music scale
    var allNotes = ["C", "D", "E", "F", "G", "A", "B"];
    // Makes keys for these octaves
    const MIN_OCTAVE = 3, MAX_OCTAVE = 5;

    let song1 = ['C,3', 'D,3', 'E,3', 'F,3', 'G,3', 'A,3', 'B,3' ];

    // to "shake off" the object reference so .reverse() doesn't immute song1
    let song2 = JSON.parse(JSON.stringify(song1)).reverse();

    // Create the grid of keyboard keys
    for (var octave = MIN_OCTAVE; octave <= MAX_OCTAVE; octave++) {
        // Create a new row with a Bootstrap class
        var row = $("<div>").addClass("row");
        $("#keyboard").append(row);

        // Loop over array of color values
        for (var i = 0; i < allColors.length; i++) {
            // Create a new column with a Bootstrap class
            var col = $("<div>").addClass("col");
            row.append(col);

            // Create a <span> to be the clickable key
            var keyboardKey = $("<span>").addClass("key");
            col.append(keyboardKey);

            // Set background color with CSS
            var color = allColors[i];
            keyboardKey.css("background-color", color);

            // Set this key's note with data-note
            var note = allNotes[i];
            keyboardKey.data("note", note);

            // Set this key's octave with data-octave
            keyboardKey.data("octave", octave);

            // Make this key run a function when clicked
            keyboardKey.click(keyClicked);

            // Text for display
            keyboardKey.text(`${note}${octave}`);

            // Use note and octave to make a unique ID
            keyboardKey.attr("id", `${note}${octave}`);
        }
    }

    // Boolean for whether or not we're currently recording
    var isRecording = false;

    // Empty array to record a song as the user clicks notes
    var recordedNotes = [];

    // This anonymous function makes the Play Recording
    // button play the array of recorded notes
    $("#playButton").click(function () {
        playRecording(recordedNotes);
    });

    // Assign functions to the other buttons
    $("#recordButton").click(toggleRecording);
    $("#clearButton").click(clearRecording);

    $('#songOneButton').click(playSongOne);
    $('#songTwoButton').click(playSongTwo);

    function playSongOne() { playRecording(song1) }

    function playSongTwo() { playRecording(song2) }

    function clearRecording() {
        // create a new, empty array
        recordedNotes = [];
    }

    function toggleRecording() {
        // toggle the boolean flag variable
        isRecording = !isRecording;
        // toggle the class of the button and update its text
        $("#recordButton").toggleClass("btn-dark btn-light")
            .text(isRecording ? "Stop Recording" : "Start Recording");

        // if recording has just started,
        if (isRecording) {
            // new array for this recording
            clearRecording();
        }
    }

    function recordNote(note, octave) {
        // Make a string like "C,3"
        var entry = note + "," + octave;
        // Store the note information in the array
        recordedNotes.push(entry);
    }

    function playRecordedNote(recordedNote) {
        console.log(recordedNote);
        // recordedNote will contain a string like "C,3"
        // Split the string into an array where index 0
        // holds the note, and index 1 holds the octave
        var pieces = recordedNote.split(",");
        var note = pieces[0]; // "C"
        var octave = pieces[1]; // "3"
        // Put the note and octave on the screen
        $("#keyPlaying").text(note + octave);
        // Find all keys and remove the class that gives
        // active keys a dropshadow, change selection to
        // the one that matches the note being played,
        // and reapply the class to just that one span
        $("span.key").removeClass("playing")
            .filter(`#${note}${octave}`)
            .addClass("playing");
        // Play the recorded note
        playNote(note, octave);
    }

    function playRecording(arrayOfNotes) {
        // Loop over recorded notes, calling the anonymous
        // function for each element
        arrayOfNotes.forEach(function (entry, index) {
            // Cause another anonymous function to run
            // with a set delay (in milliseconds)
            setTimeout(function () {
                // The entry will be a string from the array,
                // like "C,3"
                playRecordedNote(entry);
            }, index * 500); // additional 500 MS delay for each note
        });

        // After all the recorded notes have played, clear the span
        // that displays the currently playing note and remove
        // the dropshadow for the last played key
        setTimeout(function () {
            $("span.key").removeClass("playing");
            $("#keyPlaying").html("&nbsp;");
        }, arrayOfNotes.length * 500);
    }

    function keyClicked() {
        // Which span was clicked?
        var keyPlayed = $(this);
        // Get its data-note attribute
        var notePlayed = keyPlayed.data("note");
        // Get its data-octave attribute
        var octavePlayed = keyPlayed.data("octave");

        // Make the sound play
        playNote(notePlayed, octavePlayed);

        // If recording is turned on, record the note info
        if (isRecording)
            recordNote(notePlayed, octavePlayed);
    }

    function playNote(note, octave) {
        // use the instrument from the audiosynth library
        // to play the desired note for half a second
        organ.play(note, octave, 0.5);
    }
});