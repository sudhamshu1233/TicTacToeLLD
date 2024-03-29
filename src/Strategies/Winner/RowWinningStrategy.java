package Strategies.Winner;

import Models.Board;
import Models.Move;
import Models.Player;

public class RowWinningStrategy implements WinningStrategies{

    @Override
    public Player getWinner(Board board, Move previousMove) {
        int row = previousMove.getCell().getRow();
        for(int column = 0; column<board.getBoard().getFirst().size();column++)
        {
            Player player = previousMove.getCell().getPlayer();
            if(player==null)
            {
                return null;
            }
            if(!player.equals(board.getBoard().get(row).get(column).getPlayer()))
            {
                return null;
            }
        }
        return previousMove.getCell().getPlayer();
    }
}
