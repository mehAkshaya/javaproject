package edu.ccrm.domain;

/**
 * Represents a university course.
 * Uses a custom builder (CourseBuilder) for flexible object creation.
 */
public class Course {

    private final String code;
    private final String name;
    private final int creditHours;
    private final Semester term;

    // Private constructor to enforce the builder usage
    private Course(CourseBuilder builder) {
        this.code = builder.code;
        this.name = builder.name;
        this.creditHours = builder.creditHours;
        this.term = builder.term;
    }

    // Getters
    public String getCode() { return code; }
    public String getName() { return name; }
    public int getCreditHours() { return creditHours; }
    public Semester getTerm() { return term; }

    @Override
    public String toString() {
        return "[Course: " + code + " | " + name +
               " | Credits=" + creditHours +
               " | Term=" + term + "]";
    }

    /**
     * Builder class for Course
     */
    public static class CourseBuilder {
        private final String code;
        private final String name;
        private int creditHours = 0;
        private Semester term = Semester.FALL; // default

        public CourseBuilder(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public CourseBuilder creditHours(int credits) {
            this.creditHours = credits;
            return this;
        }

        public CourseBuilder term(Semester sem) {
            this.term = sem;
            return this;
        }

        public Course build() {
            validate();
            return new Course(this);
        }

        private void validate() {
            if (code == null || code.isBlank()) {
                throw new IllegalStateException("Course code must not be empty.");
            }
            if (name == null || name.isBlank()) {
                throw new IllegalStateException("Course name must not be empty.");
            }
            if (creditHours < 0) {
                throw new IllegalStateException("Credits cannot be negative.");
            }
        }
    }
}
