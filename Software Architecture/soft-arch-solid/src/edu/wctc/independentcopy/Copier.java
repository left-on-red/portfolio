package edu.wctc.independentcopy;

import edu.wctc.independentcopy.reader.Reader;
import edu.wctc.independentcopy.writer.Writer;

/**
 * This class has only dependencies to abstractions, not to details (concrete
 * classes). See how the Copier method does not depend on any specific concrete
 * class. This is called loose coupling and it makes classes like this one more
 * flexible and easier to reuse with other programs. This is a classic example
 * of the Dependency Inversion Principle (DIP) as well as the Liskov
 * Substitution Principle.
 *
 * See dependentcopy project for a poor design
 *
 * @author Jim Lombardo
 * @version 1.02
 * @see Driver for run instructions and info about design rules
 */
public class Copier {

    private Reader reader;
    private Writer writer;

    /**
     * Get a Reader and Writer object (interface types). Copying ability is open
     * to any number of implementations that implement those interfaces.
     *
     * Both the Dependency Inversion Principle (DIP) and the Liskov Substitution
     * Principle are highlighted here.
     */
    public Copier(Reader reader, Writer writer) {
        this.reader = reader;
        this.writer = writer;
    }

    /**
     * Copy one line of input from reader to writer. Program ends when carriage
     * return is entered. Notice how flexible this is due to polymorphism!
     */
    public void copy() {
        String line = reader.readln();
        writer.writeln(line);
    }
}
