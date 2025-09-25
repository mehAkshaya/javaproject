Campus Course & Records Manager (CCRM) – Java Console Application

This is a console-based Java application developed as part of my Java coursework. Its main goal is to help manage students, courses, and grades while demonstrating a wide range of Java SE concepts and programming practices.

Key Features

Import student and course data directly from CSV files.

Enroll students in selected courses.

Record and update grades.

Generate student transcripts with automatic GPA calculation.

Create backups with timestamped directories.

⚙️ Requirements

Java 17 or higher must be installed.

▶️ How to Run the Program

Compile the source code:

javac -d out -sourcepath src src/edu/ccrm/Main.java


Execute the program with assertions enabled:

java -ea -cp out edu.ccrm.Main

🖥️ Sample Workflow

Start the application.

Import student and course data from CSV files.

Display all students or courses.

Enroll a student in a course.

Assign or update grades.

View the student’s transcript with GPA calculated automatically.

Optionally, create a backup with a timestamped folder.

🚀 Setting Up on Windows + Eclipse

Install JDK on Windows:

Download the latest JDK (Oracle or OpenJDK).

Install and set the JAVA_HOME environment variable.

Add %JAVA_HOME%\bin to the system Path.

Verify installation:

java -version
javac -version


Eclipse Project Setup:

Open Eclipse → File → New → Java Project.

Name the project (e.g., CCRM-Java) and select the installed JDK.

Add the src folder, then create packages (e.g., edu.ccrm.domain, edu.ccrm.cli).

Add classes under the respective packages and paste in the source code.

🧩 How Coursework Topics Are Applied

Abstraction & Inheritance: Student.java extends the abstract class Person.java.

Polymorphism: getDetails() is defined abstract in Person and overridden in Student.

Encapsulation: All fields are private with getter and setter methods.

Builder Pattern: Used in Course.java for structured object creation.

Singleton Pattern: AppConfig.java ensures a single configuration instance.

Enums with State: Grade.java maps letter grades to numeric values.

Custom Exceptions: StudentNotFoundException.java handles missing student scenarios.

NIO.2 File I/O: Managed in DataImporter.java and BackupUtil.java.

Streams & Lambdas: Used for searching courses and processing imported files.

Recursion: Directory size calculation in BackupUtil.java.

Date & Time API: Timestamped backup folders via LocalDateTime.

Assertions: Validation is enforced inside Course.Builder.

📜 Java Evolution Highlights

1995: Java 1.0 released.

2004 (Java 5): Introduced generics, enums, and annotations.

2014 (Java 8): Added lambdas and Streams API (applied in this project).

2018 onwards: Java adopted a six-month release cycle with LTS versions.

🧠 JDK vs JRE vs JVM

JVM: Runs Java bytecode, enabling platform independence.

JRE: Required to run Java programs (includes JVM and core libraries).

JDK: Needed for development (includes JRE, compiler, and additional tools).

🔎 Notes on Assertions

The project uses assert statements for validations, such as ensuring course codes and titles are not empty. To activate assertions, run the program with the -ea flag.

✅ Example Session

Launch the program.

Import students and courses from CSV.

List all students or courses.

Enroll a student (e.g., S101 → MA201).

Assign grades.

View the student’s transcript with GPA.

Optionally, create a backup folder with a timestamp.
