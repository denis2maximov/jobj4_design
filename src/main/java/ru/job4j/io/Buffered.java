package ru.job4j.io;

import java.io.*;

public class Buffered {
    public static void main(String[] args) {
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream("data/newData.txt"));
             BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("data/output.txt", true))) {
            out.write(System.lineSeparator().getBytes());
            out.write(in.readAllBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (BufferedInputStream inJpg = new BufferedInputStream(new FileInputStream("data/1.jpg"));
             BufferedOutputStream outJpg = new BufferedOutputStream(new FileOutputStream("data/2.jpg"))) {
            outJpg.write(inJpg.readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}