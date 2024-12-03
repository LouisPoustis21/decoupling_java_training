package fr.lernejo.guessgame;

import fr.lernejo.logger.Logger;
import fr.lernejo.logger.LoggerFactory;

public class Simulation {
    private final Logger logger = LoggerFactory.getLogger("simulation");
    private final Player player;
    private long numberToGuess;

    public Simulation(Player player) {
        this.player = player;
    }

    public void initialize(long numberToGuess) {
        this.numberToGuess = numberToGuess;
    }

    /**
     * @return true if the player guessed the correct number.
     */
    private boolean nextRound() {
        long guess = player.askNextGuess();
        if (guess == numberToGuess) {
            logger.log("Correct! The number was " + numberToGuess);
            return true;
        } else {
            boolean lowerOrGreater = guess < numberToGuess;
            player.respond(lowerOrGreater);
            return false;
        }
    }

    public void loopUntilPlayerSucceed(long maxIterations) {
        long startTime = System.currentTimeMillis();
        for (long i = 0; i < maxIterations; i++) {
            if (nextRound()) {
                long elapsedTime = System.currentTimeMillis() - startTime;
                logger.log("You won! Time taken: " + formatElapsedTime(elapsedTime));
                return;
            }
        }
        logger.log("Game over! You ran out of attempts.");
    }

    private String formatElapsedTime(long milliseconds) {
        long seconds = milliseconds / 1000;
        long minutes = seconds / 60;
        seconds = seconds % 60;
        long millis = milliseconds % 1000;
        return String.format("%02d:%02d.%03d", minutes, seconds, millis);
    }
}

