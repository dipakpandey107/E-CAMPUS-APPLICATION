import java.util.ArrayList;

public class Course {
    String courseID;
    String courseName;
    double passingCriteria;
    int credit;
    String teacherName;
    ArrayList<Long> studentID;

    Course(String cID, String name, int credit, double passingCriteria, String teacherName, String[] studentIDs) {
        this.courseID = cID;
        this.courseName = name;
        this.credit = credit;
        this.passingCriteria = passingCriteria;
        this.teacherName = teacherName;
        studentID = new ArrayList<>();
        for (int i = 0; i < studentIDs.length; i++) {
            Long sid = Long.parseLong(studentIDs[i]);
            studentID.add(sid);
        }
    }

    public void addStudent(Long stdID) {
        if (!this.studentID.contains(stdID)) {
            this.studentID.add(stdID);
            System.out.println("Student Added to Course");
            return;
        }
        System.out.println("Student already enrolled in Course");
    }

    public void deleteStudent(Long stdID) {
        if (this.studentID.contains(stdID)) {
            this.studentID.remove(stdID);
            System.out.println("Student Removed From Course");
            return;
        }
    }

    public void displayDetails(String teacherName) {
        System.out.println("Course Details: ");
        System.out.println("ID: " + this.courseID);
        System.out.println("Name: " + this.courseName);
        System.out.println("Credit: " + this.credit);
        System.out.println("Passing Criteria: " + this.passingCriteria);
        System.out.println("Taught By: " + teacherName);
        System.out.println("Student's List: ");
        for (int i = 0; i < studentID.size(); i++) {
            System.out.print(studentID.get(i));
        }

    }
}