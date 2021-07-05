package edu.wctc.part4.solution;

import java.util.ArrayList;
import java.util.List;

/**
 * This class simulates an HR person that does the actual hiring and commands
 * Employees to go through orientation.
 *
 * @author Jim Lombardo
 * @version 1.03
 */
public class HrPerson {

    private List<Employee> employees = new ArrayList<>();

    public HrPerson() {

    }

    public void hireEmployee(String firstName, String lastName, String ssn) {
        Employee e = new Employee(firstName, lastName, ssn);
        employees.add(e);
        orientEmployee(e);
    }

    // HRManager delegates work to employee
    private void orientEmployee(Employee emp) {
        emp.doFirstTimeOrientation("B101");
    }

    public void outputReport(String ssn) {

        // find employee in list
        for (Employee emp : employees) {
            if (emp.getSsn().equals(ssn)) {
                // if found run report
                if (emp.hasMetWithHr() && emp.hasMetDeptStaff()
                        && emp.hasReviewedDeptPolicies() && emp.hasMovedIn()) {
                    emp.printReport();
                }
                break;
            }
        }
    }

}
