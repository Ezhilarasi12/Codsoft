import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Course class to store course information
class Course {
    String code;
    String title;
    String description;
    int capacity;
    int availableSlots;
    String schedule;

    public Course(String code, String title, String description, int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.availableSlots = capacity;
        this.schedule = schedule;
    }
}

// Student class to store student information
class Student {
    String id;
    String name;
    ArrayList<Course> registeredCourses;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }
}

public class Database {
    static Map<String, Course> courseDatabase = new HashMap<>();
    static Map<String, Student> studentDatabase = new HashMap<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Initialize courses
        courseDatabase.put("CS101", new Course("CS101", "Introduction to Computer Science", "Introduction to programming concepts", 30, "Monday, Wednesday, Friday"));
        courseDatabase.put("MATH102", new Course("MATH102", "Calculus", "Introduction to calculus concepts", 25, "Tuesday, Thursday"));
        courseDatabase.put("ENGL103", new Course("ENGL103", "English Composition", "Introduction to writing and composition", 20, "Monday, Wednesday"));

        while (true) {
            System.out.println("1. Display available courses");
            System.out.println("2. Register for a course");
            System.out.println("3. Drop a course");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            switch (choice) {
                case 1:
                    displayAvailableCourses();
                    break;
                case 2:
                    registerForCourse();
                    break;
                case 3:
                    dropCourse();
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Method to display available courses
    public static void displayAvailableCourses() {
        System.out.println("Available Courses:");
        for (Course course : courseDatabase.values()) {
            System.out.println("Course Code: " + course.code);
            System.out.println("Title: " + course.title);
            System.out.println("Description: " + course.description);
            System.out.println("Available Slots: " + course.availableSlots);
            System.out.println("Schedule: " + course.schedule);
            System.out.println();
        }
    }

    // Method to register for a course
    public static void registerForCourse() {
        System.out.print("Enter your student ID: ");
        String studentId = scanner.nextLine();
        System.out.print("Enter the course code: ");
        String courseCode = scanner.nextLine();

        if (studentDatabase.containsKey(studentId)) {
            Student student = studentDatabase.get(studentId);
            if (courseDatabase.containsKey(courseCode)) {
                Course course = courseDatabase.get(courseCode);
                if (course.availableSlots > 0) {
                    student.registeredCourses.add(course);
                    course.availableSlots--;
                    System.out.println("Course registered successfully.");
                } else {
                    System.out.println("Course is full. Please try another course.");
                }
            } else {
                System.out.println("Course not found. Please try again.");
            }
        } else {
            System.out.print("Enter your name: ");
            String name = scanner.nextLine();
            Student student = new Student(studentId, name);
            studentDatabase.put(studentId, student);
            if (courseDatabase.containsKey(courseCode)) {
                Course course = courseDatabase.get(courseCode);
                if (course.availableSlots > 0) {
                    student.registeredCourses.add(course);
                    course.availableSlots--;
                    System.out.println("Course registered successfully.");
                } else {
                    System.out.println("Course is full. Please try another course.");
                }
            } else {
                System.out.println("Course not found. Please try again.");
            }
        }
    }

    // Method to drop a course
    public static void dropCourse() {
        System.out.print("Enter your student ID: ");
        String studentId = scanner.nextLine();
        System.out.print("Enter the course code: ");
        String courseCode = scanner.nextLine();

        if (studentDatabase.containsKey(studentId)) {
            Student student = studentDatabase.get(studentId);
            if (student.registeredCourses.removeIf(course -> course.code.equals(courseCode))) {
                courseDatabase.get(courseCode).availableSlots++;
                System.out.println("Course dropped successfully.");
            } else {
                System.out.println("You are not registered for this course.");
            }
        } else {
            System.out.println("Student not found. Please try again.");
        }
    }
}