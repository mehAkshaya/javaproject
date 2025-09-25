package edu.ccrm.cli;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.Grade;
import edu.ccrm.domain.Student;
import edu.ccrm.exception.CourseNotFoundException;
import edu.ccrm.exception.StudentNotFoundException;
import edu.ccrm.io.BackupUtil;
import edu.ccrm.io.DataImporter;
import edu.ccrm.service.AppConfig;
import edu.ccrm.service.CourseService;
import edu.ccrm.service.StudentService;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class ConsoleMenuManager {

    private final Scanner input;
    private final StudentService studentManager;
    private final CourseService courseManager;
    private final DataImporter dataLoader;

    public ConsoleMenuManager() {
        this.input = new Scanner(System.in);
        this.studentManager = new StudentService();
        this.courseManager = new CourseService();
        this.dataLoader = new DataImporter();
    }

    public void start() {
        boolean exit = false;

        do {
            displayMenu();
            System.out.print("Choose an option: ");
            int choice = readInt();

            switch (choice) {
                case 1 -> showStudents();
                case 2 -> showCourses();
                case 3 -> enrollStudentInCourse();
                case 4 -> assignGradeToStudent();
                case 5 -> printTranscript();
                case 6 -> loadDataFromFiles();
                case 7 -> backupData();
                case 9 -> exit = true;
                default -> System.out.println("❌ Invalid selection. Try again.");
            }
        } while (!exit);

        System.out.println("Application closed.");
    }

    private void displayMenu() {
        System.out.println("\n=== Student & Course Management ===");
        System.out.println("1. View All Students");
        System.out.println("2. View All Courses");
        System.out.println("3. Enroll a Student");
        System.out.println("4. Record a Grade");
        System.out.println("5. Generate Transcript");
        System.out.println("6. Import Data (CSV)");
        System.out.println("7. Create Backup");
        System.out.println("9. Exit");
    }

    private void showStudents() {
        System.out.println("\n--- Registered Students ---");
        studentManager.getAllStudents()
                      .forEach(student -> System.out.println(student.getDetails()));
    }

    private void showCourses() {
        System.out.println("\n--- Available Courses ---");
        List<Course> courses = courseManager.getAllCourses();
        courses.forEach(System.out::println);
    }

    private void enrollStudentInCourse() {
        System.out.print("Enter Student ID: ");
        String studentId = input.nextLine().trim();

        System.out.print("Enter Course Code: ");
        String courseCode = input.nextLine().trim();

        try {
            Student s = studentManager.findStudentById(studentId);
            Course c = courseManager.findCourseByCode(courseCode);
            s.enrollCourse(c.getCode());
            System.out.println("✅ Enrollment completed.");
        } catch (StudentNotFoundException | CourseNotFoundException e) {
            System.out.println("⚠️ " + e.getMessage());
        }
    }

    private void assignGradeToStudent() {
        try {
            System.out.print("Student ID: ");
            String studentId = input.nextLine().trim();

            Student s = studentManager.findStudentById(studentId);

            System.out.print("Course Code: ");
            String courseCode = input.nextLine().trim();

            System.out.print("Enter Grade (S, A, B, C, F): ");
            String gradeInput = input.nextLine().trim().toUpperCase();

            Grade grade = Grade.valueOf(gradeInput);
            s.assignGrade(courseCode, grade);

            System.out.println("🎓 Grade recorded successfully.");
        } catch (StudentNotFoundException e) {
            System.out.println("⚠️ " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("⚠️ Invalid grade format.");
        }
    }

    private void printTranscript() {
        System.out.print("Enter Student ID: ");
        String studentId = input.nextLine().trim();

        try {
            String transcript = studentManager.generateTranscript(studentId, courseManager);
            System.out.println(transcript);
        } catch (StudentNotFoundException e) {
            System.out.println("⚠️ " + e.getMessage());
        }
    }

    private void loadDataFromFiles() {
        String dataFolder = AppConfig.getInstance().getProperty("data.folder");
        dataLoader.importStudents(dataFolder + "/students_import.csv", studentManager);
        dataLoader.importCourses(dataFolder + "/courses_import.csv", courseManager);
        System.out.println("📂 Data import completed.");
    }

    private void backupData() {
        String dataFolder = AppConfig.getInstance().getProperty("data.folder");
        String backupFolder = AppConfig.getInstance().getProperty("backup.folder");

        BackupUtil util = new BackupUtil();
        util.createBackup(dataFolder, backupFolder);

        try {
            long size = BackupUtil.calculateDirectorySize(Paths.get(backupFolder));
            System.out.printf("💾 Backup size: %.2f KB%n", size / 1024.0);
        } catch (IOException e) {
            System.out.println("⚠️ Unable to calculate backup size.");
        }
    }

    private int readInt() {
        while (!input.hasNextInt()) {
            System.out.println("⚠️ Please enter a number.");
            input.next();
        }
        return input.nextInt();
    }
}
