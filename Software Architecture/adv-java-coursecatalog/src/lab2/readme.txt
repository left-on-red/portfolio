
Objectives for Lab #2:
---------------------
   * Practice using abstraction by creating an Interface super class and
     choosing an appropriate interface name (should be a noun).
   * Practice identifying common behaviors (methods) and placing
     those things in the super class
   * Properties must go in the sub-classes because Interfaces don't allow properties.
   * In the files provided many mistakes have been made with regard to proper
     encapsulation (declaring properties private, etc.) and placement of
     common members. You may have to move some things around, remove things
     and/or create new things to make this work better.
   * Can you think of any other ways to improve on this design?

Instructions for Lab #2:
----------------------
1. Create an interface super class that contains common methods
   to be inherited by the sub-classes that are provided.
2. Modify your sub-classes to take advantage of this inheritance. You will 
   need to implement all methods in the sub classes. Remember that NetBeans has
   a wizard to help you with this when you select to "implement all abstract
   methods". How will you deal with common fields? Remember, interfaces
   can't have instance fields!
3. Fix any and all encapsulation problems.
4. Create a driver class to test your code. In this driver class create and
   use at least one instance of each sub-class.
5. In the driver class document what you think is good or bad about this
   architecture from the perspective of using abstraction and inheritance.
   (Remember, a driver class must have a main method)
6. In your solution when can Liskov Substitution principle be used and when
   is it not possible. Write down your answer by providing a comment in the
   driver class.

