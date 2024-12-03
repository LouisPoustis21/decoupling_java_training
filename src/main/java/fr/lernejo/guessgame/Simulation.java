package fr.lernejo.guessgame;

import fr.lernejo.logger.Logger;
import fr.lernejo.logger.LoggerFactory;

public class Simulation {

    private final Logger logger = LoggerFactory.getLogger("Simulation");
    private final Player player;
    private long numberToGuess;

    public Simulation(Player player) {
        this.player = player;
    }

    public void initialize(long numberToGuess) {
        this.numberToGuess = numberToGuess;
    }

    private boolean nextRound() {
        long guess = player.askNextGuess();
        if (guess == numberToGuess) {
            logger.log("Correct! The number was " + numberToGuess);
            return true;
        } else if (guess < numberToGuess) {
            logger.log("The number is higher than " + guess);
            player.respond(true);
        } else {
            logger.log("The number is lower than " + guess);
            player.respond(false);
        }
        return false;
    }

    public void loopUntilPlayerSucceed(long maxIterations) {
        long startTime = System.currentTimeMillis();
        int iterations = 0;

        while (iterations < maxIterations) {
            iterations++;
            if (nextRound()) {
                long endTime = System.currentTimeMillis();
                logger.log("You won in " + iterations + " rounds!");
                logger.log("Time taken: " + formatTime(endTime - startTime));
                return;
            }
        }

        long endTime = System.currentTimeMillis();
        logger.log("You lost. Maximum iterations reached (" + maxIterations + ").");
        logger.log("Time taken: " + formatTime(endTime - startTime));
    }

    private String formatTime(long milliseconds) {
        long seconds = (milliseconds / 1000) % 60;
        long minutes = (milliseconds / (1000 * 60)) % 60;
        long millis = milliseconds % 1000;
        return String.format("%02d:%02d.%03d", minutes, seconds, millis);
    }
}

