package com.xyqyear.possystem.core;

import java.io.FileWriter;
import java.io.IOException;

public class TxtPrinter implements IPrinter {
    @Override
    public void print(String s) {
        try {
            FileWriter fileWriter = new FileWriter("receipt.txt");
            fileWriter.append(s + "\n");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
