@Controller
public class CourseController {

   @Autowired
   TeacherDao teacherDao;

   @Autowired
   StudentDao studentDao;

   @Autowired
   CourseDao courseDao;

    @GetMapping("courses")
    public String displayCourses(Model model) {
        List<Course> courses = courseDao.getAllCourses();
        List<Teacher> teachers = teacherDao.getAllTeachers();
        List<Student> students = studentDao.getAllStudents();
        model.addAttribute("courses", courses);
        model.addAttribute("teachers", teachers);
        model.addAttribute("students", students);
        return "courses";
    }
    
}