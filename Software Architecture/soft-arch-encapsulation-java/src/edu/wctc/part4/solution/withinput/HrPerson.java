package edu.wctc.part4.solution.withinput;

import edu.wctc.part4.solution.Employee;

import java.util.ArrayList;
import java.util.List;

public class HrPerson {

    private List<Employee> employees = new ArrayList<>();
    private KeyboardInputService inputService;

    public HrPerson(KeyboardInputService inputService) {
        this.inputService = inputService;
    }

    public void hireEmployee(String firstName, String lastName, String ssn) {
        Employee e = new Employee(firstName, lastName, ssn);
        employees.add(e);
        orientEmployee(e);
    }

    // HRManager delegates work to employee
    private void orientEmployee(Employee emp) {
        String cubicleId = inputService.getEmployeeCubeId();
        emp.doFirstTimeOrientation(cubicleId);
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
