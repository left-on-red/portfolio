Instructions for DependentCopy Project:
===========================================
This example demonstrates a very poor design -- one that is burdened by dependency issues, causing **rigidity** (requires KeyboardReader and ScreenWriter objects), **fragility** (changing the Copier class to accommodate other Reader or Writer objects might break programs using the original version) and **immobility** (you can't use the Copier class where file input and file output are needed, for example).

Notice that the high-level Copier class (also called the client, because it uses the services of other objects) is highly dependent on the KeyboardReader and ScreenWriter implementations (low-level classes).

Design error #1 -- Copier violates Dependency Inversion Principle, which states that 1) high-level modules should not depend upon low-level modules. Both should depend upon abstractions; and 2) Abstractions should not depend upon details. Details should depend upon abstractions. The term "details" refers to implementation logic.

Design error #2 -- Violates Liskov Substitution Principle, which tells us: derived classes (e.g. KeyboardReader and ScreenWriter) must be usable through their base class interface (in this case, the base class is the Object class), without the client (in this case, Copier) being able to tell the difference. That isn't true here! The object class does not have the methods readln() and writeln() which are in KeyboardReader and KeyboardWriter respectively.

HOW TO USE: Run the main in Driver.java. Type in some text at the console, then press return. The text will be copied to the output console.