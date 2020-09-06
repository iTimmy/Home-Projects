package com.mycompany.classroster.dto;

import com.mycompany.classroster.controller.*;
import com.mycompany.classroster.dao.*;
import com.mycompany.classroster.dto.*;
import com.mycompany.classroster.view.*;
import java.util.*;

public class Student {
    private String firstName;
    private String lastName;
    private String studentId;
    // Programming Language + cohort month/year
    private String cohort;

    public Student(String studentId) {
        this.studentId = studentId;
    }

    private UserIO io = new UserIOConsoleImpl();

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getCohort() {
        return cohort;
    }

    public void setCohort(String cohort) {
        this.cohort = cohort;
    }   
    
    @Override
public int hashCode() {
    int hash = 7;
    hash = 89 * hash + Objects.hashCode(this.firstName);
    hash = 89 * hash + Objects.hashCode(this.lastName);
    hash = 89 * hash + Objects.hashCode(this.studentId);
    hash = 89 * hash + Objects.hashCode(this.cohort);
    return hash;
}

@Override
public boolean equals(Object obj) {
    if (this == obj) {
        return true;
    }
    if (obj == null) {
        return false;
    }
    if (getClass() != obj.getClass()) {
        return false;
    }
    final Student other = (Student) obj;
    if (!Objects.equals(this.firstName, other.firstName)) {
        return false;
    }
    if (!Objects.equals(this.lastName, other.lastName)) {
        return false;
    }
    if (!Objects.equals(this.studentId, other.studentId)) {
        return false;
    }
    if (!Objects.equals(this.cohort, other.cohort)) {
        return false;
    }
    return true;
    }

    @Override
    public String toString() {
        return "Student{" + "firstName=" + firstName + ", lastName=" + lastName + ", studentId=" + studentId + ", cohort=" + cohort + '}';
    }
}