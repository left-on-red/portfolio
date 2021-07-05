package edu.wctc.part4.solution.withinput;

import java.util.Scanner;

public class KeyboardInputService {
    private Scanner scanner = new Scanner(System.in);

    public String getEmployeeFirstName() {
        System.out.print("Employee first name: ");
        String input = scanner.nextLine();
        return input;
    }

    public String getEmployeeLastName() {
        System.out.print("Employee last name: ");
        String input = scanner.nextLine();
        return input;
    }

    public String getEmployeeSsn() {
        System.out.print("Employee SSN: ");
        String input = scanner.nextLine();
        return input;
    }

    public String getEmployeeCubeId() {
        System.out.print("Employee cubicle ID: ");
        String input = scanner.nextLine();
        return input;
    }

}
