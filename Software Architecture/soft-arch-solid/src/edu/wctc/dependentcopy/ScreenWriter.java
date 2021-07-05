package edu.wctc.dependentcopy;

/**
 * A simple class that demonstrates a capability to write a line of text to the
 * console. Notice this class has no abstract interface; it is derived from the
 * base class <code>Object</code> and extends the base interface with its own
 * (non-abstract) method writeln().
 *
 * See independentcopy project for a better design
 *
 * @author Jim Lombardo
 * @version 1.02
 * @see Driver for run instructions and info about design rules
 */
public class ScreenWriter {

    /**
     * Write a line of text to the console screen.
     *
     * @param line - a String representing one line of text.
     */
    public void writeln(String line) {
        System.out.println("Here is the text you entered, which I've copied below.");
        System.out.println(line);
    }
}
