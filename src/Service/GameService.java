package Service;

import Models.*;
import Strategies.Winner.ColumnWinningStrategies;
import Strategies.Winner.CornerWinningStrategy;
import Strategies.Winner.RowWinningStrategy;
import Strategies.Winner.WinningStrategies;
import projectenums.GameStatus;
import projectenums.cellstatus;
import projectenums.playerType;

import java.util.List;

public class GameService {
    Game currentGame;


    public Game buildGame(int dimension, List<Player> playerList)
    {
        currentGame = new Game.GameBuilder().setGameStatus(GameStatus.INPROGRESS)
                .setCurrentBoard(new Board(dimension))
                .addWinningStrategy(new RowWinningStrategy())
                .addWinningStrategy(new ColumnWinningStrategies())
                .addWinningStrategy(new CornerWinningStrategy())
                .setPlayers(playerList)
                .buildGame();

        return currentGame;

    }

    public boolean checkIfDraw()
    {
        List<List<Cell>> Cells = currentGame.getCurrentBoard().getBoard();
        for(int row=0;row<Cells.size();row++)
        {
            for(int column=0;column<Cells.size();column++)
            {
                if(Cells.get(row).get(column).getCellStatus().equals(cellstatus.EMPTY))
                {
                    return false;
                }
            }

        }
        return true;
    }

    public void setDraw()
    {
        currentGame.setGameStatus(GameStatus.DRAW);
        System.out.println("Game is Drawn");
    }

    public int getPreviousIndex(int index)
    {
        int newIndex = --index;
        if(newIndex<0)
        {
            newIndex = currentGame.getPlayers().size()-1;
        }
        return newIndex;
    }
    public void undo()
    {
        Move lastMove = currentGame.getMoves().getLast();
        int row  = lastMove.getCell().getRow();
        int column = lastMove.getCell().getColumn();
        setBoardState(new Move(new Cell(row,column)));
        int currentNext =getPreviousIndex(currentGame.getNextPlayerIndex());
        if(currentGame.getPlayers().get(currentNext).getPlayerType().equals(playerType.BOT))
        {
            currentNext = getPreviousIndex(currentNext);
        }
        currentGame.setNextPlayerIndex(currentNext);
        if(currentGame.getGameStatus()==GameStatus.WIN)
        {
            currentGame.setGameStatus(GameStatus.INPROGRESS);
            currentGame.setWinner(null);
        }
        if(currentGame.getGameStatus()==GameStatus.DRAW)
        {
            currentGame.setGameStatus(GameStatus.INPROGRESS);
            currentGame.setWinner(null);
        }


    }

    public void setBoardState(Move move)
    {

        currentGame.getCurrentBoard().getBoard().get(move.getCell().getRow()).set(move.getCell().getColumn(),move.getCell());
    }
    public void makeNextMove() throws IllegalArgumentException
    {

        Board currentBoard = currentGame.getCurrentBoard();
        List<WinningStrategies> winningStrategies = currentGame.getWinningStrategies();
        Player currentPlayer = currentGame.getPlayers().get(currentGame.getNextPlayerIndex());
        Move move = currentPlayer.makeMove(currentGame.getCurrentBoard());
        if(checkIfCellAlreadyOccupied(move))
        {
            throw new IllegalArgumentException("Given Cell is occupied");
        }
        setBoardState(move);
        currentGame.getMoves().add(move);
        checkWinner(winningStrategies, currentBoard, move);
        setNextPlayerIndex();
        if(checkIfDraw() && currentGame.getWinner()==null)
        {
            setDraw();
        }

    }
    public boolean checkIfCellAlreadyOccupied(Move move)
    {
        Cell c = currentGame.getCurrentBoard().getBoard().get(move.getCell().getRow()).get(move.getCell().getColumn());
        if(c.getPlayer()!=null) {
            System.out.println(c.getPlayer().getSymbol());
        }
        return c.getCellStatus().equals(cellstatus.OCCUPIED);
    }

    public GameStatus getCurrentGameStatus()
    {
        return currentGame.getGameStatus();
    }

    public void displayCurrentGameBoard()
    {
        List<List<Cell>> board = currentGame.getCurrentBoard().getBoard();
        for(int row=0;row<board.size();row++) {


            for (int column = 0; column < board.getFirst().size(); column++) {

                System.out.print("|");
                Player currentPlayerInCell = board.get(row).get(column).getPlayer();
                if(currentPlayerInCell!=null)
                {
                    System.out.print(currentPlayerInCell.getSymbol());
                }
                else
                {
                    System.out.print("-");
                }



            }
            System.out.print("|\n");
        }
    }



    void setNextPlayerIndex()
    {
        int nextPlayerIndex = (currentGame.getNextPlayerIndex()+1)%currentGame.getPlayers().size();
        currentGame.setNextPlayerIndex(nextPlayerIndex);
    }

    void checkWinner(List<WinningStrategies> winningStrategies,Board currentBoard,Move move)
    {
        for(WinningStrategies winnningSrategy:winningStrategies)
        {
            Player winner = winnningSrategy.getWinner(currentBoard,move);
            if(winner!=null)
            {
                currentGame.setGameStatus(GameStatus.WIN);
                currentGame.setWinner(winner);
                System.out.println("Winner "+winner.getName());
                return;
            }
        }
    }
}
