package fr.lernejo.guessgame;

import fr.lernejo.logger.Logger;
import fr.lernejo.logger.LoggerFactory;

import java.security.SecureRandom;

public class Launcher {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger("Launcher");

        if (args.length < 1) {
            logger.log("Usage:");
            logger.log(" -interactive: Play interactively with a human player.");
            logger.log(" -auto <number>: Play automatically with the computer player, guessing the given number.");
            return;
        }

        if ("-interactive".equals(args[0])) {
            logger.log("Launching interactive mode...");
            HumanPlayer humanPlayer = new HumanPlayer();
            Simulation simulation = new Simulation(humanPlayer);
            SecureRandom random = new SecureRandom();
            long randomNumber = random.nextInt(100); 
            simulation.initialize(randomNumber);
            simulation.loopUntilPlayerSucceed(Long.MAX_VALUE); 
        } else if ("-auto".equals(args[0]) && args.length == 2) {
            try {
                long targetNumber = Long.parseLong(args[1]);
                logger.log("Launching auto mode...");
                ComputerPlayer computerPlayer = new ComputerPlayer();
                Simulation simulation = new Simulation(computerPlayer);
                simulation.initialize(targetNumber);
                simulation.loopUntilPlayerSucceed(1000); 
            } catch (NumberFormatException e) {
                logger.log("Invalid number format for the target number.");
            }
        } else {
            logger.log("Invalid arguments. Usage:");
            logger.log(" -interactive: Play interactively with a human player.");
            logger.log(" -auto <number>: Play automatically with the computer player, guessing the given number.");
        }
    }
}

