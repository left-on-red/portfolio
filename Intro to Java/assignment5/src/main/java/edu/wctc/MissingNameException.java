package edu.wctc;

public class MissingNameException extends Exception {
    public MissingNameException(String nameType) {
        super(String.format("name-type of \"%s\" may not be blank", nameType));
    }
}
