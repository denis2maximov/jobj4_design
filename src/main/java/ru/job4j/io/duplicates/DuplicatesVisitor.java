package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private static Map<FileProperty, List<Path>> map = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(Files.size(file.toAbsolutePath()), file.getFileName().toString());
        if (!map.containsKey(fileProperty) && attrs.isRegularFile()) {
            List<Path> list = new ArrayList<>();
            list.add(file.toAbsolutePath());
            map.put(fileProperty, list);
        } else {
            map.get(fileProperty).add(file.toAbsolutePath());
        }

        return super.visitFile(file, attrs);
    }

    public static void print() {
        map.values().stream()
                .filter(v -> v.size() > 1)
                .forEach((v) -> {
                    for (Map.Entry<FileProperty, List<Path>> pair : map.entrySet()) {
                        if (v.equals(pair.getValue())) {
                            System.out.println("Name of the duplicate: "
                                    + pair.getKey().getName()
                                    + " Size, bytes: "
                                    + pair.getKey().getSize());
                            List<Path> list = pair.getValue();
                            for (Path path : list) {
                                System.out.println("-- " + path.toString());
                            }
                            System.out.println();
                        }
                    }
                });
            }
  }



