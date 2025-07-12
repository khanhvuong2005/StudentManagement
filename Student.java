package main.java.com.khanhvq.model;

public class Student {
    private int studentId;
    private String fullName; // Full Name (String, max 50 characters) [cite: 11]
    private double gpa; // GPA (double, range 0.0-4.0) [cite: 12]

    // Constructor [cite: 20]
    public Student(int studentId, String fullName, double gpa) {
        this.studentId = studentId;
        this.fullName = fullName;
        this.gpa = gpa;
    }

    // Getters [cite: 20]
    public int getStudentId() {
        return studentId;
    }

    public String getFullName() {
        return fullName;
    }

    public double getGpa() {
        return gpa;
    }

    // Setters (if needed, but for studentId and fullName, usually set via
    // constructor) [cite: 20]
    // You might want a setter for GPA if it can change.
    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return String.format("ID: %d, Name: %s, GPA: %.2f", studentId, fullName, gpa);
    }
}
