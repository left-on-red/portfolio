package edu.wctc.independentcopy.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

/**
 * A simple low-level, derived class that demonstrates the capability to read a
 * line of input from a file. Notice this class is derived from the interface
 * <code>Reader</code>, which is an abstraction of these details.
 *
 * See dependentcopy project for a poor design
 *
 * @author Jim Lombardo
 * @version 1.02
 * @see edu.wctc.independentcopy.Driver for run instructions and info about design rules
 */
public class FileReader implements edu.wctc.independentcopy.reader.Reader {

    private boolean lineReadFlag = false;

    public void method2() {
        System.out.println("not a polymorphic method");
    }

    /**
     * Read a line of input from a file
     */
    @Override
    public String readln() {
        File data = new File("data.txt");

        BufferedReader in = null;
        String line = null;

        try {
            if (data.exists()) {
                // make sure we differentiate between java.io.FileReader
                // class and this class (also called FileReader)
                in = new BufferedReader(new java.io.FileReader(data));
                line = in.readLine();
                in.close();
            } else {
                System.out.println("File not found - data.txt");
                line = null;
            }
        } catch (IOException ioe) {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ioe2) {
                System.out.println(ioe2.getMessage());
            }
            System.out.println(ioe.getMessage());
            System.exit(1);  // 1 = signals program end with error
        }

        // ugly hack so we can end the program after reading a line
        if (lineReadFlag) {
            return null;
        } else {
            lineReadFlag = true;
            return line;
        }
    }

}
