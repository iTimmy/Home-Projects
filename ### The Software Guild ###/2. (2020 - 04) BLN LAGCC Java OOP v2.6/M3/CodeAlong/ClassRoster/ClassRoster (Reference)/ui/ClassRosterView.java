package ui;

import controller.*;
import dao.*;
import dto.*;
import ui.*;
import java.util.*;

public class ClassRosterView {

    public ClassRosterView(UserIO io) {
        this.io = io;
    }

    public ClassRosterView() {
	}

        UserIO io = new UserIOConsoleImpl();
    
        public int printMenuAndGetSelection() {
            io.print("Main Menu");
            io.print("1. List Students");
            io.print("2. Create New Student");
            io.print("3. View a Student");
            io.print("4. Remove a Student");
            io.print("5. Exit");
    
            return io.readInt("Please select from the above choices.", 1, 5);
        }
    

    public Student getNewStudentInfo() {
        String studentId = io.readString("Please enter Student ID");
        String firstName = io.readString("Please enter First Name");
        String lastName = io.readString("Please enter Last Name");
        String cohort = io.readString("Please enter Cohort");
        Student currentStudent = new Student(studentId);
        currentStudent.setFirstName(firstName);
        currentStudent.setLastName(lastName);
        currentStudent.setCohort(cohort);

        // what is the difference between setting the constructor vs using the set methods to set values
        System.out.println("ok");
        return currentStudent;
    }

    public void displayCreateStudentBanner() {
        io.print("=== Create Student ===");
    }

    public void displayCreateSuccessBanner() {
        io.readString(
                "Student successfully created.  Please hit enter to continue");
    }

        public void displayExitBanner() {
            io.print("Good Bye!!!");
        }
        
        public void displayUnknownCommandBanner() {
            io.print("Unknown Command!!!");
        }

        public void displayErrorMessage(String errorMsg) {
            io.print("=== ERROR ===");
            io.print(errorMsg);
        }

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
}