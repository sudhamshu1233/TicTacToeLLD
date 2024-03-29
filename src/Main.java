import Controllers.GameController;
import Models.Board;
import Models.Bot;
import Models.Game;
import Models.Player;
import Strategies.Bot.BotPlayingStrategiesFactory;
import Strategies.Winner.ColumnWinningStrategies;
import Strategies.Winner.CornerWinningStrategy;
import Strategies.Winner.RowWinningStrategy;
import Strategies.Winner.WinningStrategies;
import projectenums.GameStatus;
import projectenums.difficultylevel;
import projectenums.playerType;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Player> players = new ArrayList<>();
        Set<Character> Symbols = new HashSet<>();
        Scanner sc = new Scanner(System.in);
        int dimension = 0;
        while(dimension<=0){
            System.out.println("Enter dimension of board");
            dimension = sc.nextInt();
            if(dimension<=0)
            {
                System.out.println("Enter dimension greater than 0");
            }
        }
        System.out.println("Bot vs Bot... mode ?");
        boolean botMode = sc.nextBoolean();
        if(botMode)
        {
            System.out.println("Number of Bots?");
            int numberOfBots = sc.nextInt();
            char symbol = 'A';
            int i = 0;
            while(numberOfBots > 0) {
                players.add(new Bot("BOT" + i , symbol, difficultylevel.EASY, new BotPlayingStrategiesFactory(), sc));
                symbol++;
                numberOfBots--;
                i++;
            }

            GameController g1 = new GameController();
            Game g = g1.buildGame(dimension,players);

            while(g.getGameStatus()==GameStatus.INPROGRESS)
            {

                try {
                    g1.makeNextMove();
                }
                catch(IllegalArgumentException e)
                {
                    System.out.println("Cell already occupied try entering again");
                }
                g1.displayCurrentGameBoard();
            }
            return;

        }
        System.out.println("Enter no of non - bot players");
        int playerNum = sc.nextInt();
        System.out.println("Is Bot allowed..");
        boolean isBot = sc.nextBoolean();
        if(isBot)
        {
            players.add(new Bot('X',difficultylevel.EASY,new BotPlayingStrategiesFactory(),sc));
            Symbols.add('X');
        }
        for(int i=0;i<playerNum;i++)
        {
            boolean playercreated = false;
            while(!playercreated) {
                System.out.println("Enter symbol for p" + i);
                String symbol = sc.next();
                char c = symbol.charAt(0);
                if (Symbols.contains(c)) {
                    System.out.println("Symbol already taken try entering another one...");
                }
                else
                {
                    playercreated = true;
                    Symbols.add(c);
                    players.add(new Player("P"+i,c,playerType.HUMAN,sc));
                }
            }
        }
        GameController g1 = new GameController();
        Game g = g1.buildGame(dimension,players);

        while(g.getGameStatus()==GameStatus.INPROGRESS)
        {

            try {
                g1.makeNextMove();
                g1.displayCurrentGameBoard();
                System.out.println("###########################");
                System.out.println("Do u want to undo last move ?");
                boolean doundo = sc.nextBoolean();
                if(doundo)
                {
                    g1.undoLastMove();
                }
            }
            catch(IllegalArgumentException e)
            {
                System.out.println("Cell already occupied try entering again");
            }



        }



    }
}