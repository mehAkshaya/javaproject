package edu.ccrm.domain;

/**
 * Enum to represent academic grades with associated GPA values.
 */
public enum Grade {

    S(10.0), 
    A(9.0), 
    B(8.0), 
    C(7.0), 
    F(0.0), 
    NA(0.0);   // NA = Not Assigned / Pending

    private final double gpaValue;

    Grade(double gpaValue) {
        this.gpaValue = gpaValue;
    }

    /** Returns the grade's GPA weight */
    public double getGpaValue() {
        return gpaValue;
    }

    /** Utility: check if the grade counts as a passing grade */
    public boolean isPassing() {
        return this != F && this != NA;
    }
}
