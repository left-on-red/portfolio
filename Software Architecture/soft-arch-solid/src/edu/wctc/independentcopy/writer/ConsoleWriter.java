package edu.wctc.independentcopy.writer;

/**
 * A simple class that demonstrates a capability to write a line of text to the
 * console. Notice this class is derived from the interface <code>Writer</code>,
 * which is an abstraction of these details.
 *
 * See dependentcopy project for a poor design
 *
 * @author Jim Lombardo
 * @version 1.02
 * @see edu.wctc.independentcopy.Driver for run instructions and info about design rules
 */
public class ConsoleWriter implements Writer {

    /**
     * Write a line of text to the console.
     *
     * @param line - a String representing one line of text.
     */
    @Override
    public void writeln(String line) {
        System.out.println("Here is the text you entered, which I've copied below.");
        System.out.println(line);
    }
}
