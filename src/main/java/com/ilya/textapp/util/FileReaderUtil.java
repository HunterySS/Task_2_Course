package com.ilya.textapp.util;

import com.ilya.textapp.exception.TextProcessingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class FileReaderUtil {
    private static final Logger LOGGER = LogManager.getLogger(FileReaderUtil.class);

    public static String readFile(String filePath) throws TextProcessingException {
        LOGGER.debug("Reading file: {}", filePath);

        if (filePath == null || filePath.isBlank()) {
            LOGGER.error("File path is null or empty");
            throw new TextProcessingException("File path cannot be null or empty");
        }

        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) {

            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }

            LOGGER.info("File read successfully. Size: {} characters", content.length());

        } catch (Exception e) {
            LOGGER.error("Failed to read file: {}", filePath, e);
            throw new TextProcessingException("Failed to read file: " + filePath, e);
        }

        return content.toString().strip();
    }
}