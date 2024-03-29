package Strategies.Winner;

import Models.Board;
import Models.Bot;
import Models.Move;
import Models.Player;

public interface WinningStrategies {
    Player getWinner(Board board, Move previousMove);
}
