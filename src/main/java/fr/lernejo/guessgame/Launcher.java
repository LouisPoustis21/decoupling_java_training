package fr.lernejo.guessgame;

import fr.lernejo.logger.LoggerFactory;

import java.security.SecureRandom;

public class Launcher {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("""
                Usage:
                -interactive : for manual play with unlimited guesses
                -auto <number> : for automatic play with a robot (1000 guesses limit)
                """);
            return;
        }

        if (args[0].equals("-interactive")) {
            SecureRandom random = new SecureRandom();
            long randomNumber = random.nextInt(100);

            Simulation simulation = new Simulation(new HumanPlayer());
            simulation.initialize(randomNumber);
            simulation.loopUntilPlayerSucceed(Long.MAX_VALUE);
        } else if (args[0].equals("-auto") && args.length == 2) {
            try {
                long numberToGuess = Long.parseLong(args[1]);
                Simulation simulation = new Simulation(new ComputerPlayer());
                simulation.initialize(numberToGuess);
                simulation.loopUntilPlayerSucceed(1000);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format for auto mode. Please provide a valid number.");
            }
        } else {
            System.out.println("""
                Usage:
                -interactive : for manual play with unlimited guesses
                -auto <number> : for automatic play with a robot (1000 guesses limit)
                """);
        }
    }
}

