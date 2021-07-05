package edu.wctc.part4.solution;

/**
 * This class simulates a real company and in this simple program is responsible
 * for delegating the hiring of employees to the HrManager, which then requires
 * the Employee go through orientation.
 *
 * @author Jim Lombardo
 * @version 1.02
 */
public class Company {

    private HrPerson hr;

    public Company() {
        hr = new HrPerson();
    }

    public void hireEmployee(String firstName, String lastName, String ssn) {
        hr.hireEmployee(firstName, lastName, ssn);
        hr.outputReport(ssn);
    }

}
