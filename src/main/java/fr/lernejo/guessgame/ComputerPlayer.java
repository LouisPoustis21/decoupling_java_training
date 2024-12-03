package fr.lernejo.guessgame;

import fr.lernejo.logger.Logger;
import fr.lernejo.logger.LoggerFactory;

public class ComputerPlayer implements Player {
    private final Logger logger = LoggerFactory.getLogger("player");
    private long lowerBound = Long.MIN_VALUE;
    private long upperBound = Long.MAX_VALUE;

    @Override
    public long askNextGuess() {
        long guess = lowerBound + (upperBound - lowerBound) / 2;
        logger.log("Computer guesses: " + guess);
        return guess;
    }

    @Override
    public void respond(boolean lowerOrGreater) {
        if (lowerOrGreater) {
            upperBound = lowerBound + (upperBound - lowerBound) / 2;
        } else {
            lowerBound = lowerBound + (upperBound - lowerBound) / 2 + 1;
        }
    }
}

