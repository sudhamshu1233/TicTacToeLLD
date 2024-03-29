package Models;

import projectenums.cellstatus;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<List<Cell>> board;

    public Board(int dimensions)
    {
        this.board = new ArrayList<>();
        for(int row=0;row<dimensions;row++)
        {
            this.board.add(new ArrayList<>());
            for(int column=0;column<dimensions;column++)
            {
                board.get(row).add(new Cell(row,column));
            }
        }
    }

    public List<List<Cell>> getBoard()
    {
        return board;
    }


    public void serBoard(List<List<Cell>> board)
    {
        this.board = board;
    }
}
