package edu.wctc;

public class UnknownGenderCodeException extends Exception {
    public UnknownGenderCodeException(char genderCode) {
        super(String.format("the given character code \"%c\" is not known", genderCode));
    }
}
