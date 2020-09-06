package controller;

import controller.*;
import dao.*;
import dto.*;
import ui.*;
import java.util.*;

public class ClassRosterController {

  

    private UserIO io = new UserIOConsoleImpl();
    private ClassRosterDao dao = new ClassRosterDaoFileImpl();
    private ClassRosterView view = new ClassRosterView();

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            while (menuSelection != 5) {
    
                menuSelection = getMenuSelection();
                System.out.println("ok");
    
                switch (menuSelection) {
                    case 1:
                        listStudents();
                        break;
                    case 2:
                    System.out.println("okk");
                        createStudent();
                        break;
                    case 3:
                        viewStudent();
                        break;
                    case 4:
                        removeStudent();
                        break;
                    case 5:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }
            }
            System.out.println("ok");
            exitMessage();
        } catch (ClassRosterDaoException e) {
            // view.displayErrorMessage(e.getMessage());
        }
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }
    
    private void createStudent() throws ClassRosterDaoException {
        System.out.println("okkk");
        view.displayCreateStudentBanner();
        System.out.println("okkkk");
        Student newStudent = view.getNewStudentInfo();
        System.out.println("okkkkk");
        dao.addStudent(newStudent.getStudentId(), newStudent);
        
        System.out.println("okkkkkllll");
        view.displayCreateSuccessBanner();
    }
    
    private void listStudents() throws ClassRosterDaoException {
        view.displayDisplayAllBanner();
        List<Student> studentList = dao.getAllStudents();
        view.displayStudentList(studentList);
    }
    
    private void viewStudent() throws ClassRosterDaoException {
        view.displayDisplayStudentBanner();
        String studentId = view.getStudentIdChoice();
        Student student = dao.getStudent(studentId);
        view.displayStudent(student);
    }
    
    private void removeStudent() throws ClassRosterDaoException {
        view.displayRemoveStudentBanner();
        String studentId = view.getStudentIdChoice();
        Student removedStudent = dao.removeStudent(studentId);
        view.displayRemoveResult(removedStudent);
    }
    
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }
    
    private void exitMessage() {
        view.displayExitBanner();
    }
}