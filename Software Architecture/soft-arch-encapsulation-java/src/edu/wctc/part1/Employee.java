package edu.wctc.part1;

import java.time.LocalDate;

/**
 * Fix the code in this class to do PROPERTY encapsulation correctly. Also
 * consider if any of the properties should be mandatory and use a constructor
 * to enforce that. Review the tips in the document Encapsulation Checklist if
 * needed.
 */
public class Employee {

    private String firstName;
    private String lastName;
    private String ssn;
    private boolean metWithHr = false;
    private boolean metDeptStaff = false;
    private boolean reviewedDeptPolicies = false;
    private boolean movedIn = false;
    private String cubeId;
    private LocalDate orientationDate;

    public Employee(String firstName, String lastName, String ssn, boolean metWithHr, boolean metDeptStaff, boolean reviewedDeptPolicies, boolean movedIn, String cubeId, LocalDate orientationDate) {
        setFirstName(firstName);
        setLastName(lastName);
        setSsn(ssn);
        setMetWithHr(metWithHr);
        setMetDeptStaff(metDeptStaff);
        setReviewedDeptPolicies(reviewedDeptPolicies);
        setMovedIn(movedIn);
        setCubeId(cubeId);
        setOrientationDate(orientationDate);
    }

    public String getFirstName() { return this.firstName; }
    public void setFirstName(String firstName) {
        if (firstName == null || firstName.length() == 0) { throw new IllegalArgumentException("invalid value for firstName"); }
        this.firstName = firstName;
    }

    public String getLastName() { return this.lastName; }
    public void setLastName(String lastName) {
        if (lastName == null || lastName.length() == 0) { throw new IllegalArgumentException("invalid value for lastName"); }
        this.lastName = lastName;
    }

    public String getSsn() { return this.ssn; }
    public void setSsn(String ssn) {
        if (ssn.length() != 9) { throw new IllegalArgumentException("ssn needs to be 9 characters"); }
        this.ssn = ssn;
    }

    public String getCubeId() { return this.cubeId; }
    public void setCubeId(String cubeId) {
        if (cubeId == null || cubeId.length() == 0) { throw new IllegalArgumentException("invalid value for cubeId"); }
    }

    public boolean getMetWithHr() { return this.metWithHr; }
    public void setMetWithHr(boolean metWithHr) { this.metWithHr = metWithHr; }

    public boolean getMetDeptStaff() { return this.metDeptStaff; }
    public void setMetDeptStaff(boolean metDeptStaff) { this.metDeptStaff = metDeptStaff; }

    public boolean getReviewedDeptPolicies() { return this.reviewedDeptPolicies; }
    public void setReviewedDeptPolicies(boolean reviewedDeptPolicies) { this.reviewedDeptPolicies = reviewedDeptPolicies; }

    public boolean getMovedIn() { return this.movedIn; }
    public void setMovedIn(boolean movedIn) { this.movedIn = movedIn; }

    public LocalDate getOrientationDate() { return this.orientationDate; }
    public void setOrientationDate(LocalDate orientationDate) {
        if (orientationDate.compareTo(LocalDate.now()) < 0) { throw new IllegalArgumentException("orientationDate can't be less than the current Date"); }
        this.orientationDate = orientationDate;
    }
}
