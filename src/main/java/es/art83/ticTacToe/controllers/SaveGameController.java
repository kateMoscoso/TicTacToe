package es.art83.ticTacToe.controllers;

public interface SaveGameController {
    void saveGame();

    void saveGame(String gameName);

    void overwriteGame(String gameName);

}
