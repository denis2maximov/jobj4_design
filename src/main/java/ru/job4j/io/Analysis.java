package ru.job4j.io;

import java.io.*;
import java.util.*;

public class Analysis {
    public void unavailable(String source, String target) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             BufferedWriter writer = new BufferedWriter(new FileWriter(target))) {
            List<String> list = new ArrayList<>();
            reader.lines().filter(s -> !s.startsWith("200"))
                    .map(s -> s.split(" "))
                    .map(s -> s[1])
                    .forEach(list::add);
            writer.write(list.get(0) + " ; " + list.get(list.size() - 1));
        }
    }

    public static void main(String[] args) throws IOException {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}