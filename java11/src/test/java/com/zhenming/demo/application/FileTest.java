package com.zhenming.demo.application;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

class FileTest {
    private static final Logger LOG = LoggerFactory.getLogger(FileTest.class);

    @Test
    void writeString() throws IOException {
        Path path = Paths.get("fileTest.txt");
        Files.writeString(path, "\nHello World\t" + UUID.randomUUID());
        Assertions.assertTrue(true);
    }

    @Test
    void readString() throws IOException {
        Path path = Paths.get("fileTest.txt");
        if (path.toFile().exists()) {
            LOG.info("read file string = \n[{}]", Files.readString(path));
        } else {
            Assertions.fail("file not found");
        }
    }

    @Test
    void isSameFile() throws IOException {
        Path path = Paths.get("fileTest.txt");
        LOG.info("isSameFile result = {}", Files.isSameFile(path, Paths.get("fileTest.txt")));
        Assertions.assertTrue(true);
    }
}
