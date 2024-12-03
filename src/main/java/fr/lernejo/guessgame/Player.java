package fr.lernejo.guessgame;

public interface Player {
    long askNextGuess();

    /**
     * Called by {@link Simulation} to inform if the previous guess was lower or greater than the number to find.
     */
    void respond(boolean lowerOrGreater);
}

