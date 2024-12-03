package fr.lernejo.logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ContextualLogger implements Logger {
    private final String callerClass;
    private final Logger delegateLogger;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    public ContextualLogger(String callerClass, Logger delegateLogger) {
        this.callerClass = callerClass;
        this.delegateLogger = delegateLogger;
    }

    @Override
    public void log(String message) {
        String timestamp = LocalDateTime.now().format(formatter);
        delegateLogger.log(timestamp + " " + callerClass + " " + message);
    }
}

