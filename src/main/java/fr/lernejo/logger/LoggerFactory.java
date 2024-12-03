package fr.lernejo.logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Predicate;

public class LoggerFactory {
    public static Logger getLogger(String name) {
        Logger consoleLogger = new ConsoleLogger();
        Logger contextualLogger = new ContextualLogger(name, consoleLogger);

        Logger fileLogger = new FileLogger("target/captain.log");
        Logger filteredFileLogger = new FilteredLogger(fileLogger, message -> !message.contains("player"));

        return new CompositeLogger(contextualLogger, filteredFileLogger);
    }
}

