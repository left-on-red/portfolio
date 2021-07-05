# bookstore

INSTRUCTIONS:
===================================
You have been presented with a description and purpose of the
Interface Segregation Principle (ISP). You will need to understand this
principle to successfully complete the lab work.

Try not to peek at the instructor solutions in "edu.wctc.isp.solution". If you get stuck or want to check if your solution is
a good one, review the instructor's solutions.

ISP RULES:
Clients should not be forced to depend on methods that they do not use.

THE CHALLENGE:
===================================
This lab includes an interface that breaks the Interface Segregation Principle (ISP).

For the first part, refactor the program in "edu.wctc.isp" to use two interfaces
instead of one. Create two interfaces that properly segregate the methods of
books versus music products.

For the second part, refactor your two-interface solution to allow for
polymorphism. Remember that interfaces can extend other interfaces! Extract
commonalities between music and books into a third interface. When you are
finished, you should be able to create a List of the common interface type, and
add hardcover books, paperback books, and vinyl albums to it.
