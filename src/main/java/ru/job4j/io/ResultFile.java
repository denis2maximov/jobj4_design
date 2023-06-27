package ru.job4j.io;

import java.io.FileOutputStream;

public class ResultFile {
    public static void multiple(int size) {
        try (FileOutputStream out = new FileOutputStream("data/dataresult.txt")) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    out.write((i + 1) * (j + 1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



