package ru.job4j.io;

import java.io.IOException;
import java.nio.file.*;

import static java.nio.file.Files.newDirectoryStream;

public class PathExample {
    public static void main(String[] args) throws IOException {
        Path dir = Paths.get("path/paths");
        Files.createDirectories(dir);
        Path path = Path.of("path/paths/path.txt");
        Files.createFile(path);
        System.out.println("Файл/директория существует?: " + Files.exists(path));
       System.out.println("Это директория?: " + Files.isDirectory(path));
        System.out.println("Это файл?: " + Files.isRegularFile(path));
        System.out.println("Имя файла: " + path.getFileName());
        System.out.println("Путь к файлу абсолютный?: " + path.isAbsolute());
       System.out.println("Родительская директория файла: " + path.getParent());
        System.out.println("Абсолютный путь к файлу: " + path.toAbsolutePath());
       System.out.println("Абсолютный путь к директории: " + dir.toAbsolutePath());
        System.out.println("Доступен для чтения?: " + Files.isReadable(path));
       System.out.println("Доступен для записи?: " + Files.isWritable(path));
        Files.move(path, Path.of("path/path.txt"));
        Files.delete(Path.of("path/path.txt"));
        Files.delete(Path.of("path/paths"));
        Files.delete(Path.of("path"));
        Path dir1 = Paths.get("path1/paths1");
        Files.createDirectories(dir1);
        Path path1 = FileSystems.getDefault().getPath( "path1", "paths1", "access.log");
        Files.createFile(path1);
        Path path2 = Path.of("path1/paths1/path1.txt");
        Files.createFile(path2);
        System.out.println("Файл/директория существует?: " + Files.exists(path2));
        System.out.println("Это директория?: " + Files.isDirectory(path2));
        System.out.println("Это файл?: " + Files.isRegularFile(path2));
        System.out.println("Имя файла: " + path2.getFileName());
        System.out.println("Путь к файлу абсолютный?: " + path2.isAbsolute());
        System.out.println("Родительская директория файла: " + path2.getParent());
        System.out.println("Абсолютный путь к файлу: " + path2.toAbsolutePath());
        System.out.println("Абсолютный путь к директории: " + dir1.toAbsolutePath());
        System.out.println("Доступен для чтения?: " + Files.isReadable(path2));
        System.out.println("Доступен для записи?: " + Files.isWritable(path2));
        try (DirectoryStream<Path> paths = newDirectoryStream(dir1)) {
            paths.forEach(System.out::println);
        }
    }
}