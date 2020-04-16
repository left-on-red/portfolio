# The script of the game goes in this file.

# Declare characters used by this game. The color argument colorizes the
# name of the character.

define r = Character("Tom")
define u = Character("???")


# The game starts here.

label start:

    play music "audio/ambient.ogg"

    scene club

    show neutral

    u "hello. my name is Tom"
    r "my ideal career is to be a web and software developer."
    r "some of my hobbies include: video games, programming, eating, and sleeping (in that order)"
    r "I'm not a huge fan of movies and books, which invalidates 3 of the 7 questions in this script. but I'll improvise."
    r "whenever I'm bored, I like to take games and look into the source code to try to understand how they work and occasionally steal assets from them. like how I did with Doki Doki Literature Club to steal the club background that's being displayed right now."
    r "uh... the most important thing to know about me is that you I'll do pretty much anything if you give me enough Dr. Pepper™"

    # This ends the game.

    return
