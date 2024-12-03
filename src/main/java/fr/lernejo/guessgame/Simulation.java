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

    private boolean nextRound() {
        long guess = player.askNextGuess();
        if (guess == numberToGuess) {
            logger.log("Correct! The number was " + numberToGuess);
            return true;
        } else {
            boolean isLower = guess > numberToGuess;
            player.respond(isLower);
            return false;
        }
    }

    public void loopUntilPlayerSucceed(long maxIterations) {
        long startTime = System.currentTimeMillis();
        boolean success = false;

        for (long i = 0; i < maxIterations && !success; i++) {
            success = nextRound();
        }

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        if (success) {
            logger.log("You won! Game duration: " + (duration / 1000) + "s " + (duration % 1000) + "ms");
        } else {
            logger.log("loose! You did not guess the number within " + maxIterations + " attempts.");
        }
    }
}

