package edu.ccrm.domain;

// Represents a course. I used the Builder pattern here because it was required.
public class Course {
    private String code;
    private String title;
    private int credits;
    private Semester semester;

    // Private constructor so you have to use the Builder.
    private Course(Builder builder) {
        this.code = builder.code;
        this.title = builder.title;
        this.credits = builder.credits;
        this.semester = builder.semester;
    }

    // Getters
    public String getCode() { return code; }
    public String getTitle() { return title; }
    public int getCredits() { return credits; }
    public Semester getSemester() { return semester; }

    @Override
    public String toString() {
        return String.format("Course[%s: %s, Credits: %d, Semester: %s]", code, title, credits, semester);
    }
    
    // A static nested class for the Builder pattern.
    public static class Builder {
        private String code;
        private String title;
        private int credits;
        private Semester semester;

        public Builder(String code, String title) {
            this.code = code;
            this.title = title;
        }

        public Builder credits(int credits) {
            this.credits = credits;
            return this; // Allows for chaining methods like new Course.Builder(...).credits(...).build()
        }

        public Builder semester(Semester semester) {
            this.semester = semester;
            return this;
        }

        public Course build() {
            // Some basic validation before creating the object.
            assert code != null && !code.isEmpty() : "Course code cannot be empty";
            assert title != null && !title.isEmpty() : "Course title cannot be empty";
            return new Course(this);
        }
    }
}