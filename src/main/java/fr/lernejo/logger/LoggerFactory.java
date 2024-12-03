package fr.lernejo.logger;

import java.util.function.Predicate;

public class LoggerFactory {
    public static Logger getLogger(String name) { 
        Logger consoleLogger = new ConsoleLogger();
        Logger contextualConsoleLogger = new ContextualLogger(name, consoleLogger);
        Logger fileLogger = new FileLogger("target/captain.log");
        Predicate<String> simulationFilter = message -> message.contains("Simulation");
        Logger filteredFileLogger = new FilteredLogger(new ContextualLogger(name, fileLogger), simulationFilter);

       
        return new CompositeLogger(contextualConsoleLogger, filteredFileLogger);
    }
}

