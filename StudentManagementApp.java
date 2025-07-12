package main.java.com.khanhvq;

import main.java.com.khanhvq.model.Student;
import main.java.com.khanhvq.service.StudentService;

import java.util.List;
import java.util.Scanner;

public class StudentManagementApp {
    private StudentService studentService;
    private Scanner scanner;

    public StudentManagementApp() {
        this.studentService = new StudentService();
        this.scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        StudentManagementApp app = new StudentManagementApp();
        app.run();
    }

    public void run() {
        int choice;
        do {
            displayMenu();
            System.out.print("Enter your choice: ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        addStudentHandler();
                        break;
                    case 2:
                        deleteStudentHandler();
                        break;
                    case 3:
                        searchStudentHandler();
                        break;
                    case 4:
                        studentService.displayAllStudents();
                        break;
                    case 0:
                        System.out.println("Exiting application. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                choice = -1;
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
                choice = -1;
            }
            System.out.println();
        } while (choice != 0);
        scanner.close();
    }

    private void displayMenu() {
        System.out.println("--- Student Management System ---");
        System.out.println("1. Add New Student");
        System.out.println("2. Delete Student by ID");
        System.out.println("3. Search Student by Name");
        System.out.println("4. Display All Students");
        System.out.println("0. Exit");
    }

    private void addStudentHandler() {
        System.out.println("\n--- Add New Student ---");
        try {
            System.out.print("Enter Student ID (integer): ");
            int id = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter Full Name (max 50 chars): ");
            String name = scanner.nextLine();

            System.out.print("Enter GPA (0.0-4.0): ");
            double gpa = Double.parseDouble(scanner.nextLine());

            studentService.addStudent(id, name, gpa);
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number format for ID or GPA.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void deleteStudentHandler() {
        System.out.println("\n--- Delete Student ---");
        try {
            System.out.print("Enter Student ID to delete: ");
            int id = Integer.parseInt(scanner.nextLine());
            studentService.deleteStudent(id);
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number format for Student ID.");
        }
    }

    private void searchStudentHandler() {
        System.out.println("\n--- Search Student ---");
        System.out.print("Enter partial or full name to search: ");
        String query = scanner.nextLine();
        List<Student> foundStudents = studentService.searchStudents(query);

        if (foundStudents.isEmpty()) {
            System.out.println("No students found matching '" + query + "'.");
        } else {
            System.out.println("\n--- Found Students ---");
            System.out.printf("%-10s %-30s %-5s\n", "ID", "Full Name", "GPA");
            System.out.println("--------------------------------------------------");
            for (Student student : foundStudents) {
                System.out.printf("%-10d %-30s %-5.2f\n",
                        student.getStudentId(),
                        student.getFullName(),
                        student.getGpa());
            }
            System.out.println("--------------------------------------------------");
        }
    }
}
