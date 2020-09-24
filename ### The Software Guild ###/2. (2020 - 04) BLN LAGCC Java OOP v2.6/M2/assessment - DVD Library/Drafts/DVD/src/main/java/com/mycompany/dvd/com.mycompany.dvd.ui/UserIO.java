package com.mycompany.dvd.ui;

public interface UserIO {
    void print(String msg);

    void println(String msg);

    double readDouble(String prompt);

    double readDouble(String prompt, double min, double max);

    float readFloat(String prompt);

    float readFloat(String prompt, float min, float max);

    int readInt(String prompt);

    int readInt(String prompt, int min, int max);

    String readString(String prompt);
}
