package edu.ccrm.domain;

import java.util.HashMap;
import java.util.Map;

// Student class inherits from Person.
public class Student extends Person {
    // Using a Map to store enrolled course codes and their grades.
    private Map<String, Grade> enrolledCourses;

    public Student(String id, String fullName) {
        // 'super' calls the constructor of the parent class (Person).
        super(id, fullName);
        this.enrolledCourses = new HashMap<>();
    }

    public void enrollCourse(String courseCode) {
        // Default grade is NA (Not Available) until one is assigned.
        enrolledCourses.put(courseCode, Grade.NA);
    }
    
    public void assignGrade(String courseCode, Grade grade) {
        if (enrolledCourses.containsKey(courseCode)) {
            enrolledCourses.put(courseCode, grade);
        } else {
            System.out.println("Error: Student not enrolled in " + courseCode);
        }
    }

    public Map<String, Grade> getEnrolledCourses() {
        return enrolledCourses;
    }

    // This overrides the abstract method from Person.
    @Override
    public String getDetails() {
        return "Student ID: " + id + ", Name: " + fullName;
    }
}