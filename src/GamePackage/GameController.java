package GamePackage;
import GamePackage.Player.*;

enum GameStatus{
    start,
    finished
}


public class GameController {
    private boolean isGameGoing;
    private Player firstPlayer;
    private Player secondPlayer;
    private GameStatus gameStatus;

    private Player currentPlayer;
    public GameController(String startStatus, String player1, String player2){
        firstPlayer = parsePlayer(player1, PlayerCharacter.X);
        secondPlayer = parsePlayer(player2, PlayerCharacter.O);
        isGameGoing = parseGameStatus(startStatus);
        if(firstPlayer != null && secondPlayer != null && isGameGoing){
            startGame();
        }
        else {
            System.out.println("Bad parameters!");
        }
    }

    private void startGame(){
        switch (gameStatus){
            case finished:
                isGameGoing = false;
                break;

            case start:
                currentPlayer = firstPlayer;
                isGameGoing = true;
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

    public void finishGame(String exitString){
        switch (exitString){
            case "exit":
                gameStatus = GameStatus.finished;
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
    }/////////////////


    public Grid makeMove(Grid grid) {
        System.out.println("Enter the coordinates:");

        grid = currentPlayer.makeMove(grid);
        if(currentPlayer.getClass() == UserPlayer.class){
            UserPlayer userPlayer = (UserPlayer)currentPlayer;
            if(userPlayer.isExitEntered){
                finishGame("exit");
            }
        }
        return grid;
    }

    public boolean isGameFinished(Grid currentGrid)
    {
        char[][] grid = currentGrid.getGrid();
        if(grid[0][0] + grid[0][1] + grid[0][2] == 264 || grid[1][0] + grid[1][1] + grid[1][2] == 264 ||
                grid[2][0] + grid[2][1] + grid[2][2] == 264 || grid[0][0] + grid[1][0] + grid[2][0] == 264 ||
                grid[0][1] + grid[1][1] + grid[2][1] == 264 || grid[0][2] + grid[1][2] + grid[2][2] == 264 ||
                grid[0][0] + grid[1][1] + grid[2][2] == 264 || grid[2][0] + grid[1][1] + grid[0][2] == 264){
            System.out.println("X wins");
            finishGame("exit");
            return true;
        }
        if(grid[0][0] + grid[0][1] + grid[0][2] == 237 || grid[1][0] + grid[1][1] + grid[1][2] == 237 ||
                grid[2][0] + grid[2][1] + grid[2][2] == 237 || grid[0][0] + grid[1][0] + grid[2][0] == 237 ||
                grid[0][1] + grid[1][1] + grid[2][1] == 237 || grid[0][2] + grid[1][2] + grid[2][2] == 237 ||
                grid[0][0] + grid[1][1] + grid[2][2] == 237 || grid[2][0] + grid[1][1] + grid[0][2] == 237){
            System.out.println("O wins");
            finishGame("exit");
            return true;
        }

        int xs = 0;
        int os = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                char currentPlayer = grid[i][j];
                if(currentPlayer == 'X'){
                    xs ++;
                }
                else if(currentPlayer== 'O'){
                    os ++;
                }
            }
        }

        if (xs + os == 9) {
            System.out.printf("Draw");
            finishGame("exit");
            return true;
        }

        return false;
    }
}
