package fr.lernejo.logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ContextualLogger implements Logger {
    private final Logger delegateLogger;
    private final String callerClass;
    private final DateTimeFormatter formatter;

    public ContextualLogger(String callerClass, Logger delegateLogger) {
        this.callerClass = callerClass;
        this.delegateLogger = delegateLogger;
        this.formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    }

    @Override
    public void log(String message) {
        String enrichedMessage = LocalDateTime.now().format(formatter) + " " + callerClass + " " + message;
        delegateLogger.log(enrichedMessage);
    }
}

