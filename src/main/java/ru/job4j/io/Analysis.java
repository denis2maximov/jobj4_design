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
                if ((arrayString[0].equals("400") ||  arrayString[0].equals("500")) && work) {
                    writer.append(arrayString[1]).append(";");
                    work = false;
                }

                if ((arrayString[0].equals("200") || arrayString[0].equals("300")) && !work) {
                    writer.append(arrayString[1]).append(";").append(System.lineSeparator());
                    work = true;
                }
            }
        }
            }


    public static void main(String[] args) throws IOException {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
        analysis.unavailable("data/server1.log", "data/target1.csv");
    }
}