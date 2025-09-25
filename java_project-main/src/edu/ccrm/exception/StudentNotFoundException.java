package edu.ccrm.exception;

// A custom checked exception.
public class StudentNotFoundException extends Exception {
    public StudentNotFoundException(String message) {
        super(message);
    }
}