package Models;

import Strategies.Bot.BotPlayingStrategiesFactory;
import projectenums.cellstatus;
import projectenums.difficultylevel;
import projectenums.playerType;

import java.util.Scanner;

public class Bot extends Player{

    difficultylevel d;
    BotPlayingStrategiesFactory factory;
    public Bot(char Symbol, difficultylevel d, BotPlayingStrategiesFactory factory, Scanner scanner)
    {
        super("BOT",Symbol, playerType.BOT,scanner);
        this.d = d;
        this.factory = factory;
    }
    public Bot(String Name,char Symbol, difficultylevel d, BotPlayingStrategiesFactory factory, Scanner scanner)
    {
        super(Name,Symbol, playerType.BOT,scanner);
        this.d = d;
        this.factory = factory;
    }

    public Move makeMove(Board board)
    {
        System.out.println(Name+" Making move");
        Move mv = factory.getBotPlayingStrategy(d).suggestMove(board);
        int row = mv.getCell().getRow();
        int column = mv.getCell().getColumn();
        return new Move(new Cell(this, cellstatus.OCCUPIED,row,column));

    }
}
