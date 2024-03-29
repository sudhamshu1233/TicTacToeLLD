package Models;

import projectenums.cellstatus;

public class Cell {
    private Player currentplayerpresent;

    private cellstatus currentcellstatus;

    private int row;
    private int column;

    public Cell(int row, int col) {
        this.row = row;
        this.column = col;
        currentcellstatus = cellstatus.EMPTY;
    }
    public Cell(Player p, cellstatus c,int row, int column)
    {
        this.currentplayerpresent = p;
        this.currentcellstatus = c;
        this.row = row;
        this.column = column;
    }

    public Player getPlayer()
    {
        return this.currentplayerpresent;
    }

    public cellstatus getCellStatus()
    {
        return currentcellstatus;
    }

    public int getRow()
    {
        return this.row;
    }
    public int getColumn()
    {
        return this.column;
    }

    public void setCurrentcellstatus(cellstatus currentcellstatus) {
        this.currentcellstatus = currentcellstatus;
    }

    public void setCurrentplayerpresent(Player currentplayerpresent) {
        this.currentplayerpresent = currentplayerpresent;
    }


}
