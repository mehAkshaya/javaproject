package edu.ccrm.io;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

// This utility handles the backup requirement using NIO.2
public class BackupUtil {

    public void createBackup(String sourceDir, String backupDir) {
        // Use the Date/Time API for the folder name
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HHmmss"));
        Path sourcePath = Paths.get(sourceDir);
        Path destPath = Paths.get(backupDir, "backup-" + timestamp);
        
        try {
            Files.createDirectories(destPath);
            // Walk the source directory and copy files
            try (Stream<Path> stream = Files.walk(sourcePath)) {
                stream.forEach(source -> {
                    try {
                        Files.copy(source, destPath.resolve(sourcePath.relativize(source)));
                    } catch (IOException e) {
                        System.err.println("Unable to copy file: " + source);
                    }
                });
            }
            System.out.println("Backup created at: " + destPath);
        } catch (IOException e) {
            System.err.println("Could not create backup: " + e.getMessage());
        }
    }
    
    // A recursive method to calculate directory size, using Files.walk which is recursive itself.
    public static long calculateDirectorySize(Path path) throws IOException {
        try (Stream<Path> walk = Files.walk(path)) {
            return walk
                .filter(Files::isRegularFile)
                .mapToLong(p -> {
                    try {
                        return Files.size(p);
                    } catch (IOException e) {
                        return 0L;
                    }
                })
                .sum();
        }
    }
}