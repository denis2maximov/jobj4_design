package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (values.get(key) == null) {
            throw new IllegalArgumentException(String.format("This key: '%s' is missing",  key));
        }
        return values.get(key);
    }
    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        for (String string : args) {
            String[] st1 = validation(string);
            values.put(st1[0], st1[1]);
        }
    }

    private static String[] validation(String string) {
        if (!string.startsWith("-")) {
            throw new IllegalArgumentException(String.format("Error: This argument '%s' does not start with a '-' character",
                    string));
        }
        if (!string.contains("=")) {
            throw new IllegalArgumentException(String.format("Error: This argument '%s' does not contain an equal sign",
                    string));
        }
        String[] stringArray = string.substring(1).split("=", 2);
        if (stringArray[0].isEmpty()) {
            throw new IllegalArgumentException(String.format("Error: This argument '%s' does not contain a key", string));
        }
        if (stringArray[1].isEmpty()) {
            throw new IllegalArgumentException(String.format("Error: This argument '%s' does not contain a value",  string));
        }
        return stringArray;
    }
    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }
    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));
        System.out.println(jvm.get("encoding"));
        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
