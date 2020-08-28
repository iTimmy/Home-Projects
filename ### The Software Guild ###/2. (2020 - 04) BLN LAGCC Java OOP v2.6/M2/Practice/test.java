import java.io.*;
import java.io.PrintWriter;
import java.util.*;

public class test {
    public static void main(String[] args) throws IOException {
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
                    String last = tokens[tokens.length - 1];
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
            FileWriter fw = new FileWriter("test.txt",true);
    	  BufferedWriter bw = new BufferedWriter(fw);
          PrintWriter pw = new PrintWriter(bw);
          
            // PrintWriter writeFile = new PrintWriter("Student.txt");
            Scanner input = new Scanner(System.in);

            System.out.print("First Line: ");
            String userInputOne = input.nextLine();
            pw.write(userInputOne + " :: ");

            System.out.print("Second Line: ");
            String userInputTwo = input.nextLine();
            pw.write(userInputTwo + " :: ");

            System.out.print("Third Line: ");
            String userInputThree = input.nextLine();
            pw.println(userInputThree);

            pw.flush();
            pw.close();
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
          if(str.indexOf(userInput) != -1){
            System.out.println("EXISTS");
          } else {
              System.out.println("Non-existent.");
          }
        }

        File fromFile = new File("test.txt");         
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFile));
        String line;
        String firstHandler="";
        while ((line = bufferedReader.readLine()) != null) 
        {                
         if (line.startsWith(userInput))
           {
            System.out.println(line);
            String[] parts = line.split(" :: ");   
            // System.out.println(Arrays.toString(parts));  
           } 
         }
        } catch (Exception e) {
    
        }
      
      }
}