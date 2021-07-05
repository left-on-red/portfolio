package edu.wctc.part4.solution;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Here is one example of a final solution to our Encapsulation labs where
 * PROPERTY, METHOD and CLASS Encapsulation are all used in a multi-class
 * program that properly simulates the real world.
 *
 * Note the following features:
 *
 * -- All properties are private, controlled by public getter and setter methods
 * that control what data is allowed through validation
 *
 * -- Code that is repeated (Don't Repeat Yourself or DRY) is encapsulated in a
 * helper method called "getFormattedDate". Such methods don't have to be private
 * but this one is because it has no use elsewhere.
 *
 * -- Methods that need to be called in a specific order are encapsulated in a single
 * parent method called "doFirstTimeOrientation". This assures that the order will be
 * followed. But even if order where not a priority this code would still benefit from
 * the use of parent method/helper method architecture. It forces each method routine to
 * be simple, for easy understanding and editing, and it makes the code more
 * readable. Further, it means that the pubic methods are reusable by
 * themselves.
 *
 * -- Not all methods are public. Having too many public methods
 * makes a class harder to understand and more complex. Some of the methods
 * below are declared private because they don't need to be called from other
 * classes.
 *
 * -- Notice that a separate EmployeeReportService object is created so
 * that the responsibility for doing output is delegated to that object. It is
 * not the job of an Employee to do output. (Single Responsibility Principal).
 * Also, this is a reusable object that can be used elsewhere.
 *
 * -- Other classes representing nouns in the problem domain are used to make the simulation
 * closer to reality. A Company "has a" HrManager that hires Employees. When an
 * employee is hired the HrManager issues the command to the employee to do the
 * orientation, but the HrManager is acting on behalf of the Company so it is
 * the company that is actually hiring the employee and mandating orientation.
 * These tasks are all delegated to the hidden objects inside. Company delegates
 * to HrManager, HrManager delegates to Employee -- just like the real world!
 *
 * @author Jim Lombardo
 * @version 1.02
 */
public class Employee {

    // Use constants for numbers or Strings that are repeated
    // (all are called 'magic numbers', which are evil)
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
    // Assume this must be performed first, and assume that an employee
    // would only do this once, upon being hired. If that were true, this
    // method should not be public. It should only be available to this class
    // and should only be called as part of the larger task of:
    // doFirstTimeOrientation()
    private void meetWithHrForBenefitAndSalaryInfo() {
        metWithHr = true;
        reportService.addData(firstName + " " + lastName + " met with HR on "
                + getFormattedDate() + NEWLINE);
    }

    // Assume this must be performed first, and assume that an employee
    // would only do this once, upon being hired. If that were true, this
    // method should not be public. It should only be available to this class
    // and should only be called as part of the larger task of:
    // doFirstTimeOrientation()
    private void meetDepartmentStaff() {
        metDeptStaff = true;
        reportService.addData(firstName + " " + lastName + " met with dept staff on "
                + getFormattedDate() + NEWLINE);
    }

    // Assume this must be performed third. And assume that because department
    // policies may change that this method may need to be called
    // independently from other classes.
    public void reviewDeptPolicies() {
        reviewedDeptPolicies = true;
        reportService.addData(firstName + " " + lastName + " reviewed dept policies on "
                + getFormattedDate() + NEWLINE);
    }

    // Assume this must be performed 4th. And assume that because employees
    // sometimes change office locations that this method may need to be called
    // independently from other classes.
    public void moveIntoCubicle(String cubeId) {
        setCubeId(cubeId);

        this.movedIn = true;
        reportService.addData(firstName + " " + lastName + " moved into cubicle "
                + cubeId + " on " + getFormattedDate() + NEWLINE);
    }

    public String getFirstName() {
        return firstName;
    }

    // setter methods give the developer the power to control what data is
    // allowed through validation. Throwing ane exception is the best
    // practice when validation fails. Don't do a System.out.println()
    // to display an error message -- not the job of this class!
    public void setFirstName(String firstName) {
        if (firstName == null || firstName.isEmpty()) {
            throw new IllegalArgumentException("first name" + REQUIRED_MSG);
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.isEmpty()) {
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
        if (cubeId == null || cubeId.isEmpty()) {
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

    @Override
    public String toString() {
        return "Employee{" + "firstName=" + firstName + ", lastName=" + lastName + ", ssn=" + ssn + '}';
    }

}
