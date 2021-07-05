package edu.wctc.part4.solution.withinput;

import edu.wctc.part3.CommandLineOutputService;

public class Company {
    private HrPerson hr;
    private KeyboardInputService inputService;
    private CommandLineOutputService outputService;

    public Company() {
        // Company creates a helper object to manage getting input
        inputService = new KeyboardInputService();
        // Simple output service to print exception messages
        outputService = new CommandLineOutputService();

        // HrPerson also needs help with input, so the helper object
        // is shared with her
        hr = new HrPerson(inputService);
    }

    public void hireEmployee() {
        boolean success = false;

        while (!success) {
            try {
                String firstName = inputService.getEmployeeFirstName();
                String lastName = inputService.getEmployeeLastName();
                String ssn = inputService.getEmployeeSsn();

                hr.hireEmployee(firstName, lastName, ssn);
                hr.outputReport(ssn);

                success = true;
            } catch(IllegalArgumentException exception) {
                outputService.simpleOutput(exception.getMessage());
            }
        }
     }
}
