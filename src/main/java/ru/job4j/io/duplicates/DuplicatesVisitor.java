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

import static java.util.stream.Collectors.groupingBy;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private HashMap<Path, FileProperty> map =  new HashMap<>();
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
       FileProperty fileProperty = new FileProperty(Files.size(file), file.getFileName().toString());

       if (attrs.isRegularFile()) {
           map.put(file.toAbsolutePath(), fileProperty);
       }

        Map<FileProperty, List<Path>> rsl = map.keySet().stream()
                .collect(groupingBy(map::get));

                 rsl.values().stream()
                                .filter(v -> v.size() > 1)
                         .forEach((v) -> {
                                     for (Map.Entry<FileProperty, List<Path>> pair : rsl.entrySet()) {
                                         if (v.equals(pair.getValue())) {
                                             System.out.println("Name of the duplicate: "
                                                     + pair.getKey().getName()
                                                     + "Size, bytes: "
                                                     + pair.getKey().getSize());
                                             List<Path> list = pair.getValue();
                                             for (Path path : list) {
                                                 System.out.println("-- " + path.toString());
                                             }
                                             System.out.println();
                                         }
                                     }
                                 });

        return super.visitFile(file, attrs);
     }
    }


