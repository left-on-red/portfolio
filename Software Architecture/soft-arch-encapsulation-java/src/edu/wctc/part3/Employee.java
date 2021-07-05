package edu.wctc.part3;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * In this lab focus on CLASS Encapsulation and the Single Responsibility
 * Principle (SRP) and fix/add code as necessary.
 *
 * Pay special attention to the following issues:
 *
 * 1. It is not the job of this class or any of the methods in this class to
 * do output. So you must remove the System.out.println statements. But we
 * still need output. What to do?
 *
 * Delegate that work to a new object that IS responsible for output. Create an
 * output service class and have the employee object talk to that object to
 * perform the output. Using a separate class inside another like this is a form
 * of class encapsulation -- hiding a class within another class.
 *
 * Notice how you can easily change the way output is done, going from console
 * output to JOptionPane output if desired without the Employee object knowing
 * about the change. Flexible!
 *
 * 2. When doing method validation we have a similar problem. It is not the job
 * of this class or any of its methods to do output. But error messages aren't a
 * reporting issue. Error messages can be produced from invalid data in any
 * program, whether or not those programs have reporting services.
 *
 * So we need a different approach. The right thing to do is to create an
 * exception that notifies the user that a validation error has happened. An
 * example of this is provided in the setFirstName() method. Mimic this behavior
 * in other setter methods.
 *
 * Review the tips in the Encapsulation Checklist document if needed.
 */
public class Employee {

    private String firstName;
    private String lastName;
    private String ssn;
    private boolean metWithHr;
    private boolean metDeptStaff;
    private boolean reviewedDeptPolicies;
    private boolean movedIn;
    private String cubeId;
    private LocalDate orientationDate;
    private final CommandLineOutputService output = new CommandLineOutputService();

    public Employee(String firstName, String lastName, String ssn, String cubeId) {
        // Using setter method guarantees validation will be performed
        setFirstName(firstName);
        setLastName(lastName);
        setSsn(ssn);
        doFirstTimeOrientation(cubeId);
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
        setMetWithHr(true);
        output.simpleOutput(firstName + " " + lastName + " met with HR on " + getFormattedDate());
    }

    // Assume this must be performed second, and assume that an employee
    // would only do this once, upon being hired. If that were true, this
    // method should not be public. It should only be available to this class
    // and should only be called as part of the larger task of
    // doFirstTimeOrientation()
    private void meetDepartmentStaff() {
        setMetDeptStaff(true);
        output.simpleOutput(firstName + " " + lastName + " met with dept staff on " + getFormattedDate());
    }

    // Assume this must be performed third. And assume that because department
    // policies may change that this method may need to be called
    // independently from other classes.
    public void reviewDeptPolicies() {
        setReviewedDeptPolicies(true);
        output.simpleOutput(firstName + " " + lastName + " reviewed dept policies on " + getFormattedDate());
    }

    // Assume this must be performed 4th. And assume that because employees
    // sometimes change office locations that this method may need to be called
    // independently from other classes.
    public void moveIntoCubicle(String cubeId) {
        setCubeId(cubeId);
        setMovedIn(true);
        output.simpleOutput(firstName + " " + lastName + " moved into cubicle " + cubeId + " on " + getFormattedDate());
    }

    public String getFirstName() {
        return firstName;
    }

    // Setter methods give the developer the power to control what data is
    // allowed through validation. Throwing an exception is the best
    // practice when validation fails. Don't do a System.out.println()
    // to display an error message -- not the job of this class!
    public void setFirstName(String firstName) {
        if (firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException("first name is required");
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("last name is required");
        }
        this.lastName = lastName;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        if (ssn == null || ssn.length() < 9 || ssn.length() > 11) { // Magic numbers!
            throw new IllegalArgumentException("ssn is required and must be between 9 and 11 characters (if hyphens are used)");
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
            throw new IllegalArgumentException("cube id is required");
        }
        this.cubeId = cubeId;
    }

    public LocalDate getOrientationDate() {
        return orientationDate;
    }

    public void setOrientationDate(LocalDate orientationDate) {
        if (orientationDate == null) {
            throw new IllegalArgumentException("orientation date is required");
        }
        this.orientationDate = orientationDate;
    }
}
