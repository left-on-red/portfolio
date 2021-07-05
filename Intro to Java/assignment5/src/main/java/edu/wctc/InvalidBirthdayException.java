package edu.wctc;

public class InvalidBirthdayException extends Exception {
    public InvalidBirthdayException(int year, int month, int day) {
        super(String.format("the given year of \"%d/%d/%d\" is invalid", month, day, year));
    }
}
