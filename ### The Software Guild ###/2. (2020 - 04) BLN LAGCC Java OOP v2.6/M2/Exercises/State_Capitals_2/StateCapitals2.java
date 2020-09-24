import java.io.*;
import java.util.*;

public class StateCapitals2 {

    Map<String, String> map = new HashMap<>();
    public static void main (String[] args) throws Exception {
        display();
        storage();
        quiz();
    }

    public static void display() {
        System.out.println("50 STATES & CAPITALS ARE LOADED.\n" +
        "=======\n" +
        "HERE ARE THE STATES :");
    }

    public static void storage() throws Exception {
        Scanner readFile = new Scanner(new BufferedReader(new FileReader("StateCapitals.txt")));

        while(readFile.hasNextLine()) {
            String displayReadFile = readFile.nextLine();
            System.out.println(displayReadFile);
        }
    }

    public static void quiz() throws Exception {
        Scanner readFile = new Scanner(new BufferedReader(new FileReader("StateCapitals.txt")));

        System.out.println("\nREADY TO TEST YOUR KNOWLEDGE?\n" +
        "WHAT IS THE CAPITAL OF '" + "Alaska" + "'?");
        System.out.println(readFile.next("Alabama::Montgomery"));
    }

    
}