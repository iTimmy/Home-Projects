package ui;

import dto.*;
import java.util.*;

public class ClassRosterView {

    private UserIO io = new UserIOConsoleImpl();
    
    public void Display() {
        /*
        System.out.println("Main Menu" +
        "\n1. List Student IDs" +
        "\n2. Create New Student" +
        "\n3. View a Student" +
        "\n4. Remove a Student" +
        "\n5. Exit");
        */
    }

    // display
    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. List Student IDs");
        io.print("2. Create New Student");
        io.print("3. View a Student");
        io.print("4. Remove a Student");
        io.print("5. Exit");

        return io.readInt("Please select from the"
                + " above choices.", 1, 5);
    }

    // gets user data

    // ### 2: CREATE STUDENTS ### //
    public Student getNewStudentInfo() {
        String studentId = io.readString("Please enter Student ID");
        String firstName = io.readString("Please enter First Name");
        String lastName = io.readString("Please enter Last Name");
        String cohort = io.readString("Please enter Cohort");
        Student currentStudent = new Student(studentId);
        currentStudent.setFirstName(firstName);
        currentStudent.setLastName(lastName);
        currentStudent.setCohort(cohort);
        return currentStudent;
    }

    public void displayCreateStudentBanner() {
        io.print("=== Create Student ===");
    }

    public void displayCreateSuccessBanner() {
        io.readString(
                "Student successfully created.  Please hit enter to continue");
    }



// ### 1: LIST STUDENTS ### //
    public void displayDisplayAllBanner() {
        io.print("=== Display All Students ===");
    }

    public void displayStudentList(List<Student> studentList) {
        for (Student currentStudent : studentList) {
            String studentInfo = String.format("#%s : %s %s",
                  currentStudent.getStudentId(),
                  currentStudent.getFirstName(),
                  currentStudent.getLastName());
            io.print(studentInfo);
        }
        io.readString("Please hit enter to continue.");
    }



// ### 3: VIEW STUDENTS ### //
    public void displayDisplayStudentBanner () {
        io.print("=== Display Student ===");
    }
    
    public String getStudentIdChoice() {
        return io.readString("Please enter the Student ID.");
    }
    
    public void displayStudent(Student student) {
        if (student != null) {
            io.print(student.getStudentId());
            io.print(student.getFirstName() + " " + student.getLastName());
            io.print(student.getCohort());
            io.print("");
        } else {
            io.print("No such student.");
        }
        io.readString("Please hit enter to continue.");
    }



// ### 4: REMOVE STUDENTS ### //
    public void displayRemoveStudentBanner () {
        io.print("=== Remove Student ===");
    }

    public void displayRemoveResult(Student studentRecord) {
        if(studentRecord != null){
        io.print("Student successfully removed.");
        }else{
        io.print("No such student.");
        }
        io.readString("Please hit enter to continue.");
    }



// ### 5: EXIT ### //
    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }
    
    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }
}