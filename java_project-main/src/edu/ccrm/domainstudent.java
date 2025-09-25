package edu.ccrm.domain;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Represents a student in the system.
 * Each student can enroll in courses and receive grades.
 */
public class Student extends Person {

    // Stores the mapping between course codes and grades.
    private final Map<String, Grade> courseGrades;

    public Student(String id, String fullName) {
        super(id, fullName);
        this.courseGrades = new LinkedHashMap<>();
    }

    /** Enrolls the student in a course. Initially, the grade is set to NA. */
    public void registerCourse(String courseCode) {
        courseGrades.putIfAbsent(courseCode, Grade.NA);
    }

    /** Updates the grade of a course if the student is already enrolled. */
    public void setGrade(String courseCode, Grade grade) {
        if (courseGrades.containsKey(courseCode)) {
            courseGrades.put(courseCode, grade);
        } else {
            System.err.println("Grade cannot be assigned. Student is not registered in: " + courseCode);
        }
    }

    /** Provides a read-only view of all enrolled courses and their grades. */
    public Map<String, Grade> getCourseGrades() {
        return Map.copyOf(courseGrades);
    }

    /** Returns basic details about the student. */
    @Override
    public String getDetails() {
        return String.format("Student[ID=%s, Name=%s, Enrolled=%d]", id, fullName, courseGrades.size());
    }
}


    // This overrides the abstract method from Person.
    @Override
    public String getDetails() {
        return "Student ID: " + id + ", Name: " + fullName;
    }
}
