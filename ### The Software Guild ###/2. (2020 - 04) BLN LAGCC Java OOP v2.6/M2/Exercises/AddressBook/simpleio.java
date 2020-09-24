import java.io.*;
import java.util.*;

public class simpleio {
    public static void main (String[] args) throws Exception {
        PrintWriter out = new PrintWriter(new FileWriter("io.txt"));

        out.println("ok");
        out.println("no");
        out.println("yes");
        out.flush();
        out.close();

        Scanner input = new Scanner(new BufferedReader(new FileReader("io.txt")));

        while (input.hasNextLine()) {
            String currentLine = input.nextLine();
            System.out.println(currentLine);
        }

        System.out.println("Clear.");
    }
}