import java.util.*;
import java.io.*;

public class StateCapitals2 {
    Map<String, String> states = new HashMap<>();
    Scanner input = new Scanner(System.in);
    public static void main (String[] args) throws Exception {
        StateCapitals2 sc = new StateCapitals2();
        sc.Display();
    }

    public void Display() throws Exception {
        Scanner fileRead = new Scanner(new BufferedReader(new FileReader("StateCapitals.txt")));
        System.out.println("\n______________________");
        String currentLine = "";
        while (fileRead.hasNextLine()) {
            currentLine = fileRead.nextLine();
            System.out.println(currentLine);
            String[] stateTokens = currentLine.split(DELIMITER);
            String stateName = stateTokens[0];
            String stateCapital = stateTokens[1];
            states.put(stateName, stateCapital);
        }
        System.out.println("______________________");

        System.out.println("Ready to test your knowledge?\nWhat is the capital of 'Alaska'? ");
        String userInput = input.nextLine();
        if (states.get("Alaska").equals(userInput)) {
            System.out.println("Correct!");
        } else {
            System.out.println("Incorrect!");
        }
    }
    public static final String DELIMITER = "::";
}