package edu.ccrm.exception;

// A custom checked exception.
public class CourseNotFoundException extends Exception {
    public CourseNotFoundException(String message) {
        super(message);
    }
}