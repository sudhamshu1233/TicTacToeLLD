package Controllers;

import Models.Board;
import Models.Game;
import Models.Player;
import Service.GameService;
import projectenums.GameStatus;


import java.util.List;

public class GameController {
    GameService currentGameService;

    public GameController()
    {
        currentGameService = new GameService();
    }
    public Game buildGame(int dimension, List<Player> playerList)
    {

        return currentGameService.buildGame(dimension, playerList);

    }

    public void makeNextMove() throws IllegalArgumentException
    {
        currentGameService.makeNextMove();
    }

    public void undoLastMove()
    {
        currentGameService.undo();
    }

    public GameStatus getCurrentGameStatus()
    {
        return currentGameService.getCurrentGameStatus();
    }

    public void displayCurrentGameBoard()
    {
        currentGameService.displayCurrentGameBoard();
    }


}
