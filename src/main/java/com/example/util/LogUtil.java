package com.example.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil {
    private static final Logger customLogger = LoggerFactory.getLogger("CUSTOM_LOGGER_FORMAT");

    public static void log(String message) {
        customLogger.info(message);
    }
}
