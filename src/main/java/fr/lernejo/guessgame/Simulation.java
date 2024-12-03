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

    public void loopUntilPlayerSucceed() {
        boolean success;
        do {
            success = nextRound();
        } while (!success);
        logger.log("Game over!");
    }
}

