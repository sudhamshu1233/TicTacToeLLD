package Models;

import projectenums.cellstatus;
import projectenums.playerType;

import java.util.Scanner;

public class Player {
    String Name;
    char symbol;
    playerType p;

    private Scanner scaner;

    public Player(String Name, char symbol, playerType p, Scanner sc)
    {
        this.Name = Name;
        this.p = p;
        this.symbol = symbol;
        this.scaner = sc;
    }

    public playerType getPlayerType()
    {
        return p;
    }

    public String getName()
    {
        return Name;
    }

    public char getSymbol() {
        return symbol;
    }

    public Move makeMove(Board board)
    {
        System.out.println("Make move player "+this.Name);
        System.out.println("Enter row...");
        int row = this.scaner.nextInt();
        System.out.println("Enter column..");
        int column = this.scaner.nextInt();
        System.out.println(row+","+column);
        return new Move(new Cell(this, cellstatus.OCCUPIED,row,column));


    }

    public Scanner getScanner() {
        return scaner;
    }

    public void setScanner(Scanner scanner) {
        this.scaner = scanner;
    }
}
