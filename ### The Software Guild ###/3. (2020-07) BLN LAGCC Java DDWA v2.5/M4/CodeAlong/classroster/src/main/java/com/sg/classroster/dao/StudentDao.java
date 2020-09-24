public interface StudentDao {
    Student getStudentById(int id);
    List<Student> getAllStudents();
    Student addStudent(Student student);
    void updateStudent(Student student);
    void deleteStudentById(int id);
}