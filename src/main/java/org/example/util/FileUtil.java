package org.example.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtil {

    public static String readFileContent(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            System.err.println("Error reading file content: " + e.getMessage());
            return "";
        }
    }

    public static boolean validateJsonContent(String content) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.readTree(content);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
