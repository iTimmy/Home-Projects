import java.io.*;
import java.io.PrintWriter;
import java.util.*;

public class v {
  public static void main(String[] args) throws Exception {
    Scanner input = new Scanner(System.in);
    System.out.println("Read or write or search? ");
    String userInput = input.nextLine();

    if (userInput.equals("1")) {
      readFile();
    } else if (userInput.equals("2")) {
      writeFile();
    } else if (userInput.equals("3")) {
      search();
    }

  }

  public static void readFile() {
    try {
      Scanner scanner = new Scanner(new BufferedReader(new FileReader("test.txt")));
      if (scanner.hasNext() == true) {
        while (scanner.hasNext()) {
          String[] tokens = scanner.nextLine().split(" :: ");
          String last = tokens[tokens.length - 2];
          System.out.println(last);
          if (last.equals("ok")) {
            System.out.println("It is equivalent!");
          } else {
            System.out.println("Not equivalent.");
          }
        }
      } else {
        System.out.println("Doesn't exist!");
      }
    } catch (Exception e) {

    }
  }

  public static void writeFile() {
    try {
      PrintWriter writeFile = new PrintWriter("Student.txt");
      Scanner input = new Scanner(System.in);

      System.out.print("First Line: ");
      String userInputOne = input.nextLine();
      writeFile.write(userInputOne + " :: ");

      System.out.print("Second Line: ");
      String userInputTwo = input.nextLine();
      writeFile.write(userInputTwo + " :: ");

      System.out.print("Third Line: ");
      String userInputThree = input.nextLine();
      writeFile.write(userInputThree);

      writeFile.flush();
      writeFile.close();
    } catch (Exception e) {

    }
  }

  public static void search() {
    try {
    Scanner txtscan = new Scanner(new File("test.txt"));
    Scanner input = new Scanner(System.in);

    System.out.print("Search: ");
    String userInput = input.nextLine();

    while(txtscan.hasNextLine()){
    String str = txtscan.nextLine();
      if(str.indexOf("word") != -1){
        System.out.println("EXISTS");
      }
    }
    } catch (Exception e) {

    }
  
  }
}