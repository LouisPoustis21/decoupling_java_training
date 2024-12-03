package fr.lernejo.guessgame;

import fr.lernejo.logger.Logger;
import fr.lernejo.logger.LoggerFactory;

public class ComputerPlayer implements Player {
    private final Logger logger = LoggerFactory.getLogger("player");
    private long min = 0;
    private long max = 100;
    private long lastGuess;

    @Override
    public long askNextGuess() {
        lastGuess = (min + max) / 2;
        logger.log("Computer guesses: " + lastGuess);
        return lastGuess;
    }

    @Override
    public void respond(boolean lowerOrGreater) {
        if (lowerOrGreater) {
            max = lastGuess - 1;
        } else {
            min = lastGuess + 1;
        }
    }
}

