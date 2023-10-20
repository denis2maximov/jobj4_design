package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

public class Search {

    public static void main(String[] args) throws IOException {
        new Search().validation(args);
        search(Path.of(args[0]), p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }
     public void validation(String[] args) {
         if (args.length != 2) {
             throw new IllegalArgumentException("The wrong number of arguments was passed.");
         }
         File file = new File(args[0]);
         if (!file.isDirectory()) {
             throw new IllegalArgumentException("Directory does not exist or is not a directory");
         }

         boolean result = args[1].matches("\\.\\w+$");
         if (!args[1].isEmpty() && !result) {
             throw new IllegalArgumentException("This is not a file extension");
         }
     }
    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
  }

