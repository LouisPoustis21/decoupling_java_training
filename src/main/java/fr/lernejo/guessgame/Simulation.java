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
            logger.log("Congratulations! You guessed the number!");
            return true;
        } else if (guess > numberToGuess) {
            player.respond(true); 
        } else {
            player.respond(false); 
        }
        return false;
    }

    public void loopUntilPlayerSucceed(long maxIterations) {
    long startTime = System.currentTimeMillis();
    boolean success = false;
    long iterations = 0;

    while (iterations < maxIterations) {
        iterations++;
        if (nextRound()) {
            success = true;
            break;
        }
    }

    long endTime = System.currentTimeMillis();
    long durationMillis = endTime - startTime;

    long minutes = (durationMillis / 1000) / 60;
    long seconds = (durationMillis / 1000) % 60;
    long milliseconds = durationMillis % 1000;

    logger.log(String.format("Game finished in %d iterations and %02d:%02d.%03d",
        iterations, minutes, seconds, milliseconds));
    if (success) {
        logger.log("win");
    } else {
        logger.log("perdue trop de temps");
    }
}

