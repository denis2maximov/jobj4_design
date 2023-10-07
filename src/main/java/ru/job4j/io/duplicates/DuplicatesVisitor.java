package ru.job4j.io.duplicates;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.stream.Collectors;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final Map<FileProperty, List<Path>> map = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(attrs.size(), file.getFileName().toString());
        map.computeIfAbsent(fileProperty, v -> new ArrayList<>()).add(file.toAbsolutePath());
        return super.visitFile(file, attrs);
    }

    public void print() {
        List<Path> lists;
        String ls = System.lineSeparator();
        for (Map.Entry<FileProperty, List<Path>> pair : map.entrySet()) {
            lists = pair.getValue();
            if (lists.size() > 1) {
                System.out.printf("%s Name of the duplicate: %s  Size, bytes: %s%s", ls,
                        pair.getKey().getName(), pair.getKey().getSize(), ls);
                lists.forEach(p -> System.out.printf("-- %s%s", p, ls));
            }
        }

    }
}




