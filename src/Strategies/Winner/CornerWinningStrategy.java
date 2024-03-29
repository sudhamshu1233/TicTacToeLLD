package Strategies.Winner;

import Models.Board;
import Models.Move;
import Models.Player;

public class CornerWinningStrategy implements WinningStrategies{


    @Override
    public Player getWinner(Board board, Move previousMove) {
        int []cornerrows = new int[]{0,0,board.getBoard().size()-1,board.getBoard().size()-1};
        int []cornercolumns = new int[]{0,board.getBoard().getFirst().size()-1,0,board.getBoard().getFirst().size()-1};
        Player currplayer = previousMove.getCell().getPlayer();
        for(int i = 0;i<4;i++)
        {

            int row = cornerrows[i];
            int column = cornercolumns[i];
            Player player = board.getBoard().get(row).get(column).getPlayer();
            if(player==null)
            {
                return null;
            }
            if(!player.equals(currplayer))
            {
                return null;
            }

        }
        return currplayer;
    }
}
