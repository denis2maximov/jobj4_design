package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream inputStream = new FileInputStream("data/even.txt")) {
             StringBuilder string = new StringBuilder();
            int end;
            while ((end = inputStream.read()) != -1) {
                string.append((char) end);
            }
            String[] a = string.toString().split(System.lineSeparator());
            for (String st : a) {
                System.out.println(Integer.parseInt(st) + " " + (Integer.parseInt(st) % 2 == 0));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
