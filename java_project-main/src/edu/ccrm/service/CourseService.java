package edu.ccrm.service;

import edu.ccrm.domain.Course;
import edu.ccrm.exception.CourseNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// This class manages the courses.
public class CourseService {
    private List<Course> courses = new ArrayList<>();

    public void addCourse(Course course) {
        courses.add(course);
    }

    public List<Course> getAllCourses() {
        return courses;
    }

    public Course findCourseByCode(String code) throws CourseNotFoundException {
        for (Course c : courses) {
            if (c.getCode().equalsIgnoreCase(code)) {
                return c;
            }
        }
        throw new CourseNotFoundException("Course with code '" + code + "' was not found.");
    }

    // Used a Stream here as required by the project to filter courses.
    public List<Course> searchCourses(String searchTerm) {
        return courses.stream()
            .filter(course -> course.getTitle().toLowerCase().contains(searchTerm.toLowerCase()))
            .collect(Collectors.toList());
    }
}