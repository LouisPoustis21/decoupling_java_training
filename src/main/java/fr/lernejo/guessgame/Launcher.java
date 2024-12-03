package fr.lernejo.guessgame;

import java.security.SecureRandom;

public class Launcher {
    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("-interactive")) {
            HumanPlayer humanPlayer = new HumanPlayer();
            Simulation simulation = new Simulation(humanPlayer);
            long randomNumber = new SecureRandom().nextInt(100);
            simulation.initialize(randomNumber);
            simulation.loopUntilPlayerSucceed(Long.MAX_VALUE);
        } else if (args.length > 1 && args[0].equals("-auto")) {
            try {
                long targetNumber = Long.parseLong(args[1]);
                ComputerPlayer computerPlayer = new ComputerPlayer();
                Simulation simulation = new Simulation(computerPlayer);
                simulation.initialize(targetNumber);
                simulation.loopUntilPlayerSucceed(1000);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format for auto mode.");
            }
        } else {
            System.out.println("Usage:");
            System.out.println("  -interactive : Play the game manually.");
            System.out.println("  -auto <number> : Let the computer guess the given number.");
        }
    }
}

