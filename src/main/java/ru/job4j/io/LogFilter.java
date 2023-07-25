package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    public List<String> filter(String file) {
        List<String> list = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            while (in.read() != -1) {
            String str = in.readLine();
            String[] strings = str.split(" ");
            if ("404".equals(strings[strings.length - 2])) {
                list.add(str);
            }
       }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(new FileOutputStream(file)))) {
              for (String string : log) {
                  out.printf("%s%n", string);
              }
          } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

        public static void main(String[] args) throws IOException {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("data/log.txt");
        save(log, "data/404.txt");
    }
}