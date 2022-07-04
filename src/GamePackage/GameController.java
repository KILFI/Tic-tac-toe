package GamePackage;
import GamePackage.Player.*;

public class GameController implements GameControllable{
    private boolean isGameGoing;
    private Player firstPlayer;
    private Player secondPlayer;
    private GameStatus gameStatus;

    private Player currentPlayer;
    public GameController(String startStatus, String player1, String player2){
        firstPlayer = parsePlayer(player1, PlayerCharacter.X);
        secondPlayer = parsePlayer(player2, PlayerCharacter.O);
        isGameGoing = parseGameStatus(startStatus);
        if(firstPlayer == null || secondPlayer == null || !isGameGoing){
            firstPlayer = null;
            secondPlayer = null;
            isGameGoing = false;
            gameStatus = null;
            System.out.println("Bad parameters!");
        }
    }

    public GameController(){}

    @Override
    public void startGame(){
        if(gameStatus != null) {
            switch (gameStatus) {
                case finished:
                    isGameGoing = false;
                    break;

                case start:
                    currentPlayer = firstPlayer;
                    isGameGoing = true;
                    break;
            }
        }
    }

    private boolean parseGameStatus(String startStatus){
        switch (startStatus){
            case "start":
                gameStatus = GameStatus.start;
                return true;
            case "exit":
                gameStatus = GameStatus.finished;
                return true;
            default:
                return false;
        }
    }

    private Player parsePlayer(String inputPlayer, PlayerCharacter playerCharacter){
        switch (inputPlayer){
            case "easy":
                Player player = new EasyAI(playerCharacter);
                return player;
            case "medium":
                player = new MediumAI(playerCharacter);
                return player;
            case "hard":
                player = new HardAI(playerCharacter);
                return player;
            case "user":
                player = new UserPlayer(playerCharacter);
                return player;
            default:
                return null;
        }
    }

    public boolean isGameGoing(){
        return isGameGoing;
    }

    @Override
    public void finishGame(String exitString){
        switch (exitString){
            case "exit":
                gameStatus = GameStatus.finished;
                isGameGoing = false;
                break;

            case "X_win":
                gameStatus = GameStatus.X_win;
                isGameGoing = false;
                break;

            case "O_win":
                gameStatus = GameStatus.O_win;
                isGameGoing = false;
                break;

            case "draw":
                gameStatus = GameStatus.draw;
                isGameGoing = false;
                break;

            default:
                System.out.println("Bad parameters!");
                break;
        }
    }

    public Player getCurrentPlayer(){
        return currentPlayer;
    }

    public void changeCurrentPlayer(){
        if(currentPlayer == firstPlayer){
            currentPlayer = secondPlayer;
        } else {
            currentPlayer = firstPlayer;
        }
    }

    public GameStatus getGameStatus(){
        return gameStatus;
    }


    @Override
    public GridOperations makeMove(GridOperations grid) {
        grid = currentPlayer.makeMove(grid);
        if(currentPlayer.getClass() == UserPlayer.class){
            UserPlayer userPlayer = (UserPlayer)currentPlayer;
            if(userPlayer.isExitEntered){
                finishGame("exit");
            }
        }
        else {
            System.out.println("Making move level " + "\"" + getCurrentPlayer().getLevel() + "\"");
        }
        return grid;
    }

    public boolean isGameFinished(GridOperations currentGrid)
    {
        if(MoveChecker.checkForFinish(currentGrid, currentPlayer)){
            PlayerCharacter winner = currentPlayer.getPlayerCharacter();
            System.out.println(winner + " wins");
            finishGame(winner + "_win");
            return true;
        }

        if(MoveChecker.checkForDraw(currentGrid)){
            System.out.println("Draw");
            finishGame("draw");
            return true;
        }

        return false;
    }
}