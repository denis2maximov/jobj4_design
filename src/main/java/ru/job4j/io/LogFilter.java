package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    public List<String> filter(String file) throws IOException {
        List<String> list = new ArrayList<>();
        BufferedReader in = new BufferedReader(new FileReader(file));
        while (in.read() != -1) {
            list.add(in.readLine());
        }
        return list.stream().filter(s -> s.contains(" " + "404" + " ")).toList();
    }
    public static void main(String[] args) throws IOException {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("data/log.txt");
        log.forEach(System.out::println);
    }
}