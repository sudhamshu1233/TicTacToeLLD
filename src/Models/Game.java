package Models;

import Strategies.Winner.WinningStrategies;
import projectenums.GameStatus;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Board currentBoard;
    private List<Move> moves;
    private List<Player> players;
    private int nextPlayerIndex;
    private Player winner;
    private GameStatus gameStatus;
    private List<WinningStrategies> winningStrategies;

    private Game(){

    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public List<Move> getMoves()
    {
        return moves;
    }

    public List<WinningStrategies> getWinningStrategies()
    {
        return winningStrategies;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }

    private Game(GameBuilder gameBuilder)
    {
        this.currentBoard = gameBuilder.currentBoard;
        this.gameStatus = gameBuilder.gameStatus;
        this.moves = new ArrayList<>();
        this.players = gameBuilder.players;
        this.nextPlayerIndex = 0;
        this.winner = null;
        this.winningStrategies = gameBuilder.winningStrategies;
    }

    public Board getCurrentBoard()
    {
        return currentBoard;
    }

    public List<Player> getPlayers()
    {
        return players;
    }




    public void play()
    {
        System.out.println(nextPlayerIndex);
        Player currentPlayer = players.get(nextPlayerIndex);
        Move move = currentPlayer.makeMove(currentBoard);
        currentBoard.getBoard().get(move.getCell().getRow()).set(move.getCell().getColumn(),move.getCell());
        moves.add(move);
        for(WinningStrategies winnningSrategy:winningStrategies)
        {
            Player winner = winnningSrategy.getWinner(currentBoard,move);
            if(winner!=null)
            {
                gameStatus = GameStatus.WIN;
                this.winner = winner;
                System.out.println("Winner "+winner.getName());
                return;
            }
        }
        nextPlayerIndex  = (nextPlayerIndex+1)%players.size();


    }

    public Player getWinner() {
        return winner;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }


    public static class GameBuilder {
        private Board currentBoard;
        private List<Player> players;

        private GameStatus gameStatus;
        private List<WinningStrategies> winningStrategies;

        public GameBuilder setCurrentBoard(Board currentBoard) {
            this.currentBoard = currentBoard;
            return this;
        }



        public GameBuilder setGameStatus(GameStatus gameStatus) {
            this.gameStatus = gameStatus;
            return this;
        }


        public GameBuilder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }


        public GameBuilder setWinningStrategies(List<WinningStrategies> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }

        public GameBuilder addWinningStrategy(WinningStrategies strategy)
        {
            if(this.winningStrategies==null)
            {
                this.winningStrategies = new ArrayList<>();
            }
            this.winningStrategies.add(strategy);
            return this;
        }

        public Game buildGame()
        {
            return new Game(this);
        }
    }







}
