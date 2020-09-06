package dao;

import java.util.*;
import dto.*;

public class ClassRosterDaoFileImpl implements ClassRosterDao {

    private Map<String, Student> students = new HashMap<>();
        @Override
        public Student addStudent(String studentId, Student student) {
            Student prevStudent = students.put(studentId, student);
            return prevStudent;
        }
    
        @Override
        public List<Student> getAllStudents() {
            return new ArrayList<Student>(students.values());
        }
    
        @Override
        public Student getStudent(String studentId) {
            return students.get(studentId);
        }
    
        @Override
        public Student removeStudent(String studentId) {
            Student removedStudent = students.remove(studentId);
            return removedStudent;
        }
}