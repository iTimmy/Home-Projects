package com.kz.assontrack.dao;

import java.util.List;
import com.kz.assontrack.dto.*;

public interface TeacherDao {
    Teacher getTeacherById(int id);

    List<Teacher> getAllTeachers();

    Teacher addTeacher(Teacher teacher);

    void updateTeacher(Teacher teacher);

    void deleteTeacherById(int id);
}
