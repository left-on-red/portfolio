package edu.wctc.independentcopy;

import edu.wctc.independentcopy.reader.*;
import edu.wctc.independentcopy.writer.*;

/**
 * See dependentcopy project for a poor design
 *
 * @author Jim Lombardo
 * @version 1.02
 * @see Driver for run instructions and info about design rules
 */
public class Driver {

    public static void main(String[] args) {
        /*
         Comment/uncomment various combinations of Reader
         and Writer classes to see how switching objects
         causes no problems with Copier class because it's
         polymorphic!!!
         */

        //Reader reader = new KeyboardReader();
        //Reader reader = new FileReader();
        Reader reader = new ListReader();

        //Writer writer = new ConsoleWriter();
        //Writer writer = new GuiWriter();
        //Writer writer = new FileWriter();
        Writer writer = new ListWriter();

        /*
        Copy from reader to writer
        Notice that Copier is NOT dependent on implementation of reader/writer
        (it is not rigid, fragile or immobile)
         */
        Copier copier = new Copier(reader, writer);
        copier.copy();

        // Send end of program message
        System.out.println("Program ended. Line of reader input copied successfully to writer output.");
    }
}
