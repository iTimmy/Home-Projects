import java.util.*;

public class StudentQuizGrades {
    public static void main (String[] args) {
        UserIO io = new UserIOImpl();
        Map<String, Integer> student = new HashMap<>();
        List<Integer> quizScores = new ArrayList<>();

        student.put("John Cena", 85);
        student.put("Ethan Folk", 80);
        student.put("Fuck Me", 20);

        Set<String> studentKey = student.keySet();
        Collection<Integer> studentValue = student.values();
        
        while(true) {
            io.print("\n1. View list of students\n" + "2. Add a student\n" + "3. Remove a student\n"
            + "4. View list of quiz scores for given student\n"
            + "5. View the average quiz score for given student\n"
            + "6. Exit\n");

            int select = 0;
            int averageScore = 0;
            select = io.readInt("Please select: ", 1, 6);
        switch (select) {
            case 1:
                io.print("\nViewing list of students\n");
                for (String current : studentKey) {
                    io.print(current + "\n");
                }
                break;
            case 2:
                io.print("\nAdding a student\n");
                String newStudentName = io.readString("Enter a student name: ");
                int score1 = io.readInt("Enter 1st score: ");
                int score2 = io.readInt("Enter 2nd score: ");
                int score3 = io.readInt("Enter 3rd score: ");
                quizScores.add(score1);
                quizScores.add(score2);
                quizScores.add(score3);
                averageScore = (score1 + score2 + score3)/quizScores.size();
                student.put(newStudentName, averageScore);
                System.out.println("___________________\nAverage Score: " + averageScore);
                break;
            case 3:
                io.print("\nRemoving a student\n");
                break;
            case 4:
                io.print("\nViewing list of student scores for given student\n");
                System.out.println(quizScores);
                break;
            case 5:
                io.print("\nViewing list of student scores for given student\n");
                System.out.println("___________________\nAverage Score: " + studentValue);
                break;
            case 6:
                break;
        }
    }
    }
}