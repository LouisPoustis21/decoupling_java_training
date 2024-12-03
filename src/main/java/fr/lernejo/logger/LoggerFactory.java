package fr.lernejo.logger;

import java.util.function.Predicate;

public class LoggerFactory {

    public static Logger getLogger(String name) {
        Logger consoleLogger = new ContextualLogger(name, new ConsoleLogger());
        Logger fileLogger = new FilteredLogger(
            new ContextualLogger(name, new FileLogger("target/captain.log")),
            message -> message.contains("Simulation")
        );

        return new CompositeLogger(consoleLogger, fileLogger);
    }
}

