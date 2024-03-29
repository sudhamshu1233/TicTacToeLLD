package Models;

public class Move {
    private int moveNumber;
    private Cell cell;

    public Move(Cell cell)
    {

        this.cell = cell;
    }

    public int getMoveNumber() {
        return moveNumber;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public void setMoveNumber(int moveNumber) {
        this.moveNumber = moveNumber;
    }
}
