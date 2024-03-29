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

    public void displayBoard()
    {
        for(int row=0;row<board.size();row++) {


            for (int column = 0; column < board.getFirst().size(); column++) {

                System.out.print("|");
                Player currentPlayerInCell = board.get(row).get(column).getPlayer();
                if(currentPlayerInCell!=null)
                {
                    System.out.print(currentPlayerInCell.symbol);
                }
                else
                {
                    System.out.print("-");
                }



            }
            System.out.print("|\n");
        }
    }

    public void serBoard(List<List<Cell>> board)
    {
        this.board = board;
    }
}
