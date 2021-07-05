package edu.wctc.independentcopy.reader;

import java.util.Scanner;

/**
 * A simple low-level, derived class that demonstrates the capability to read a
 * line of input from the keyboard. Notice this class is derived from the
 * interface <code>Reader</code>, which is an abstraction of these details.
 *
 * See dependentcopy project for a poor design
 *
 * @author Jim Lombardo
 * @version 1.02
 * @see edu.wctc.independentcopy.Driver for run instructions and info about design rules
 */
public class KeyboardReader implements Reader {

    /**
     * Reads characters from the keyboard until a carriage return is entered.
     *
     * @return a String representing one line of input.
     */
    @Override
    public String readln() {
        System.out.println("Please enter some text, then press return:");

        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }
}
