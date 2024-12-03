package fr.lernejo.guessgame;

public interface Player {
    long askNextGuess();
    void respond(boolean lowerOrGreater);
}

