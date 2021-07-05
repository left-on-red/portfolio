package edu.wctc;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	    //private static Scanner keyboard = new Scanner(System.in);
        Scanner scanner = new Scanner(System.in);

        System.out.print("first name > ");
        String firstName = scanner.nextLine();

        System.out.print("middle initial > ");
        String middleInitial = scanner.nextLine();

        System.out.print("last name > ");
        String lastName = scanner.nextLine();

        System.out.print("birth month > ");
        String month = scanner.nextLine();

        System.out.print("birth day > ");
        String day = scanner.nextLine();

        System.out.print("birth year > ");
        String year = scanner.nextLine();

        System.out.print("gender (M/F) > ");
        String gender = scanner.nextLine();

        try {
            DriversLicense license = new DriversLicense();

            // I feel like it would be easier if these were static classes instead
            // or better yet, combined into one static class if they're each only going to have one method like that
            FirstNameUtility firstNameUtility = new FirstNameUtility();
            LastNameUtility lastNameUtility = new LastNameUtility();
            MonthDayGenderUtility monthDayGenderUtility = new MonthDayGenderUtility();

            license.setBirthYear(Integer.parseInt(year));
            license.setBirthMonthDayGender(monthDayGenderUtility.encodeMonthDayGender(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day), gender.charAt(0)));
            license.setFirstNameMiddleInitial(firstNameUtility.encodeFirstName(firstName, middleInitial));
            license.setSoundexCode(lastNameUtility.encodeLastName(lastName));
            license.setOverflow(00);

            FloridaFormatter floridaFormatter = new FloridaFormatter();
            WisconsinFormatter wisconsinFormatter = new WisconsinFormatter();

            System.out.printf("Florida license format: %s\n", floridaFormatter.formatLicenseNumber(license));
            System.out.printf("Wisconsin license format: %s\n", wisconsinFormatter.formatLicenseNumber(license));
        }

        catch(Exception e) { e.printStackTrace(); scanner.close(); }
    }
}
