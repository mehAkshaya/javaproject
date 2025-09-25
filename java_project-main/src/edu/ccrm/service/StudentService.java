package edu.ccrm.service;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.Student;
import edu.ccrm.domain.Grade;
import edu.ccrm.exception.StudentNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// This class holds the main list of students and has methods to work with them.
public class StudentService {
    private List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
    }

    public List<Student> getAllStudents() {
        return students;
    }

    // A simple loop to find a student by their ID.
    public Student findStudentById(String id) throws StudentNotFoundException {
        for (Student s : students) {
            if (s.getId().equalsIgnoreCase(id)) {
                return s;
            }
        }
        // Throw a custom exception if not found.
        throw new StudentNotFoundException("Student with ID '" + id + "' was not found.");
    }
    
    public String generateTranscript(String studentId, CourseService courseService) throws StudentNotFoundException {
        Student student = findStudentById(studentId);
        StringBuilder sb = new StringBuilder();
        
        sb.append("\n--- TRANSCRIPT FOR " + student.getFullName() + " (" + student.getId() + ") ---\n");
        double totalPoints = 0;
        int totalCredits = 0;

        for (Map.Entry<String, Grade> entry : student.getEnrolledCourses().entrySet()) {
            String courseCode = entry.getKey();
            Grade grade = entry.getValue();
            try {
                Course course = courseService.findCourseByCode(courseCode);
                sb.append(String.format("%s: %s - Grade: %s\n", course.getCode(), course.getTitle(), grade));
                totalPoints += grade.getPoints() * course.getCredits();
                totalCredits += course.getCredits();
            } catch (Exception e) {
                 sb.append(courseCode + ": Course details not found.\n");
            }
        }

        if (totalCredits > 0) {
            double gpa = totalPoints / totalCredits;
            sb.append(String.format("\nGPA: %.2f\n", gpa));
        } else {
            sb.append("\nNo courses with grades recorded.\n");
        }
        
        return sb.toString();
    }
}