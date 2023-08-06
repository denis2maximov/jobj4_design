package ru.job4j.io;

import java.io.*;

public class Analysis {

    public void unavailable(String source, String target) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             BufferedWriter writer = new BufferedWriter(new FileWriter(target))) {
            boolean work = true;
            String string;
            while ((string = reader.readLine()) != null) {
                String[] arrayString = string.split(" ");
                boolean status = string.startsWith("400") || string.startsWith("500");
                if (status == work) {
                    writer.append(arrayString[1]).append(";").append(work ? "" : System.lineSeparator());
                    work = !work;
                }
            }
        }
    }

            public static void main(String[]args) throws IOException {
                Analysis analysis = new Analysis();
                analysis.unavailable("data/server.log", "data/target.csv");
                analysis.unavailable("data/server1.log", "data/target1.csv");
            }
        }

