import GamePackage.GameController;
import GamePackage.GameStatus;
import GamePackage.Grid;
import GamePackage.GridOperations;
import GamePackage.Player.UserPlayer;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GameController game = new GameController();
        printCommands();

        while (true) {
            System.out.println("Input command:");
            String[] inputData = scanner.nextLine().split(" ");

            if(inputData.length == 3) {
                game = new GameController(inputData[0], inputData[1], inputData[2]);
                game.startGame();
                GridOperations grid = new Grid();
                if(game.isGameGoing()){
                    grid.printGrid();
                }

                while (game.isGameGoing()) {
                    if (game.getCurrentPlayer().getClass() == UserPlayer.class) {
                        System.out.println("Enter the coordinates:");

                        GridOperations bufGrid = game.makeMove(grid);
                        if (bufGrid != null) {
                            grid.setGrid(bufGrid.getGrid());
                            grid.printGrid();
                            if (game.isGameFinished(grid)) {
                                break;
                            }
                            game.changeCurrentPlayer();
                        }
                    } else {
                        grid = game.makeMove(grid);
                        grid.printGrid();
                        if (game.isGameFinished(grid)) {
                            break;
                        }
                        game.changeCurrentPlayer();
                    }
                }
            } else if (inputData.length == 1) {
                game.finishGame(inputData[0]);
                if (game.getGameStatus() == GameStatus.finished) {
                    break;
                }
            }
            else {
                System.out.println("Bad parameters!");
            }
        }
    }

    private static void printCommands(){
        System.out.println("Welcome to the Tic-tac-toe game!");
        System.out.println("To start a game write 'start' and choose two players, for example 'start user easy'");
        System.out.println("The game has 3 difficulty level: 'easy', 'medium', 'hard'");
        System.out.println("To exit, write 'exit' after the end of match");
        System.out.println("Enjoy the game!");
    }
}