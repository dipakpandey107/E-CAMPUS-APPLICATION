import java.util.ArrayList;

public class Teacher extends User {
    String email;
    ArrayList<Course> coursesTaught;

    public Teacher(String uname, String passwd, String fullName, String email) {
        super(uname, passwd, fullName);
        this.email = email;
        coursesTaught = new ArrayList<Course>();
    }

    public Course addCourse(String courseID, String name, int credit, double passingCriteria, String[] studentIDs) {
        Course c = new Course(courseID, name, credit, passingCriteria, this.fullName, studentIDs);
        coursesTaught.add(c);
        return c;
    }

    public void displayCoursesTaught() {
        for (Course course : this.coursesTaught) {
            course.displayDetails(this.fullName);
        }
    }

    public void viewCourses() {
        for (Course course : this.coursesTaught) {
            System.out.println("----------Course Details-----------");
            System.out.println("Course ID: " + course.courseID);
            System.out.println("Course Name: " + course.courseName);
            System.out.println("Course Credit: " + course.credit);
            System.out.println("Student's Registered: ");
            for (Long stdID : course.studentID) {
                System.out.print(stdID + " ");
            }
            System.out.println("\n------------------------------------");
        }
    }

    public void addStudent(String cID, Long stdID) {
        for (Course course : coursesTaught) {
            if (course.courseID.equals(cID)) {
                course.addStudent(stdID);
                return;
            }
        }
        System.out.println("You don't teach this course.");
    }

    public void deleteStudent(String cID, Long stdID) {
        for (Course course : coursesTaught) {
            if (course.courseID.equals(cID)) {
                course.deleteStudent(stdID);
                return;
            }
        }
        System.out.println("You don't teach this course.");
    }
}