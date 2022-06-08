package com.tistory.shanepark.file.stream;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * ## FileInputStream Test
 * once open a inputStream, it doesn't matter even if the original file is removed
 * <p>
 * 1. open a file stream
 * 2. delete the original file
 * 3. copy the file from input stream
 * 4. rename it to original one
 */
@Slf4j
public class FileInputStreamTest {

    public static void main(String[] args) throws IOException {
        final String ORIGINAL = "/home/shane/Downloads/test.jpg";
        final String TARGET = "/home/shane/Downloads/targetFile.jpg";
        File file = new File(ORIGINAL);
        try (InputStream inputStream = Files.newInputStream(file.toPath())
        ) {
            if (file.delete()) {
                log.info("Original file deleted");
            } else {
                log.info("Failed deleting the original file");
            }

            for (int i = 0; i < 10; i++) {
                log.info("waits {}/10s", i + 1);
                Thread.sleep(1000);
            }

            File targetFile = new File(TARGET);
            Files.copy(inputStream, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

            if (targetFile.isFile()) {
                log.info("target file created");
                if (targetFile.renameTo(file)) {
                    log.info("target file renamed");
                } else {
                    log.info("target file rename failed");
                }
            } else {
                log.info("{} is not a file", targetFile);
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
