package edu.wctc.independentcopy.writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * A simple low-level, derived class that demonstrates the capability to write a
 * line of input to a file. Notice this class is derived from the interface
 * <code>Writer</code>, which is an abstraction of these details.
 *
 * See dependentcopy project for a poor design
 *
 * @author Jim Lombardo
 * @version 1.02
 * @see edu.wctc.independentcopy.Driver for run instructions and info about design rules
 */
public class FileWriter implements edu.wctc.independentcopy.writer.Writer {

    /**
     * Write a line of input to a file "datacopy.txt"
     */
    @Override
    public void writeln(String line) {
        boolean append = false;
        File data = new File("datacopy.txt");
        PrintWriter out = null;

        // This is where we setup our streams (append = false means overwrite)
        // new java.io.FileWriter() creates the file if doesn't exit
        try {
            // make sure we differentiate between java.io.FileWriter
            // class and this class, also called FileWriter
            out = new PrintWriter(
                    new BufferedWriter(
                            new java.io.FileWriter(data, append)));
            out.println(line);
            out.close();

        } catch (IOException ioe) {
            if (out != null) {
                out.close();
            }
            System.out.println(ioe.getMessage());
            System.exit(1);  // 1 = signals program end with error
        }

    }

}
