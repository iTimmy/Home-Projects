package com.kz.assontrack.dao;

import java.util.List;
import com.kz.assontrack.dto.*;

public interface StudentDao {
    Student getStudentById(int id);

    List<Student> getAllStudents();

    Student addStudent(Student student);

    void updateStudent(Student student);

    void deleteStudentById(int id);
}
