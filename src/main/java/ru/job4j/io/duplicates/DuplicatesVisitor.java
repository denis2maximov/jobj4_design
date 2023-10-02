package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private static Map<Path, FileProperty> map = new HashMap<>();
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(Files.size(file.toAbsolutePath()), file.getFileName().toString());
        map.put(file.toAbsolutePath(), fileProperty);
        return super.visitFile(file, attrs);
    }

    public static Map<FileProperty, List<Path>> filter() {
        Map<FileProperty, List<Path>> map2 = map.keySet().stream()
                .collect(groupingBy(map::get));
        return map2.entrySet().stream()
                .filter(v -> v.getValue().size() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
    public static void print() {
        DuplicatesVisitor.filter().values().stream()
                .filter(v -> v.size() > 1)
                .forEach((v) -> {
                    for (Map.Entry<FileProperty, List<Path>> pair : DuplicatesVisitor.filter().entrySet()) {
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



