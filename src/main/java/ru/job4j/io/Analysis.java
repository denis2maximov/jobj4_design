package ru.job4j.io;

import java.io.*;
import java.text.Format;
import java.util.Arrays;
import java.util.function.Predicate;

public class Analysis {

    public void unavailable(String source, String target) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             BufferedWriter writer = new BufferedWriter(new FileWriter(target))) {
            boolean work = true;
            String string;
            StringBuilder stringBuilder = new StringBuilder();
            while ((string = reader.readLine()) != null) {
                String[] arrayString = string.split(" ");
                if ((((arrayString[0].equals("400")
                        || arrayString[0].equals("500")) && work) || (arrayString[0].equals("200")
                        || arrayString[0].equals("300")) && !work)) {
                    stringBuilder.append(arrayString[1]).append(";");
                   work = !work;
                }
            }
                String[] stringOut = stringBuilder.toString().split(";");
            for (int i = 0; i < stringOut.length - 1; i = i + 2) {
                writer.append(stringOut[i]).append(";").append(stringOut[i + 1]).append(";");
                writer.write(System.lineSeparator());
            }
            }
        }

            public static void main(String[]args) throws IOException {
                Analysis analysis = new Analysis();
                analysis.unavailable("data/server.log", "data/target.csv");
                analysis.unavailable("data/server1.log", "data/target1.csv");
            }
        }

