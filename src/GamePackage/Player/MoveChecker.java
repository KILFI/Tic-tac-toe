package GamePackage.Player;
import GamePackage.GridOperations;

public class MoveChecker {

    private static char playerCharacter;

    public static boolean checkForFinish(GridOperations currentGrid, Player currentPlayer) {
        char[][] grid = currentGrid.getGrid();
        int currentPlayerCharSum = 3 * currentPlayer.getPlayerCharacter().toString().charAt(0);
        if(
            grid[0][0] + grid[0][1] + grid[0][2] == currentPlayerCharSum ||
            grid[1][0] + grid[1][1] + grid[1][2] == currentPlayerCharSum ||
            grid[2][0] + grid[2][1] + grid[2][2] == currentPlayerCharSum ||
            grid[0][0] + grid[1][0] + grid[2][0] == currentPlayerCharSum ||
            grid[0][1] + grid[1][1] + grid[2][1] == currentPlayerCharSum ||
            grid[0][2] + grid[1][2] + grid[2][2] == currentPlayerCharSum ||
            grid[0][0] + grid[1][1] + grid[2][2] == currentPlayerCharSum ||
            grid[2][0] + grid[1][1] + grid[0][2] == currentPlayerCharSum)
        {
            return true;
        }
        else {
            return false;
        }
    }

    public static boolean checkForDraw(GridOperations currentGrid){
        char[][] grid = currentGrid.getGrid();
        int xs = 0;
        int os = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                char currentPlayerCharacter = grid[i][j];
                if(currentPlayerCharacter == PlayerCharacter.X.toString().charAt(0)){
                    xs ++;
                }
                else if(currentPlayerCharacter == PlayerCharacter.O.toString().charAt(0)){
                    os ++;
                }
            }
        }

        if (xs + os == 9) {
            return true;
        }
        else {
            return false;
        }
    }

    public static char[][] checkForEndMove(char[][] currentGrid, PlayerCharacter playerCharacter){
        char playerChar = playerCharacter.toString().charAt(0);

        char[][] bufGrid = checkForRowDoubles(currentGrid, playerChar);
        if(bufGrid != null){
            return bufGrid;
        }

        bufGrid = checkForColumnDoubles(currentGrid, playerChar);
        if(bufGrid != null){
            return bufGrid;
        }

        bufGrid = checkForDiagonalDoubles(currentGrid, playerChar);
        if(bufGrid != null){
            return bufGrid;
        }

        return null;
    }

    public static char[][] checkForBlockMove(char[][] currentGrid, PlayerCharacter playerCharacter, PlayerCharacter opponentCharacter){
        char opponentChar = opponentCharacter.toString().charAt(0);
        MoveChecker.playerCharacter = playerCharacter.toString().charAt(0);

        char[][] bufGrid = checkForRowDoubles(currentGrid, opponentChar);
        if(bufGrid != null){
            return bufGrid;
        }

        bufGrid = checkForColumnDoubles(currentGrid, opponentChar);
        if(bufGrid != null){
            return bufGrid;
        }

        bufGrid = checkForDiagonalDoubles(currentGrid, opponentChar);
        if(bufGrid != null){
            return bufGrid;
        }


        return null;
    }

    private static char[][] checkForRowDoubles(char[][] currentGrid, char playerCharacter){
        for (int i = 0; i < currentGrid[0].length; i++) {
            if(currentGrid[i][0] + currentGrid[i][1] == playerCharacter * 2 && currentGrid[i][2] == '_'){
                currentGrid[i][2] = MoveChecker.playerCharacter;
                return currentGrid;
            } else if (currentGrid[i][1] + currentGrid[i][2] == playerCharacter * 2 && currentGrid[i][0] == '_') {
                currentGrid[i][0] = MoveChecker.playerCharacter;
                return currentGrid;
            }
        }

        return null;
    }

    private static char[][] checkForColumnDoubles(char[][] currentGrid, char playerCharacter){
        for (int i = 0; i < currentGrid[0].length; i++) {
            if(currentGrid[0][i] + currentGrid[1][i] == playerCharacter * 2 && currentGrid[2][i] == '_') {
                currentGrid[2][i] = MoveChecker.playerCharacter;
                return currentGrid;
            } else if (currentGrid[1][i] + currentGrid[2][i] == playerCharacter * 2 && currentGrid[0][i] == '_') {
                currentGrid[0][i] = MoveChecker.playerCharacter;
                return currentGrid;
            }
        }

        return null;
    }

    private static char[][] checkForDiagonalDoubles(char[][] currentGrid, char playerCharacter){
        if(currentGrid[0][0] + currentGrid [1][1] == playerCharacter * 2 && currentGrid[2][2] == '_'){
            currentGrid[2][2] = MoveChecker.playerCharacter;
            return currentGrid;
        }
        if(currentGrid[1][1] + currentGrid [2][2] == playerCharacter * 2 && currentGrid[0][0] == '_'){
            currentGrid[0][0] = MoveChecker.playerCharacter;
            return currentGrid;
        }
        if(currentGrid[0][2] + currentGrid [1][1] == playerCharacter * 2 && currentGrid[2][0] == '_'){
            currentGrid[2][0] = MoveChecker.playerCharacter;
            return currentGrid;
        }
        if(currentGrid[2][0] + currentGrid [1][1] == playerCharacter * 2 && currentGrid[0][2] == '_'){
            currentGrid[0][2] = MoveChecker.playerCharacter;
            return currentGrid;
        }

        return null;
    }
}