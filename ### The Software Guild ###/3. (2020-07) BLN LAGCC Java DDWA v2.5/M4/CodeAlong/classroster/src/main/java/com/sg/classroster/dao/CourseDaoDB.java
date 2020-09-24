@Repository
public class CourseDaoDB implements CourseDao {

   @Autowired
   JdbcTemplate jdbc;

   @Override
    public Course getCourseById(int id) {
        try {
            final String SELECT_COURSE_BY_ID = "SELECT * FROM course WHERE id = ?";
            Course course = jdbc.queryForObject(SELECT_COURSE_BY_ID, new CourseMapper(), id);
            course.setTeacher(getTeacherForCourse(id));
            course.setStudents(getStudentsForCourse(id));
            return course;
        } catch(DataAccessException ex) {
            return null;
        }
    }
    @GetMapping("courseDetail")
    public String courseDetail(Integer id, Model model) {
        Course course = courseDao.getCourseById(id);
        model.addAttribute("course", course);
        return "courseDetail";
    }

    private Teacher getTeacherForCourse(int id) {
        final String SELECT_TEACHER_FOR_COURSE = "SELECT t.* FROM teacher t "
                + "JOIN course c ON c.teacherId = t.id WHERE c.id = ?";
        return jdbc.queryForObject(SELECT_TEACHER_FOR_COURSE, new TeacherMapper(), id);
    }

    private List<Student> getStudentsForCourse(int id) {
        final String SELECT_STUDENTS_FOR_COURSE = "SELECT s.* FROM student s "
                + "JOIN course_student cs ON cs.studentId = s.id WHERE cs.courseId = ?";
        return jdbc.query(SELECT_STUDENTS_FOR_COURSE, new StudentMapper(), id);
    }

   @Override
    public List<Course> getAllCourses() {
        final String SELECT_ALL_COURSES = "SELECT * FROM course";
        List<Course> courses = jdbc.query(SELECT_ALL_COURSES, new CourseMapper());
        associateTeacherAndStudents(courses);
        return courses;
    }

    private void associateTeacherAndStudents(List<Course> courses) {
        for (Course course : courses) {
            course.setTeacher(getTeacherForCourse(course.getId()));
            course.setStudents(getStudentsForCourse(course.getId()));
        }
    }

   @Override
    @PostMapping("addCourse")
        public String addCourse(Course course, HttpServletRequest request) {
            String teacherId = request.getParameter("teacherId");
            String[] studentIds = request.getParameterValues("studentId");
            
            course.setTeacher(teacherDao.getTeacherById(Integer.parseInt(teacherId)));
            
            List<Student> students = new ArrayList<>();
            for(String studentId : studentIds) {
                students.add(studentDao.getStudentById(Integer.parseInt(studentId)));
            }
            course.setStudents(students);
            courseDao.addCourse(course);
            
            return "redirect:/courses";
        }
    @Transactional
    public Course addCourse(Course course) {
        final String INSERT_COURSE = "INSERT INTO course(name, description, teacherId) "
                + "VALUES(?,?,?)";
        jdbc.update(INSERT_COURSE,
                course.getName(),
                course.getDescription(),
                course.getTeacher().getId());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        course.setId(newId);
        insertCourseStudent(course);
        return course;
    }

    private void insertCourseStudent(Course course) {
        final String INSERT_COURSE_STUDENT = "INSERT INTO "
                + "course_student(courseId, studentId) VALUES(?,?)";
        for(Student student : course.getStudents()) {
            jdbc.update(INSERT_COURSE_STUDENT, 
                    course.getId(),
                    student.getId());
        }
    }

   @Override
    @PostMapping("editCourse")
        public String performEditCourse(Course course, HttpServletRequest request) {
            String teacherId = request.getParameter("teacherId");
            String[] studentIds = request.getParameterValues("studentId");
            
            course.setTeacher(teacherDao.getTeacherById(Integer.parseInt(teacherId)));
            
            List<Student> students = new ArrayList<>();
            for(String studentId : studentIds) {
                students.add(studentDao.getStudentById(Integer.parseInt(studentId)));
            }
            course.setStudents(students);
            courseDao.updateCourse(course);
            
            return "redirect:/courses";
        }
    @GetMapping("editCourse")
        public String editCourse(Integer id, Model model) {
            Course course = courseDao.getCourseById(id);
            List<Student> students = studentDao.getAllStudents();
            List<Teacher> teachers = teacherDao.getAllTeachers();
            model.addAttribute("course", course);
            model.addAttribute("students", students);
            model.addAttribute("teachers", teachers);
            return "editCourse";
        }
    @Transactional
    public void updateCourse(Course course) {
        final String UPDATE_COURSE = "UPDATE course SET name = ?, description = ?, "
                + "teacherId = ? WHERE id = ?";
        jdbc.update(UPDATE_COURSE, 
                course.getName(), 
                course.getDescription(), 
                course.getTeacher().getId(),
                course.getId());
        
        final String DELETE_COURSE_STUDENT = "DELETE FROM course_student WHERE courseId = ?";
        jdbc.update(DELETE_COURSE_STUDENT, course.getId());
        insertCourseStudent(course);
    }

   @Override
    @GetMapping("deleteCourse")
        public String deleteCourse(Integer id) {
            courseDao.deleteCourseById(id);
            return "redirect:/courses";
        }
    @Transactional
    public void deleteCourseById(int id) {
        final String DELETE_COURSE_STUDENT = "DELETE FROM course_student WHERE courseId = ?";
        jdbc.update(DELETE_COURSE_STUDENT, id);
        
        final String DELETE_COURSE = "DELETE FROM course WHERE id = ?";
        jdbc.update(DELETE_COURSE, id);
    }

   @Override
    public List<Course> getCoursesForTeacher(Teacher teacher) {
        final String SELECT_COURSES_FOR_TEACHER = "SELECT * FROM course WHERE teacherId = ?";
        List<Course> courses = jdbc.query(SELECT_COURSES_FOR_TEACHER, 
                new CourseMapper(), teacher.getId());
        associateTeacherAndStudents(courses);
        return courses;
    }

    @Override
    public List<Course> getCoursesForStudent(Student student) {
        final String SELECT_COURSES_FOR_STUDENT = "SELECT c.* FROM course c JOIN "
                + "course_student cs ON cs.courseId = c.Id WHERE cs.studentId = ?";
        List<Course> courses = jdbc.query(SELECT_COURSES_FOR_STUDENT, 
                new CourseMapper(), student.getId());
        associateTeacherAndStudents(courses);
        return courses;
    }
}

public static final class CourseMapper implements RowMapper<Course> {

        @Override
        public Course mapRow(ResultSet rs, int index) throws SQLException {
            Course course = new Course();
            course.setId(rs.getInt("id"));
            course.setName(rs.getString("name"));
            course.setDescription(rs.getString("description"));
            return course;
        }
    }