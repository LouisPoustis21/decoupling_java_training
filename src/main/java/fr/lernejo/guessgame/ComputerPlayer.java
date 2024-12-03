package fr.lernejo.guessgame;

import fr.lernejo.logger.Logger;
import fr.lernejo.logger.LoggerFactory;

public class ComputerPlayer implements Player {
    private final Logger logger = LoggerFactory.getLogger("player");
    private long min = Long.MIN_VALUE;
    private long max = Long.MAX_VALUE;

    @Override
    public long askNextGuess() {
        long guess = (min + max) / 2;
        logger.log("Computer guesses: " + guess);
        return guess;
    }

    @Override
    public void respond(boolean lowerOrGreater) {
        if (lowerOrGreater) {
            max = (min + max) / 2 - 1;
            logger.log("The guess was too high. Adjusting range to [" + min + ", " + max + "]");
        } else {
            min = (min + max) / 2 + 1;
            logger.log("The guess was too low. Adjusting range to [" + min + ", " + max + "]");
        }
    }
}

