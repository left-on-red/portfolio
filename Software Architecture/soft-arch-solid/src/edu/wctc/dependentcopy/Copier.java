package edu.wctc.dependentcopy;

/**
 * This class is totally dependent on KeyboardReader and ScreenWriter classes --
 * a very poor design.
 *
 * It's rigid -- you may only use a KeyboardReader and a ScreenWriter; no other
 * reader of writer objects are allowed.
 *
 * It's fragile -- you cannot change the reader or writer object data types
 * without breaking other code that depends on this class definition.
 *
 * It's immobile (not portable to other projects) -- you cannot reuse this
 * Copier class in other projects where different types of reader and writer
 * objects are required.
 *
 * See independentcopy project for a better design
 *
 * @author Jim Lombardo
 * @version 1.02
 * @see Driver for run instructions and info about design rules
 *
 */
public class Copier {
    // instance variables reference incoming arguments which are
    // needed by copy() method below.

    KeyboardReader reader;
    ScreenWriter writer;

    /**
     * Custom Constructor requires KeyboardReader and ScreenWriter objects.
     * Constructor is dependent on these concrete implementations. Therefore
     * this constructor method IS NOT polymorphic -- a big disadvantage!
     */
    public Copier(KeyboardReader reader, ScreenWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    /**
     * Copy one line of input from keyboard to console screen. Program ends when
     * carriage return is entered. Notice how rigid, fragile and immobile this
     * class is due to these dependencies!
     */
    public void copy() {
        String line = reader.readln();
        writer.writeln(line);
    }
}
