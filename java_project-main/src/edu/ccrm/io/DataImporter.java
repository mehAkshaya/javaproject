package edu.ccrm.io;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.Semester;
import edu.ccrm.domain.Student;
import edu.ccrm.service.CourseService;
import edu.ccrm.service.StudentService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

// Uses modern NIO.2 to read data from files, as required.
public class DataImporter {
    public void importStudents(String filename, StudentService studentService) {
        Path path = Paths.get(filename);
        try {
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                if (line.trim().isEmpty() || line.startsWith("id")) continue; // Skip header/empty lines
                String[] parts = line.split(",");
                studentService.addStudent(new Student(parts[0], parts[1]));
            }
            System.out.println("Successfully imported " + studentService.getAllStudents().size() + " students.");
        } catch (IOException e) {
            System.out.println("Error reading students file: " + e.getMessage());
        }
    }
    
    public void importCourses(String filename, CourseService courseService) {
         Path path = Paths.get(filename);
        try (var lines = Files.lines(path)) { // Using try-with-resources and streams
             lines.filter(line -> !line.startsWith("code"))
                .map(line -> line.split(","))
                .forEach(parts -> {
                    Course course = new Course.Builder(parts[0], parts[1])
                        .credits(Integer.parseInt(parts[2]))
                        .semester(Semester.valueOf(parts[3].toUpperCase()))
                        .build();
                    courseService.addCourse(course);
                });
             System.out.println("Successfully imported courses.");
        } catch (IOException e) {
            System.out.println("Error reading courses file: " + e.getMessage());
        }
    }
}