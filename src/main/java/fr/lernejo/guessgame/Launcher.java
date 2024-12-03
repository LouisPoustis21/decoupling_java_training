package fr.lernejo.guessgame;

import java.security.SecureRandom;

public class Launcher {
    public static void main(String[] args) {
        SecureRandom random = new SecureRandom();
        long randomNumber = random.nextInt(100); 

        Player player = new HumanPlayer();
        Simulation simulation = new Simulation(player);

        simulation.initialize(randomNumber);
        simulation.loopUntilPlayerSucceed();
    }
}

