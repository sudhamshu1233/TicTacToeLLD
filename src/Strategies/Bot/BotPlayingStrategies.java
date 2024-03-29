package Strategies.Bot;

import Models.Board;
import Models.Move;

public interface BotPlayingStrategies {
    Move suggestMove(Board board);

}
