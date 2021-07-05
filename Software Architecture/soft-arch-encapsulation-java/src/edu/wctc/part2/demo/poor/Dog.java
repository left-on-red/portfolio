package edu.wctc.part2.demo.poor;

public class Dog {
    private int age;

    public Dog(int age) {
        // DRY violation! The validation occurs here and in the setter.
        if (age < 0) {
            throw new IllegalArgumentException(" age can't be negative");
        }
        this.age = age;
    }

    /*
    These methods must be called in this order:

    startRunning();
    navigateToMailCarrier();
    closeGapToMailCarrier();

    Can we enforce that?
     */
    public void closeGapToMailCarrier() {
    }

    public void navigateToMailCarrier() {
    }

    public void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException(" age can't be negative");
        }
        this.age = age;
    }

    public void startRunning() {
    }


}
