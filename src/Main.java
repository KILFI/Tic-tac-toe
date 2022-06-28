import GamePackage.GameController;
import GamePackage.GameStatus;
import GamePackage.Grid;
import GamePackage.Player.UserPlayer;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GameController game = new GameController();

        while (true) {
            System.out.println("Input command:");
            String[] inputData = scanner.nextLine().split(" ");

            if(inputData.length == 3) {
                game = new GameController(inputData[0], inputData[1], inputData[2]);
                Grid grid = new Grid();
                grid.printGrid();

                while (game.isGameGoing()) {
                    if (game.getCurrentPlayer().getClass() == UserPlayer.class) {
                        Grid bufGrid = game.makeMove(grid);
                        if (bufGrid != null) {
                            grid.setGrid(bufGrid.getGrid());
                            grid.printGrid();
                            game.changeCurrentPlayer();
                            if (game.isGameFinished(grid)) {
                                break;
                            }
                        }
                    } else {
                        System.out.println("Making move level " + "\"" + game.getCurrentPlayer().getLevel() + "\"");

                        grid = game.makeMove(grid);
                        game.changeCurrentPlayer();
                        grid.printGrid();
                        if (game.isGameFinished(grid)) {
                            break;
                        }
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
}

