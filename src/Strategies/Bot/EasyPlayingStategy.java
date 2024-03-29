package Strategies.Bot;

import Models.Board;
import Models.Cell;
import Models.Move;
import projectenums.cellstatus;

import java.util.List;

public class EasyPlayingStategy implements BotPlayingStrategies {


    @Override
    public Move suggestMove(Board board) {
        List<List<Cell>> Cells = board.getBoard();
        for(int row=0;row<Cells.size();row++)
        {
            for(int column = 0; column<Cells.get(row).size();column++)
            {
                Cell currentCell = Cells.get(row).get(column);
                if(currentCell.getCellStatus() == cellstatus.EMPTY)
                {
                    return new Move(currentCell);
                }

            }
        }
        return null;
    }
}
