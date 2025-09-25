# CCRM - My Java Project Submission

This is my project for the Java course. It's a console application built from scratch to manage student and course records. It demonstrates a wide range of Java SE features, from core OOP principles to modern file I/O.

---

## Project Overview & How to Run

This application allows a user to import data from CSV files, enroll students, assign grades, view a transcript with a calculated GPA, and create timestamped backups.

### Prerequisites
* **Java Development Kit (JDK) 17 or newer.**

### How to Run
1.  **Compile:** Open a terminal in the project's root folder and run this command. It creates an `out` folder with the compiled code.
    ```sh
    javac -d out -sourcepath src src/edu/ccrm/Main.java
    ```
2.  **Run:** After compiling, run the main class with this command:
    ```sh
    java -ea -cp out edu.ccrm.Main
    ```

---

## Evolution of Java

Here are a few major milestones in Java's history that I learned about:
* **1995:** Java 1.0 was first released.
* **2004:** Java 5 was a huge update that added key features like Generics and Enums.
* **2014:** Java 8 was another massive release, introducing Lambda Expressions and the Stream API, which I used in this project.
* **2018-Present:** Java moved to a faster 6-month release cycle, with Long-Term Support (LTS) versions for stability.

---

## Java ME vs. SE vs. EE Comparison

* **Java SE (Standard Edition):** For normal desktop and server apps. **This is what I used for this project.**
* **Java EE (Enterprise Edition):** For large-scale, web-based business applications.
* **Java ME (Micro Edition):** For small, resource-limited devices like sensors.

---

## JDK, JRE, and JVM Explained

Here's my understanding of the Java architecture:
* **JVM (Java Virtual Machine):** The engine that runs the compiled Java bytecode. This is what makes Java "platform-independent."
* **JRE (Java Runtime Environment):** What you need to *run* a Java application. It includes the JVM and core libraries.
* **JDK (Java Development Kit):** What you need to *develop* Java applications. It includes the JRE plus the compiler (`javac`) and other tools.

---

## Windows & Eclipse Setup Steps

### Windows JDK Installation
1.  Download the JDK installer for Windows from the Oracle or OpenJDK website.
2.  Run the `.exe` installer and follow the setup wizard.
3.  Set the `JAVA_HOME` environment variable to your JDK installation path (e.g., `C:\Program Files\Java\jdk-21`).
4.  Update the `Path` environment variable by adding a new entry: `%JAVA_HOME%\bin`.
5.  Verify the installation by opening a new Command Prompt and running `java -version` and `javac -version`.

    ![alt text](1-jdk-verification.png.png)

### Eclipse Project Setup
1.  Open Eclipse and go to `File > New > Java Project`.
2.  Enter a project name (e.g., `CCRM-Project`).
3.  Ensure the correct JRE is selected.
4.  Click "Finish".
5.  Right-click on the `src` folder to create new packages (`edu.ccrm.domain`, `edu.ccrm.cli`, etc.).
6.  Right-click on a package to create new classes and copy the code into the files.


---

## Mapping of Syllabus Topics to My Code

This section shows where each of the required concepts can be found in my project.

* **Inheritance & Abstraction:** Found in `domain/Student.java` which extends the abstract `domain/Person.java`.
* **Polymorphism:** The `getDetails()` method is an abstract method in `Person` that is implemented by the `Student` subclass.
* **Encapsulation:** All domain classes use `private` fields with `public` getters and setters.
* **Singleton Design Pattern:** Implemented in `service/AppConfig.java` to ensure only one instance of the configuration exists.
* **Builder Design Pattern:** Implemented as a static nested class in `domain/Course.java` for clean object creation.
* **NIO.2 File I/O:** Used in `io/DataImporter.java` and `io/BackupUtil.java` with `java.nio.file.Path` and `java.nio.file.Files`.
* **Streams & Lambdas:** Used for searching in `CourseService.java` and for processing files in `DataImporter.java`.
* **Enums with Fields:** `domain/Grade.java` contains a `double` field for the grade points.
* **Custom Exceptions:** `exception/StudentNotFoundException.java` is a custom checked exception.
* **Recursion:** The `calculateDirectorySize` method in `io/BackupUtil.java` uses `Files.walk`, which operates recursively.
* **Date/Time API:** `io/BackupUtil.java` uses `LocalDateTime` to create a timestamp for backup folder names.

---

## Notes on Assertions and Sample Commands

### Enabling Assertions
The `-ea` flag in the run command is required to enable assertions. This allows the validation checks within the `Course.Builder` class to execute. If you run the program without `-ea`, the `assert` statements will be ignored.

### Sample Command Workflow
A typical workflow in the application would be:
1.  Start the program.
2.  Enter `6` to **Import Data from Files**.
3.  Enter `1` to **List All Students** and see the imported data.
4.  Enter `2` to **List All Courses**.
5.  Enter `3` to **Enroll Student in Course**, providing a student ID (e.g., `S101`) and a course code (e.g., `MA201`).
6.  Enter `5` to **Show Student Transcript** for that student.