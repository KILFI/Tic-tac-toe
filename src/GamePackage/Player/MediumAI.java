package GamePackage.Player;
import GamePackage.Grid;
import GamePackage.GridOperations;

import java.util.Random;

public class MediumAI extends Player{

    public MediumAI(PlayerCharacter playerCharacter){
        this.playerCharacter = playerCharacter;
    }

    @Override
    public GridOperations makeMove(GridOperations inputGrid){
        GridOperations grid = inputGrid;
        char[][] currentGrid = grid.getGrid();

        char[][] bufGrid = MoveChecker.checkForEndMove(currentGrid, playerCharacter);
        if(bufGrid != null){
            grid.setGrid(bufGrid);
            return grid;
        }

        bufGrid = MoveChecker.checkForBlockMove(currentGrid, playerCharacter, getOpponentCharacter());
        if(bufGrid != null){
            grid.setGrid(bufGrid);
            return grid;
        }

       return makeRandomMove(grid);
    }

    private GridOperations makeRandomMove(GridOperations grid){
        Random random = new Random();
        int cycleCounter = 0;
        while (cycleCounter < 100) {
            int i = random.nextInt(3);
            int j = random.nextInt(3);
            char[][] currentGrid = grid.getGrid();
            if (currentGrid[i][j] == '_') {
                currentGrid[i][j] = playerCharacter.toString().charAt(0);
                break;
            }
            cycleCounter++;
        }
        return grid;
    }

    @Override
    public String getLevel() {
        return "medium";
    }
}