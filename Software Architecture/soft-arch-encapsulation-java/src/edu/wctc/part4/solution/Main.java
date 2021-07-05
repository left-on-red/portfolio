package edu.wctc.part4.solution;

/**
 * This class is the startup class for the sample application which is a
 * simulation of how companies hire employees. Notice that each class has a very
 * limited and focused set of responsibilities -- what we call the Single
 * Responsibility. Here that responsibility is to execute just enough code to
 * start the program. Everything else is delegated to hidden classes (Class
 * Encapsulation).
 *
 * Also notice how little information about how the rest of the
 * application works is exposed. This class follows the Principle of Least
 * Knowledge -- knowing as little about other parts of the program as is
 * feasible. Here, only the Company class is exposed.
 *
 * Not knowing about the other classes means this class is not dependent on
 * those other classes and changes can easily be made in those other classes
 * without affecting this class. That means greater flexibility and less rigidity
 * and fragility.
 *
 * @author Jim Lombardo
 * @version 1.02
 */
public class Main {

    public static void main(String[] args) {
        Company company = new Company();

        // Startup delegates work to Company which then delegates work to HRManager
        company.hireEmployee("John", "Doe", "444-44-4444");

    }

}
