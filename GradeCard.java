import java.util.HashMap;
import java.util.ArrayList;

public class GradeCard{
    long studentID;
    HashMap<String, Integer> grades;
    double percentage;

    GradeCard(long stdID){
        this.studentID = stdID;
        grades = new HashMap<String, Integer>();
        this.percentage = 0;
    }

    public void showGradeCard(ArrayList<Course> courses){
        if(grades.isEmpty()){
            System.out.println("No Grades Assigned Yet");
            System.out.println("-----------------------------------");    
            return;
        }
        System.out.println("----------------Grade-Card----------------");
        System.out.println("  CourseID    |     Marks      |   Grade  ");
        for(String courseID: grades.keySet()){
            System.out.println(courseID + "      |     " + grades.get(courseID) + "      |      " + calculateGrade(courses, courseID, grades.get(courseID))); 
        }
        System.out.println("\nPercentage: " + this.percentage);
        System.out.println("------------------------------------------");
    }

    public String calculateGrade(ArrayList<Course> courses, String courseID, int marks){
        for(Course course: courses){
            if(course.courseID.equals(courseID)){
                if(marks > course.passingCriteria)  return "Pass";
                return "Fail";
            }
        }
        return "Fail";
    }

    public void calculatePercentage(){
        double sum = 0;
        int count = 0;
        for(int marks: grades.values()){
            sum += marks;
            count++;
        }
        this.percentage = sum/count;
    }
}