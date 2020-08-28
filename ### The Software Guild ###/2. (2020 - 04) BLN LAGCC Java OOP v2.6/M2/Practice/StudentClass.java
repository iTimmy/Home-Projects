import java.util.*;
import java.io.*;

public class StudentClass {

    public static void main (String [] args) throws Exception {
        PrintWriter fileWrite = new PrintWriter(new FileWriter("Student.txt"));
        Scanner fileRead = new Scanner(new BufferedReader(new FileReader("Student.txt")));
        Student student = new Student("209014885");
        UserIO io = new UserIOImpl();
        Map<String, String> studentInfo = new HashMap<>();
        List<String> studentFull = new ArrayList<>();

        int i = 0;
        String question = "";
        while(!question.equals("n")) {
            String studentFirstName = io.readString("What is your first name? ");
            student.setFirstName(studentFirstName);
        
            String studentLastName = io.readString("What is your last name? ");
            student.setLastName(studentLastName);

            String studentCohort = io.readString("What is your cohort? ");
            student.setCohort(studentCohort);

            studentInfo.put(studentFirstName + " " + studentLastName, studentCohort);

            Set<String> studentKeys = studentInfo.keySet();

            for (String current : studentKeys) {
                io.println(current);
            }

            
            String data = "hello";

            studentFull.add(data);
            studentFull.add(data);
            studentFull.add(data);
            studentFull.add(data);
            studentFull.add(data);
            
            for (String current : studentFull) {
                fileWrite.print(current);
            }

            question = io.readString("Continue? ");
        }
        fileWrite.flush();
        fileWrite.close();

        io.println(student.getFirstName());
        student.getLastName();

        /*
        if (student.getKey().equals(studentSearch)) {
            io.println(sudent.getValue());
        } */

        while(fileRead.hasNextLine()) {
            String currentLine = fileRead.nextLine();
            io.println(currentLine);
        }
    }
}