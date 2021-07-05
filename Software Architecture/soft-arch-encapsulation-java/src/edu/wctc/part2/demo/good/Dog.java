package edu.wctc.part2.demo.good;

public class Dog {
    private int age;

    public Dog(int age) {
        // Constructor calls the setter to perform its validation.
        // We haven't repeated ourselves, yay!
        setAge(age);
    }

    public void chaseMailCarrier() {
        startRunning();
        navigateToMailCarrier();
        closeGapToMailCarrier();
    }

    /*
    Now these methods can only be called from the Dog class.
    Other parts of our program can't mess up the order,
    because we only allow them to call chaseMailCarrier().
     */
    private void closeGapToMailCarrier() {
    }

    private void navigateToMailCarrier() {
    }

    public void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException(" age can't be negative");
        }
        this.age = age;
    }

    private void startRunning() {
    }
}
