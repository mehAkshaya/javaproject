package edu.ccrm.cli;

import edu.ccrm.domain.Grade;
import edu.ccrm.exception.CourseNotFoundException;
import edu.ccrm.exception.StudentNotFoundException;
import edu.ccrm.io.BackupUtil;
import edu.ccrm.io.DataImporter;
import edu.ccrm.service.AppConfig;
import edu.ccrm.service.CourseService;
import edu.ccrm.service.StudentService;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

// Handles all the console menu logic and user input.
public class MenuHandler {
    private final Scanner scanner = new Scanner(System.in);
    private final StudentService studentService = new StudentService();
    private final CourseService courseService = new CourseService();
    private final DataImporter importer = new DataImporter();

    public void run() {
        // A do-while loop for the main menu.
        boolean exit = false;
        do {
            printMenu();
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Important: consume the newline character after reading an int!

            switch (choice) {
                case 1:
                    listAllStudents();
                    break;
                case 2:
                    listAllCourses();
                    break;
                case 3:
                    enrollStudent();
                    break;
                case 4:
                    assignGrade();
                    break;
                case 5:
                    showTranscript();
                    break;
                case 6:
                    importData();
                    break;
                case 7:
                    createBackup();
                    break;
                case 9:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (!exit);
        System.out.println("Exiting application.");
    }

    private void printMenu() {
        System.out.println("\n===== CCRM Menu =====");
        System.out.println("1. List All Students");
        System.out.println("2. List All Courses");
        System.out.println("3. Enroll Student in Course");
        System.out.println("4. Assign Grade to Student");
        System.out.println("5. Show Student Transcript");
        System.out.println("6. Import Data from Files");
        System.out.println("7. Create Backup");
        System.out.println("9. Exit");
    }
    
    private void listAllStudents() {
        System.out.println("\n--- Students ---");
        // Using a simple for-each loop here.
        for (var student : studentService.getAllStudents()) {
            System.out.println(student.getDetails());
        }
    }

    private void listAllCourses() {
        System.out.println("\n--- Courses ---");
        courseService.getAllCourses().forEach(System.out::println); // Used a lambda here to show I know it.
    }

    private void enrollStudent() {
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();
        System.out.print("Enter Course Code: ");
        String courseCode = scanner.nextLine();
        
        try {
            var student = studentService.findStudentById(studentId);
            var course = courseService.findCourseByCode(courseCode); // Check if course exists
            student.enrollCourse(course.getCode());
            System.out.println("Enrollment successful!");
        } catch (StudentNotFoundException | CourseNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private void assignGrade() {
        try {
            System.out.print("Enter Student ID: ");
            String studentId = scanner.nextLine();
            var student = studentService.findStudentById(studentId);

            System.out.print("Enter Course Code: ");
            String courseCode = scanner.nextLine();
            
            System.out.print("Enter Grade (S, A, B, C, F): ");
            String gradeStr = scanner.nextLine().toUpperCase();
            Grade grade = Grade.valueOf(gradeStr); // Converts string to enum
            
            student.assignGrade(courseCode, grade);
            System.out.println("Grade assigned successfully.");

        } catch (StudentNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            // This catches errors if the user types an invalid grade
            System.out.println("Error: Invalid grade entered.");
        }
    }
    
    private void showTranscript() {
        System.out.print("Enter Student ID to generate transcript: ");
        String studentId = scanner.nextLine();
        try {
            String transcript = studentService.generateTranscript(studentId, courseService);
            System.out.println(transcript);
        } catch (StudentNotFoundException e) {
             System.out.println("Error: " + e.getMessage());
        }
    }

    private void importData() {
        String dataFolder = AppConfig.getInstance().getProperty("data.folder");
        importer.importStudents(dataFolder + "/students_import.csv", studentService);
        importer.importCourses(dataFolder + "/courses_import.csv", courseService);
    }
    
    private void createBackup() {
        String dataFolder = AppConfig.getInstance().getProperty("data.folder");
        String backupFolder = AppConfig.getInstance().getProperty("backup.folder");
        BackupUtil backupUtil = new BackupUtil();
        backupUtil.createBackup(dataFolder, backupFolder);
        
        // Bonus: Show size of backup dir using the recursive util
        try {
            long size = BackupUtil.calculateDirectorySize(Paths.get(backupFolder));
            System.out.printf("Total backup size: %.2f KB\n", size / 1024.0);
        } catch(IOException e) {
            System.out.println("Could not calculate backup size.");
        }
    }
}