package es.art83.ticTacToe.controllers;

import java.util.List;

import es.art83.ticTacToe.models.entities.CoordinateEntity;
import es.art83.ticTacToe.models.entities.PieceEntity;
import es.art83.ticTacToe.models.utils.ColorModel;

public interface ShowGameController {

    String gameName();

    List<PieceEntity> piecesOnBoard();

    ColorModel gameOver();

    ColorModel turnColor();

    boolean hasAllPieces();

    List<CoordinateEntity> validSourceCoordinates();

    List<CoordinateEntity> validDestinationCoordinates();

    boolean existGame();
    
    List<String> gameNamesOfPlayer();

}
