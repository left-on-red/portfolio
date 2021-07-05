This example demonstrates a good design -- one that has NO dependency issues. 

It is not **rigid** because Copier can be used with an unlimited number of Reader and Writer objects.

It is not **fragile** because the Copier class does not require changes to support various Readers and Writers -- changes that would break other classes that depend on the Copier class.

It is **portable** because you can use the Copier class in many programs without fear of problems caused by implementation-specific code, such as KeyboardReader.

Notice that the high-level Copier class (also called the client, because it uses the services of other objects) is not dependent on any low-level classes. Instead, it is only dependent upon abstractions (interfaces, although we could just as easily use abstract classes), following the Dependency Inversion Principle and the Liskov Substitution Principle.

Design Success #1 -- Adheres to Dependency Inversion Principle, which states that 1) high-level modules should not depend upon low-level modules. Both should depend upon abstractions; and 2) Abstractions should not depend upon details. Details should depend upon abstractions.

Design Success #2 -- Adheres to Liskov Substitution Principle, which tells us that derived classes (e.g. KeyboardReader, FileReader) must be usable through their base class interfaces (Reader and Writer), without the client (in this case, Copier) being able to tell the difference.

HOW TO USE: Run the main in Driver.java. Comment and uncomment various combinations of Writer and Reader implementations and note the effects of polymorphism.

When using FileReader, make sure you have a plain text file named "data.txt" in the default file location of your IDE of choice. This file should contain at least one line of text.

If you are using FileWriter, the program will create "datacopy.txt" in the default directory.
