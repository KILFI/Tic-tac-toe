package GamePackage.Player;

public class MoveChecker {

    private static char playerCharacter;
    public static char[][] checkForEndMove(char[][] currentGrid, PlayerCharacter playerCharacter){
        char playerChar = playerCharacter.toString().charAt(0);

        char[][] bufGrid = checkForRowDoubles(currentGrid, playerChar);
        if(bufGrid != null){
            return currentGrid;
        }

        bufGrid = checkForColumnDoubles(currentGrid, playerChar);
        if(bufGrid != null){
            return currentGrid;
        }

        bufGrid = checkForDiagonalDoubles(currentGrid, playerChar);
        if(bufGrid != null){
            return currentGrid;
        }

        return null;
    }

    public static char[][] checkForBlockMove(char[][] currentGrid, PlayerCharacter playerCharacter, PlayerCharacter opponentCharacter){
        char opponentChar = opponentCharacter.toString().charAt(0);
        MoveChecker.playerCharacter = playerCharacter.toString().charAt(0);
        checkForRowDoubles(currentGrid, opponentChar);

        return null;
    }

    private static char[][] checkForRowDoubles(char[][] currentGrid, char playerCharacter){
        for (int i = 0; i < currentGrid[0].length; i++) {
            if(currentGrid[i][0] + currentGrid[i][1] == playerCharacter * 2){
                currentGrid[i][2] = MoveChecker.playerCharacter;
        