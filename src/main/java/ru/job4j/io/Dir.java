package ru.job4j.io;

import java.io.File;
import java.util.Objects;

public class Dir {
    public static void main(String[] args) {
        File file = new File("c:\\projects\\jobj4_design");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
            for (File subfile : Objects.requireNonNull(file.listFiles())) {
                if (subfile.exists() && !subfile.isDirectory()) {
                    System.out.printf("Название файла - %s, Размер файла, в байтах - %s%n",
                            subfile.getName(), subfile.length());
                }
            }
    }
}