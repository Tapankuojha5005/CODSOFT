import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Course {
    private String code;
    private String title;
    private String description;
    private int capacity;
    private int enrolled;

    public Course(String code, String title, String description, int capacity) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.enrolled = 0;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getAvailableSlots() {
        return capacity - enrolled;
    }

    public boolean enroll() {
        if (enrolled < capacity) {
            enrolled++;
            return true;
        }
        return false;
    }

    public boolean drop() {
        if (enrolled > 0) {
            enrolled--;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("%s: %s\n%s\nCapacity: %d, Available Slots: %d",
                code, title, description, capacity, getAvailableSlots());
    }
}

class Student {
    private String id;
    private String name;
    private List<Course> registeredCourses;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void registerCourse(Course course) {
        if (course.enroll()) {
            registeredCourses.add(course);
            System.out.println("You have Successfully registered for the " + course.getTitle());
        } else {
            System.out.println("Registration failed: No more slots available . ");
        }
    }

    public void dropCourse(Course course) {
        if (registeredCourses.remove(course) && course.drop()) {
            System.out.println("Successfully dropped " + course.getTitle());
        } else {
            System.out.println("Drop failed: You are not registered for this course.");
        }
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }
}


public class studentregistrationform {
    private List<Course> courses;
    private List<Student> students;

    public studentregistrationform() {
        courses = new ArrayList<>();
        students = new ArrayList<>();
        initializeCourses();
    }

    private void initializeCourses() {
        courses.add(new Course("CS5657", "Introduction to Computer Programming(ICP)", "Here you will learn about the basic concepts of programming.", 20));
        courses.add(new Course("CS5005", "Artifical Intelligence And Machine lerning(AI & ML)", "Basic concepts of Artifical intelligence and machine learning.", 40));
        courses.add(new Course("CS2345", "Algorithms and Design", "Fundamentals of algorithms.", 20));
        courses.add(new Course("CS3030", "Web Development", "An introduction to building dynamic websites using HTML, CSS, and JavaScript.", 25));
        courses.add(new Course("CS4040", "Database Systems", "Explore the principles of database design and management.", 35));

    }

    public void displayCourses() {
        System.out.println("\nAvailable Courses:");
        for (Course course : courses) {
            System.out.println(course);
            System.out.println();
        }
    }

    public Student getStudent(String id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }

    public void registerStudent(String id, String name) {
        if (getStudent(id) == null) {
            students.add(new Student(id, name));
            System.out.println("Student registered successfully.");
        } else {
            System.out.println("Student ID already exists.");
        }
    }

    public static void main(String[] args) {
        studentregistrationform system = new studentregistrationform();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Register Student");
            System.out.println("2. Display the courses");
            System.out.println("3. Register for a Course");
            System.out.println("4. Drop a Course");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter The Student ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter Student Name: ");
                    String name = scanner.nextLine();
                    system.registerStudent(id, name);
                    break;

                case 2:
                    system.displayCourses();
                    break;

                case 3:
                    System.out.print("Enter Student ID: ");
                    String regId = scanner.nextLine();
                    Student student = system.getStudent(regId);
                    if (student != null) {
                        system.displayCourses();
                        System.out.print("Enter Course Code to register: ");
                        String courseCode = scanner.nextLine();
                        Course course = system.courses.stream()
                                .filter(c -> c.getCode().equals(courseCode))
                                .findFirst()
                                .orElse(null);
                        if (course != null) {
                            student.registerCourse(course);
                        } else {
                            System.out.println("Course not found.");
                        }
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 4:
                    System.out.print("Enter Student ID: ");
                    String dropId = scanner.nextLine();
                    Student dropStudent = system.getStudent(dropId);
                    if (dropStudent != null) {
                        System.out.println("Registered Courses:");
                        for (Course c : dropStudent.getRegisteredCourses()) {
                            System.out.println(c.getCode() + ": " + c.getTitle());
                        }
                        System.out.print("Enter the Course Code you want to drop: ");
                        String dropCourseCode = scanner.nextLine();
                        Course dropCourse = dropStudent.getRegisteredCourses().stream()
                                .filter(c -> c.getCode().equals(dropCourseCode))
                                .findFirst()
                                .orElse(null);
                        if (dropCourse != null) {
                            dropStudent.dropCourse(dropCourse);
                        } else {
                            System.out.println("You are not registered for this course.");
                        }
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 5:
                    System.out.println("Exiting the system. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
