package Strategies.Winner;

import Models.Board;
import Models.Move;
import Models.Player;

public class ColumnWinningStrategies implements WinningStrategies{

    @Override
    public Player getWinner(Board board, Move previousMove) {
        int column = previousMove.getCell().getColumn();
        for(int row = 0;row<board.getBoard().size();row++)
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
