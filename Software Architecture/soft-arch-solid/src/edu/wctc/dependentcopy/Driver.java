package edu.wctc.dependentcopy;

/**
 * @author Jim Lombardo
 * @version 1.02
 */
public class Driver {

    public static void main(String[] args) {
        // Declare rigid, concrete objects
        KeyboardReader reader = new KeyboardReader();
        ScreenWriter writer = new ScreenWriter();

        // Copy from reader to writer
        // Notice that copier is dependent on implementation of reader/writer
        // making it rigid, fragile and immobile
        Copier copier = new Copier(reader, writer);
        copier.copy();

        // Send end of program message
        System.out.println("Program ended. Line of reader input copied successfully to writer output.");
    }
}
