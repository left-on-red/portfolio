package edu.wctc.part4;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * As with part3 you should focus on CLASS Encapsulation and the
 * Single Responsibility Principle (SRP). However what makes this lab DIFFERENT
 * is that in a real program you would need more than just an Employee class and
 * a EmployeeReportService class. Remember what you learned about OOAD and
 * finding the Conceptual Objects in the Problem Domain.
 *
 * Pay special attention to the following issues:
 *
 * 1. You will need additional classes to simulate the real world. To keep things
 * simple you should think about adding two more classes. Think about how employees
 * are hired, who does the hiring and who do those people work for. Think about the
 * Nouns used in business with respect to hiring employees and commanding them to go
 * through orientation.
 *
 * 2. When adding these classes think about the Single Responsibility Principle
 * and Class Encapsulation -- hiding classes inside other classes, similar to
 * what you did with the report service which was hidden inside the Employee
 * class.
 *
 * 3. For those who struggle with this final lab a solution is provided.
 * However, you should not look at the solution unless absolutely necessary if
 * you are stuck. Try to do as much of this lab on your own by doing critical
 * thinking about the classes you need and the responsibilities you are giving
 * each class.
 *
 * Review the tips in the document Encapsulation Checklist if needed.
 */
public class Employee {

    // Use constants for numbers or Strings that are repeated
    // (all are called 'magic numbers', which are evil). This makes editing
    // these values easier -- one place to do it.
    private final String REQUIRED_MSG = " is mandatory ";
    private final String NEWLINE = "\n";

    private String firstName;
    private String lastName;
    private String ssn;
    private boolean metWithHr;
    private boolean metDeptStaff;
    private boolean reviewedDeptPolicies;
    private boolean movedIn;
    private String cubeId;
    private LocalDate orientationDate;
    private EmployeeReportService reportService = new EmployeeReportService();

    /*
        Notice we force certain mandatory properties by using a custom
        constructor. But we use the setter method to perform validation.
     */
    public Employee(String firstName, String lastName, String ssn) {
        // Using setter method guarantees validation will be performed
        // Ignore the warning messages for now. Will be explained later
        setFirstName(firstName);
        setLastName(lastName);
        setSsn(ssn);
    }

    /*
        This should be private because it is useful only to this class and then,
        only as a helper method to other methods. This is method hiding - a type
        of encapsulation where we put frequently used code in one place for easy
        reuse and editing.
     */
    private String getFormattedDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yy");
        return formatter.format(orientationDate);
    }

    /*
        This method is public because it must be available to other classes in
        this project. Notice that it controls the order in which the helper methods
        are called. Order isn't always an issue, but here it obviously is, which
        may be an important requirement.
     */
    public void doFirstTimeOrientation(String cubeId) {
        orientationDate = LocalDate.now();
        meetWithHrForBenefitAndSalaryInfo();
        meetDepartmentStaff();
        reviewDeptPolicies();
        moveIntoCubicle(cubeId);
    }

    // The following methods may be public or private, depending on whether
    // they need to be called from other classes independently.
    //
    // Assume this must be performed first, and assume that an employee
    // would only do this once, upon being hired. If that were true, this
    // method should not be public. It should only be available to this class
    // and should only be called as part of the larger task of:
    // doFirstTimeOrientation()
    private void meetWithHrForBenefitAndSalaryInfo() {
        metWithHr = true;
        reportService.addData(firstName + " " + lastName + " met with HR on " + getFormattedDate() + NEWLINE);
    }

    // Assume this must be performed second, and assume that an employee
    // would only do this once, upon being hired. If that were true, this
    // method should not be public. It should only be available to this class
    // and should only be called as part of the larger task of:
    // doFirstTimeOrientation()
    private void meetDepartmentStaff() {
        setMetDeptStaff(true);
        reportService.addData(firstName + " " + lastName + " met with dept staff on " + getFormattedDate() + NEWLINE);
    }

    // Assume this must be performed third. And assume that because department
    // policies may change that this method may need to be called
    // independently from other classes.
    public void reviewDeptPolicies() {
        setReviewedDeptPolicies(true);
        reportService.addData(firstName + " " + lastName + " reviewed dept policies on " + getFormattedDate() + NEWLINE);
    }

    // Assume this must be performed 4th. And assume that because employees
    // sometimes change office locations that this method may need to be called
    // independently from other classes.
    public void moveIntoCubicle(String cubeId) {
        setCubeId(cubeId);
        setMovedIn(true);
        reportService.addData(firstName + " " + lastName + " moved into cubicle " + cubeId + " on " + getFormattedDate() + NEWLINE);
    }

    public String getFirstName() {
        return firstName;
    }

    // Setter methods give the developer the power to control what data is
    // allowed through validation. Throwing ane exception is the best
    // practice when validation fails. Don't do a System.out.println()
    // to display an error message -- not the job of this class!
    public void setFirstName(String firstName) {
        if (firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException("first name" + REQUIRED_MSG);
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("last name" + REQUIRED_MSG);
        }
        this.lastName = lastName;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        if (ssn == null || ssn.length() < 9 || ssn.length() > 11) {
            throw new IllegalArgumentException("ssn" + REQUIRED_MSG + "and must be between 9 and 11 characters (if hyphens are used)");
        }
        this.ssn = ssn;
    }

    public boolean hasMetWithHr() {
        return metWithHr;
    }

    // boolean parameters need no validation
    public void setMetWithHr(boolean metWithHr) {
        this.metWithHr = metWithHr;
    }

    public boolean hasMetDeptStaff() {
        return metDeptStaff;
    }

    public void setMetDeptStaff(boolean metDeptStaff) {
        this.metDeptStaff = metDeptStaff;
    }

    public boolean hasReviewedDeptPolicies() {
        return reviewedDeptPolicies;
    }

    public void setReviewedDeptPolicies(boolean reviewedDeptPolicies) {
        this.reviewedDeptPolicies = reviewedDeptPolicies;
    }

    public boolean hasMovedIn() {
        return movedIn;
    }

    public void setMovedIn(boolean movedIn) {
        this.movedIn = movedIn;
    }

    public String getCubeId() {
        return cubeId;
    }

    public void setCubeId(String cubeId) {
        if (cubeId == null || cubeId.isBlank()) {
            throw new IllegalArgumentException("cube id" + REQUIRED_MSG);
        }
        this.cubeId = cubeId;
    }

    public LocalDate getOrientationDate() {
        return orientationDate;
    }

    public void setOrientationDate(LocalDate orientationDate) {
        if (orientationDate == null) {
            throw new IllegalArgumentException("orientationDate" + REQUIRED_MSG);
        }
        this.orientationDate = orientationDate;
    }

    public void printReport() {
        reportService.outputReport();
    }
}
