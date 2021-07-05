package edu.wctc.dependentcopy;

import java.util.Scanner;

/**
 * A simple class that demonstrates a capability to read a line of text entered
 * from the keyboard. Notice this class has no abstract interface; it is derived
 * from the base class <code>Object</code> and extends the base interface with
 * its own (non-abstract) method, readln().
 *
 * See independentcopy project for a better design
 *
 * @author Jim Lombardo
 * @version 1.02
 * @see Driver for run instructions and info about design rules
 */
public class KeyboardReader {

    /**
     * Reads characters from the keyboard until a carriage return is entered.
     *
     * @return a String representing one line of input.
     */
    public String readln() {
        System.out.println("Please enter some text, then press return:");

        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }
}
