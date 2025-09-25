package edu.ccrm.domain;

// An enum for grades, as required. It holds the grade point value.
public enum Grade {
    S(10.0), A(9.0), B(8.0), C(7.0), F(0.0), NA(0.0);

    private final double points;

    Grade(double points) {
        this.points = points;
    }

    public double getPoints() {
        return points;
    }
}