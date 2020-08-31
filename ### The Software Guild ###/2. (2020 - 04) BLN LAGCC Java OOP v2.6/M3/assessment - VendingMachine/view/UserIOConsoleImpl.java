package view;

import java.util.*;

public class UserIOConsoleImpl implements UserIO {
    Scanner scan = new Scanner(System.in);

    public void print(String message) {
        System.out.print(message);
    }

    public void println(String message) {
        System.out.println(message);
    }

    public String readString(String prompt) {
        print(prompt);
        String msg = scan.next();
        return msg;
    }

    public int readInt(String prompt) {
        int num = 0;
        print(prompt);
        num = scan.nextInt();
        return num;
    }

    public int readInt(String prompt, int min, int max) {
        int num = 0;
        print(prompt);
        num = scan.nextInt();
        if (num > max) {
            print("Too high.");
        } else if (num < min) {
            print("Too low.");
        }
        return num;
    }

    public double readDouble(String prompt) {
        print(prompt);
        double num = scan.nextDouble();
        return num;
    }

    public double readDouble(String prompt, double min, double max) {
        print(prompt);
        double num = scan.nextDouble();
        if (num > max) {
            print("Too high.");
        } else if (num < min) {
            print("Too low.");
        }
        return num;
    }

    public float readFloat(String prompt) {
        print(prompt);
        float num = scan.nextFloat();
        return num;
    }

    public float readFloat(String prompt, float min, float max) {
        print(prompt);
        float num = scan.nextFloat();
        if (num > max) {
            print("Too high.");
        } else if (num < min) {
            print("Too low.");
        }
        return num;
    }

    public long readLong(String prompt) {
        print(prompt);
        long num = scan.nextLong();
        return num;
    }

    public long readLong(String prompt, long min, long max) {
        print(prompt);
        long num = scan.nextLong();
        if (num > max) {
            print("Too high.");
        } else if (num < min) {
            print("Too low.");
        }
        return num;
    }
}