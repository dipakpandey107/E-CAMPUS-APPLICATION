import java.util.ArrayList;
import java.io.Console;
import java.util.Scanner;

public class Main {

    //Scanner and Console for input.
    Scanner sc = new Scanner(System.in);
    Console cnsl = System.console();
    
    //For maintaining current logged in session.
    String userType = "";
    Teacher currentTeacher;
    Student currentStudent;

    //To store registered user(teacher/student) list.
    ArrayList<Teacher> teachers = new ArrayList<>();
    ArrayList<Student> students = new ArrayList<>();

    //Function for User Registration.
    public void register(String userType){
        System.out.println("Enter Username: ");
        String uname = sc.nextLine();
        System.out.print("Enter Password: ");
        String passwd = String.valueOf(cnsl.readPassword());
        for(int i = 0; i < passwd.length(); i++)    System.out.print("*");
        System.out.println("\nEnter FullName: ");
        String fullName = sc.nextLine();
        
        if(userType.equals("Student")){
            System.out.println("Enter StudentID: ");
            long stdID = sc.nextInt();    sc.nextLine();
            students.add(new Student(uname, passwd, fullName, stdID));
        }
        else if(userType.equals("Teacher")){
            System.out.println("Enter Teacher Email: ");
            String email = sc.nextLine();
            teachers.add(new Teacher(uname, passwd, fullName, email));
        }
        System.out.println("\nAccount Created Successfully\n");
    }

    //Login Function
    public int signIn(String userType){
        System.out.println("-------------Sign-In---------------");
        System.out.print("Enter Username: ");
        String uname = sc.nextLine();
        System.out.print("Enter Password: ");
        String passwd = String.valueOf(cnsl.readPassword());
        for(int i = 0; i < passwd.length(); i++)    System.out.print("*");
        System.out.println();

        //Student Login
        if(userType.equals("Student")){
            return studentSignIn(uname, passwd);
        }
        else if(userType.equals("Teacher")){
            return teacherSignIn(uname, passwd);
        }
        else{
            return -1;
        }
    }

    //Student Login
    public int studentSignIn(String uname, String passwd){
        for(Student student: students){
            if(student.username.equals(uname) && student.password.equals(passwd)){
                //If Authorization Successful.
                currentStudent = student;
                return 1;
            }
            else if(student.username.equals(uname)){
                //If Wrong Password.
                return 0;
            }
        }
        //Authorization Failed.
        return -1;
    }

    //Teacher Login
    public int teacherSignIn(String uname, String passwd){
        for(Teacher teacher: teachers){
            if(teacher.username.equals(uname) && teacher.password.equals(passwd)){
                //If Authorization Successful.
                currentTeacher = teacher;
                return 1;
            }
            else if(teacher.username.equals(uname)){
                //If Wrong Password.
                return 0;
            }
        }

        //Authorization Failed.
        return -1;
    }

    //Function to Reset Password.
    public int resetPassword(String userType){
        System.out.println("----------Reset-Password-----------");
        if(userType.equals("Student"))
            return resetPasswordStudent();
        else if(userType.equals("Teacher"))
            return resetPasswordTeacher();
        
        //Invalid Option
        return -1;
    }

    //Reset Password For Student.
    public int resetPasswordStudent(){
        System.out.println("Enter Username: ");
        String uname = sc.nextLine();
        for(Student student: students){
            if(student.username.equals(uname)){
                System.out.print("Enter New Password: ");
                String passwd = String.valueOf(cnsl.readPassword());
                for(int i = 0; i < passwd.length(); i++)    System.out.print("*");
                System.out.println();
                student.password = passwd;
                //Successful change of password.
                return 1;
            }
        }
        //If User Doesn't Exist.
        return 0;
    }

    //Reset Password For Teacher.
    public int resetPasswordTeacher(){
        System.out.println("Enter Username: ");
        String uname = sc.nextLine();
        for(Teacher teacher: teachers){
            if(teacher.username.equals(uname)){
                System.out.print("Enter New Password: ");
                String passwd = String.valueOf(cnsl.readPassword());
                for(int i = 0; i < passwd.length(); i++)    System.out.print("*");
                System.out.println();
                teacher.password = passwd;
                //Successful change of password.
                return 1;
            }
        }
        //If User Doesn't Exist.
        return 0;
    }

    //Common Function to take input.
    public int takeInput(String state){
        int opt;
        
        if(state.equals("Password")){
            System.out.println("----------Wrong Password-----------");
            System.out.println("1. Reset Password\n2. Retry");
            opt = sc.nextInt();   sc.nextLine();
            System.out.println("-----------------------------------");
            return opt;      
        }
        else if(state.equals("home")){
            if(userType.equals("Student"))
                System.out.println("--------------Student--------------");
            else
                System.out.println("--------------Teacher--------------");
            
            System.out.println("Enter Option: \n1. Sign In\n2. Register\n3. Select User Type");
            opt = sc.nextInt();     sc.nextLine();
            System.out.println("-----------------------------------");
            return opt;
        }
        else if(state.equals("type")){
            System.out.println("----------Select User Type----------");
            System.out.println("1. Student\n2. Teacher\n3. Exit");
            opt = sc.nextInt();     sc.nextLine();
            System.out.println("-----------------------------------");
            return opt;
        }

        return -1;
    }

    //Function to Add Course.
    public void takeCourseInput(){
        System.out.println("-------Enter Course Details--------");
        System.out.print("Course ID: ");
        String courseID = sc.nextLine();
        System.out.print("Course Name: ");
        String name = sc.nextLine();
        System.out.print("Passing Criteria(out of 100): ");
        double passingCriteria = sc.nextDouble();   sc.nextLine();
        System.out.print("Credit: ");
        int credit = sc.nextInt();      sc.nextLine();
        
        System.out.print("Enter Student ID's(separated by spaces) to add to this course: ");
        String input = sc.nextLine();
        
        String[] studentIDs = input.split(" ");
        Course c = currentTeacher.addCourse(courseID, name, credit, passingCriteria, studentIDs);
        for(String studentID: studentIDs){
            Long stdID = Long.parseLong(studentID);
            for(Student std: students){
                if(std.studentID == stdID){
                    std.courses.add(c);
                }
            }
        }
        System.out.println("-----------------------------------");
    }
    
    //Show Student Options
    public void studentOptions(){
        System.out.println("-----------Select Option-----------");
        int opt = 0;
        while(opt != 3){
            System.out.println("1. View Courses Taken: \n2. View Grade Card: \n3. Logout");
            opt = sc.nextInt();
            if(opt == 1){
                currentStudent.listCourses();
            }
            else if(opt == 2){
                currentStudent.showGrades();
            }
            else{
                System.out.println("Invalid Option");
            }
        }
        System.out.println("-----------------------------------");
    }

    //Show Teacher Options
    public void teacherOptions(){
        int opt = 0;
        while(opt != 5){
            System.out.println("-----------Select Option-----------");
            System.out.println("1. Add Course: \n2. View Courses & Students: \n3. Add, View, Update Marks: \n4. Add/Delete Student: \n5. Logout");
            opt = sc.nextInt();     sc.nextLine();
            switch(opt){
                case 1: takeCourseInput();
                        break;
                case 2: currentTeacher.viewCourses();
                        break;
                case 3: modifyMarks();
                        break;
                case 4: modifyStudents();
                        break;
                case 5: 
                        break;
                default:System.out.println("Enter Valid Option");
                        break;
            }
        }
        System.out.println("-----------------------------------");
    }

    //Function to modify marks.
    public void modifyMarks(){
        int opt = 0;
        while(opt != 4){
            System.out.println("-----------Select Option-----------");
            System.out.println("1. Add Marks: \n2. Update Marks: \n3. View Marks: \n4. Go Back");
            opt = sc.nextInt();     sc.nextLine();
            switch(opt){
                case 1: addMarks();
                        break;
                case 2: updateMarks();
                        break;
                case 3: viewMarks();
                        break;
                case 4: return;
                default: System.out.println("Enter Valid Option");
            }
        }
        System.out.println("-----------------------------------");
    }

    public void addMarks(){
        System.out.println("--------Enter Student Details-------");
        System.out.print("Student ID: ");
        Long sid = sc.nextLong();   sc.nextLine();
        System.out.print("Course ID: ");
        String cid = sc.nextLine();
        System.out.print("Enter Marks: ");
        int marks = sc.nextInt();       sc.nextLine();
        for(Student std: students){
            if(std.studentID == sid){
                for(Course crs: std.courses){
                    if(cid.equals(crs.courseID)){
                        std.gradeCard.grades.put(cid, marks);
                        std.gradeCard.calculatePercentage();
                        break;
                    }
                }
            }
        }
        System.out.println("-----------------------------------");
    }

    public void updateMarks(){
        System.out.println("--------Enter Student Details-------");
        System.out.print("Student ID: ");
        Long sid = sc.nextLong();   sc.nextLine();
        System.out.print("Course ID: ");
        String cid = sc.nextLine();
        System.out.print("Enter New Marks: ");
        int marks = sc.nextInt();       sc.nextLine();
        for(Student std: students){
            if(std.studentID == sid){
                std.gradeCard.grades.put(cid, marks);
                std.gradeCard.calculatePercentage();
                break;
            }
        }
        System.out.println("-----------------------------------");
    }

    public void viewMarks(){
        for(Course course: currentTeacher.coursesTaught){
            System.out.println("---------------" + course.courseID + "---------------");
            for(Long stdID: course.studentID){
                for(Student student: students){
                    if(student.studentID == stdID){
                        student.showMarks(course.courseID);
                    }
                }
            }
            System.out.println("-----------------------------------");
        }
        // System.out.println("--------Enter Student Details-------");
        // System.out.print("Student ID: ");
        // Long sid = sc.nextLong();   sc.nextLine();
        // System.out.print("Course ID: ");
        // String cid = sc.nextLine();
        // for(Student std: students){
        //     if(std.studentID == sid){
        //         for(String course: std.gradeCard.grades.keySet()){
        //             if(course.equals(cid)){
        //                 System.out.println("Grade is: " + std.gradeCard.grades.get(cid));
        //                 break;
        //             }
        //         }
        //     }
        // }
        // System.out.println("-----------------------------------");
    }

    //Function to Modify Student.
    public void modifyStudents(){
        int opt = 0;
        while(opt != 3){
            System.out.println("-----------Select Option-----------");
            System.out.println("1. Add Student \n2. Delete Student \n3. Go Back");
            opt = sc.nextInt();     sc.nextLine();
            switch(opt){
                case 1: addStudent();
                        break;
                case 2: deleteStudent();
                        break;
            }
            System.out.println("-----------------------------------");
        }
    }

    public void addStudent(){
        System.out.println("Enter Course ID: ");
        String cid = sc.nextLine();
        System.out.print("Enter Student ID to add: ");
        Long sid = sc.nextLong();       sc.nextLine();
        currentTeacher.addStudent(cid, sid);
    }

    public void deleteStudent(){
        System.out.println("Enter Course ID: ");
        String cid = sc.nextLine();
        System.out.print("Enter Student ID to delete: ");
        Long sid = sc.nextLong();       sc.nextLine();
        currentTeacher.deleteStudent(cid, sid);
    }

    public static void main(String[] args){
        Main site = new Main();
        
        while(true){
            //Select userType
            int opt = site.takeInput("type");
            
            if(opt == 1)    site.userType = "Student";
            else if(opt == 2)   site.userType = "Teacher";
            else    break; 

            while(true){
                opt = site.takeInput("home");
                //Sign-In
                if(opt == 1){
                    opt = site.signIn(site.userType);

                    //Login Successful

                    if(opt == 1){
                        System.out.println("\nSign-In Successful\n");
                        if(site.userType == "Student"){
                            site.studentOptions();
                        }
                        else if(site.userType == "Teacher"){
                            site.teacherOptions();
                        }
                    }

                    //Wrong Password
                    else if(opt == 0){
                        opt = site.takeInput("Password");

                        //Reset Password
                        if(opt == 1){
                            int res = site.resetPassword(site.userType);
                            if(res == 1){
                                System.out.println("Password Changed Successfully");
                            }
                            else{
                                System.out.println("User Doesn't Exist");
                                continue;
                            }
                        }

                        //Retry
                        else if(opt == -1){
                            continue;
                        }
                    }

                    //User Doesn't Exist
                    else if(opt == -1){
                        System.out.println("User Doesn't Exist");
                    }

                }

                //Register
                else if(opt == 2){
                    site.register(site.userType);
                }

                //Exit
                else if(opt == 3){
                    break;
                }
                else{
                    System.out.println("Not a Valid Option");
                }
            }
        }
    }
}
