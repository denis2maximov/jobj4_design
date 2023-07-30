package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new LinkedHashMap<>();
    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().filter(s -> !s.contains("#") && !s.isEmpty())
                    .map(s -> s.split("=", 2))
                    .filter(this::checkMatches)
                    .forEach(k -> values.put(k[0], k[1]));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean checkMatches(String[] strings) {
        if (strings.length < 2 || strings[0].isEmpty() || strings[1].isEmpty()) {
            throw new IllegalArgumentException("doesn't match the template!!");
        }
        return true;
}

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) throws IOException {
        Config config = new Config("data/app.properties");
            config.load();
        System.out.println(config.value("hibernate.connection.username"));
        System.out.println(config.value("hibernate.connection.password"));
    }
    }
