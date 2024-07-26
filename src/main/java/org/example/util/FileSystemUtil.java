package org.example.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileSystemUtil {

    public static void clearDirectory(String directoryPath) {
        Path dirPath = Paths.get(directoryPath);
        try {
            if (!Files.exists(dirPath)) {
                Files.createDirectories(dirPath);
                return;
            }
            try (Stream<Path> paths = Files.walk(dirPath)) {
                paths.sorted(Comparator.reverseOrder())
                        .map(Path::toFile)
                        .forEach(File::delete);
            }
        } catch (IOException e) {
            System.err.println("Failed to clear directory: " + directoryPath + ". Error: " + e.getMessage());
        }
    }

    public static boolean waitForFileDownload(String downloadPath, String expectedFileName, int timeoutInSeconds) {
        Path filePath = Paths.get(downloadPath, expectedFileName);
        long endTime = System.currentTimeMillis() + timeoutInSeconds * 1000L;
        while (System.currentTimeMillis() < endTime) {
            if (Files.exists(filePath)) {
                return true;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            }
        }
        return Files.exists(filePath);
    }

    public static Set<String> getCurrentFilesInDirectory(String directoryPath) {
        Set<String> files = Collections.emptySet();
        Path dirPath = Paths.get(directoryPath);
        try {
            ensureDirectoryExists(directoryPath);
            try (Stream<Path> stream = Files.list(dirPath)) {
                files = stream
                        .filter(Files::isRegularFile)
                        .map(path -> path.getFileName().toString())
                        .collect(Collectors.toSet());
            }
        } catch (IOException e) {
            System.err.println("Failed to list files in directory: " + directoryPath + ". Error: " + e.getMessage());
        }
        return files;
    }

    private static void ensureDirectoryExists(String directoryPath) throws IOException {
        Path path = Paths.get(directoryPath);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
    }
}
