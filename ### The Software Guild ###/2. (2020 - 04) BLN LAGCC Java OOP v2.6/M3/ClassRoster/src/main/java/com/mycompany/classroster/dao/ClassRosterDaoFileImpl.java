package com.mycompany.classroster.dao;

import com.mycompany.classroster.controller.*;
import com.mycompany.classroster.dao.*;
import com.mycompany.classroster.dto.*;
import com.mycompany.classroster.view.*;
import java.util.*;
import java.io.*;

public class ClassRosterDaoFileImpl implements ClassRosterDao {

    private Map<String, Student> students = new HashMap<>();
    private final String ROSTER_FILE;
    public static final String DELIMITER = "::";
    
    public ClassRosterDaoFileImpl(){
        ROSTER_FILE = "roster.txt";
    }

    public ClassRosterDaoFileImpl(String rosterTextFile){
        ROSTER_FILE = rosterTextFile;
    }

    @Override
    public Student addStudent(String studentId, Student student) {
        System.out.println("new : ");
        System.out.println("name:" + studentId + "     " + student.getFirstName());
        students.put(studentId, student);
        System.out.println("name: " + student.getFirstName());
        System.out.println("new : " + student.getFirstName());
        System.out.println(students.get(student));
        try {
            writeRoster();
        } catch (ClassRosterPersistenceException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return student;
    }

    @Override
    public List<Student> getAllStudents() throws ClassRosterPersistenceException {
        loadRoster();
        return new ArrayList(students.values());
    }

    @Override
    public Student getStudent(String studentId) throws ClassRosterPersistenceException {
        loadRoster();
        return students.get(studentId);
    }

    @Override
    public Student removeStudent(String studentId) throws ClassRosterPersistenceException {
        loadRoster();
        Student removedStudent = students.remove(studentId);
        writeRoster();
        return removedStudent;
    }

    private Student unmarshallStudent(String studentAsText) {
        String[] studentTokens = studentAsText.split(DELIMITER);
        String studentId = studentTokens[0];
        Student studentFromFile = new Student(studentId);

        // Index 1 - FirstName
        studentFromFile.setFirstName(studentTokens[1]);

        // Index 2 - LastName
        studentFromFile.setLastName(studentTokens[2]);

        // Index 3 - Cohort
        studentFromFile.setCohort(studentTokens[3]);

        // We have now created a student! Return it!
        return studentFromFile;
    }

    private void loadRoster() throws ClassRosterPersistenceException {
        Scanner scanner;

        try {
            scanner = new Scanner(new BufferedReader(new FileReader(ROSTER_FILE)));
        } catch (FileNotFoundException e) {
            throw new ClassRosterPersistenceException("-_- Could not load roster data into memory.", e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;
        // currentStudent holds the most recent student unmarshalled
        Student currentStudent;
        // Go through ROSTER_FILE line by line, decoding each line into a
        // Student object by calling the unmarshallStudent method.
        // Process while we have more lines in the file
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // unmarshall the line into a Student
            currentStudent = unmarshallStudent(currentLine);

            // We are going to use the student id as the map key for our student object.
            // Put currentStudent into the map using student id as the key
            students.put(currentStudent.getStudentId(), currentStudent);
        }
        // close scanner
        scanner.close();
    }

    private String marshallStudent(Student aStudent) {
        // We need to turn a Student object into a line of text for our file.
        // For example, we need an in memory object to end up like this:
        // 4321::Charles::Babbage::Java-September1842

        // It's not a complicated process. Just get out each property,
        // and concatenate with our DELIMITER as a kind of spacer.

        // Start with the student id, since that's supposed to be first.
        String studentAsText = aStudent.getStudentId() + DELIMITER;

        // add the rest of the properties in the correct order:

        // FirstName
        studentAsText += aStudent.getFirstName() + DELIMITER;

        // LastName
        studentAsText += aStudent.getLastName() + DELIMITER;

        // Cohort - don't forget to skip the DELIMITER here.
        studentAsText += aStudent.getCohort();

        // We have now turned a student to text! Return it!
        return studentAsText;
    }

    /**
     * Writes all students in the roster out to a ROSTER_FILE. See loadRoster for
     * file format.
     * 
     * @throws ClassRosterPersistenceException if an error occurs writing to the file
     */
    private void writeRoster() throws ClassRosterPersistenceException {
        // NOTE FOR APPRENTICES: We are not handling the IOException - but
        // we are translating it to an application specific exception and
        // then simple throwing it (i.e. 'reporting' it) to the code that
        // called us. It is the responsibility of the calling code to
        // handle any errors that occur.
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(ROSTER_FILE));
        } catch (IOException e) {
            throw new ClassRosterPersistenceException("Could not save student data.", e);
        }

        // Write out the Student objects to the roster file.
        // NOTE TO THE APPRENTICES: We could just grab the student map,
        // get the Collection of Students and iterate over them but we've
        // already created a method that gets a List of Students so
        // we'll reuse it.
        String studentAsText;
        List<Student> studentList = new ArrayList(students.values());
        for (Student currentStudent : studentList) {
            // turn a Student into a String
            studentAsText = marshallStudent(currentStudent);
            // write the Student object to the file
            out.println(studentAsText);
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }

}