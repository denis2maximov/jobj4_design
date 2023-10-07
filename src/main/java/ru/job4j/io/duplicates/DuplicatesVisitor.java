package ru.job4j.io.duplicates;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final Map<FileProperty, List<Path>> map = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(attrs.size(), file.getFileName().toString());
        map.computeIfAbsent(fileProperty, v -> new ArrayList<>()).add(file.toAbsolutePath());
        return super.visitFile(file, attrs);
    }

    public void print() {
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




