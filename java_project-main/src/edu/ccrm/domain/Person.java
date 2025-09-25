package edu.ccrm.domain;

// Used inheritance to avoid repeating fields for Student and Instructor.
public abstract class Person {
    protected String id;
    protected String fullName;
    
    public Person(String id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }
    
    // An abstract method to be implemented by child classes.
    public abstract String getDetails();

    // Getters and Setters
    public String getId() { return id; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
}