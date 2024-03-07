package com.ark.code.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;

public class MyFileReader {
    private static Logger LOGGER = LoggerFactory.getLogger(MyFileReader.class);
    
    public static void main(String[] args) {
        LOGGER.debug("Starting program ..");
        Instant start = Instant.now();
        LOGGER.debug("Started at "+start);
    }
}
