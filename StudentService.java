package main.java.com.khanhvq.service;

import main.java.com.khanhvq.model.Student;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class StudentService {
    private List<Student> students;
    private final int MAX_STUDENTS = 100;

    public StudentService() {
        this.students = new ArrayList<>();
    }

    public void addStudent(int studentId, String fullName, double gpa) throws IllegalArgumentException {
        // Input validation [cite: 15]
        if (students.size() >= MAX_STUDENTS) {
            throw new IllegalArgumentException("Maximum number of students reached (" + MAX_STUDENTS + ")");
        }
        if (isStudentIdExists(studentId)) {
            throw new IllegalArgumentException("Student ID " + studentId + " already exists.");
        }
        if (fullName == null || fullName.trim().isEmpty() || fullName.length() > 50) {
            throw new IllegalArgumentException("Full Name cannot be empty and must be max 50 characters.");
        }
        if (gpa < 0.0 || gpa > 4.0) {
            throw new IllegalArgumentException("GPA must be between 0.0 and 4.0.");
        }

        Student newStudent = new Student(studentId, fullName, gpa);
        students.add(newStudent);
        System.out.println("Student added successfully: " + newStudent.getFullName());
    }

    private boolean isStudentIdExists(int studentId) {
        for (Student s : students) {
            if (s.getStudentId() == studentId) {
                return true;
            }
        }
        return false;
    }

    // Delete a student: Remove a student by ID [cite: 16]
    public boolean deleteStudent(int studentId) {
        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            Student student = iterator.next();
            if (student.getStudentId() == studentId) {
                iterator.remove();
                System.out.println("Student with ID " + studentId + " deleted successfully.");
                return true;
            }
        }
        System.out.println("Student with ID " + studentId + " not found.");
        return false;
    }

    public List<Student> searchStudents(String nameQuery) {
        if (nameQuery == null || nameQuery.trim().isEmpty()) {
            System.out.println("Search query cannot be empty.");
            return new ArrayList<>();
        }
        String lowerCaseQuery = nameQuery.toLowerCase();
        return students.stream()
                .filter(s -> s.getFullName().toLowerCase().contains(lowerCaseQuery))
                .collect(Collectors.toList());
    }

    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students to display.");
            return;
        }
        System.out.println("\n--- All Students ---");
        System.out.printf("%-10s %-30s %-5s\n", "ID", "Full Name", "GPA");
        System.out.println("--------------------------------------------------");
        for (Student student : students) {
            System.out.printf("%-10d %-30s %-5.2f\n",
                    student.getStudentId(),
                    student.getFullName(),
                    student.getGpa());
        }
        System.out.println("--------------------------------------------------\n");
    }
}
