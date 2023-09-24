import java.util.ArrayList;

public class Student extends User {
    long studentID;
    ArrayList<Course> courses;
    GradeCard gradeCard;

    public Student(String uname, String passwd, String fullName, long studentID) {
        super(uname, passwd, fullName);
        this.studentID = studentID;
        courses = new ArrayList<Course>();
        gradeCard = new GradeCard(studentID);
    }

    public void listCourses() {
        if (courses.isEmpty()) {
            System.out.println("Not registered for any Course Yet");
            System.out.println("-----------------------------------");
            return;
        }
        System.out.println("-----------Courses Taken-----------");
        for (Course course : courses) {
            System.out.println(course.courseID);
        }
        System.out.println("-----------------------------------");
    }

    public void showGrades() {
        this.gradeCard.showGradeCard(this.courses);
    }

    public void showMarks(String courseID) {
        for (String subject : this.gradeCard.grades.keySet()) {
            if (subject.equals(courseID)) {
                System.out.println(this.studentID + " - " + this.gradeCard.grades.get(subject));
            }
        }
    }
}