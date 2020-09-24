package com.sg.booktracker.ui;

import java.util.Scanner;

/**
 *
 * @author kylerudy
 */
public class UserIOConsoleImpl implements UserIO {

    Scanner sc = new Scanner(System.in);

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public double readDouble(String prompt) {
        print(prompt);
        return Double.parseDouble(sc.nextLine());
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        double num;
        do {
            print(prompt);
            num = Double.parseDouble(sc.nextLine());
        } while (num < min || num > max);

        return num;
    }

    @Override
    public float readFloat(String prompt) {
        print(prompt);
        return Float.parseFloat(sc.nextLine());
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        float num;
        do {
            print(prompt);
            num = Float.parseFloat(sc.nextLine());
        } while (num < min || num > max);

        return num;
    }

    @Override
    public int readInt(String prompt) {
        print(prompt);
        return Integer.parseInt(sc.nextLine());
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        int num;
        do {
            print(prompt);
            num = Integer.parseInt(sc.nextLine());
        } while (num < min || num > max);

        return num;
    }

    @Override
    public long readLong(String prompt) {

        print(prompt);
        return Long.parseLong(sc.nextLine());
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        long num;
        do {
            print(prompt);
            num = Long.parseLong(sc.nextLine());
        } while (num < min || num > max);

        return num;
    }

    @Override
    public String readString(String prompt) {
        print(prompt);
        return sc.nextLine();
    }

}
