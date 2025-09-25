package edu.ccrm.domain;

/**
 * Enum representing the academic semesters in a year.
 */
public enum Semester {

    SPRING("Sp"), 
    SUMMER("Su"), 
    FALL("Fa");

    private final String code;

    Semester(String code) {
        this.code = code;
    }

    /** Returns the short code for the semester (e.g., "Sp" for SPRING). */
    public String getCode() {
        return code;
    }

    /** Utility: find a semester by its short code. Returns null if not found. */
    public static Semester fromCode(String code) {
        for (Semester sem : values()) {
            if (sem.code.equalsIgnoreCase(code)) {
                return sem;
            }
        }
        return null;
    }
}
