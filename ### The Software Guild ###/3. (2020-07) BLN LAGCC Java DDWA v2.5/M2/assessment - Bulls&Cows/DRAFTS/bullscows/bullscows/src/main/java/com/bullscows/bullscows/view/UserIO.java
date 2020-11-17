package com.bullscows.bullscows.view;

public interface UserIO {
    void print(String message);
    void println(String string);
    String readString(String prompt);
    int readInt(String prompt);
    int readInt(String prompt, int min, int max);
    double readDouble(String prompt);
    double readDouble(String prompt, double min, double max);
    float readFloat(String prompt);
    float readFloat(String prompt, float min, float max);
    long readLong(String prompt);
    long readLong(String prompt, long min, long max);
}
