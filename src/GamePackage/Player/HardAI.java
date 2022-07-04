package GamePackage.Player;
import GamePackage.Grid;
import GamePackage.GridOperations;

import java.util.ArrayList;

public class HardAI extends Player{

    public HardAI(PlayerCharacter playerCharacter){
        this.playerCharacter = playerCharacter;
    }

    @Override
    public GridOperations makeMove(GridOperations grid) {
        char[][] currentGrid = grid.getGrid();
        Move bestMove = minimax(currentGrid, playerCharacter);
        currentGrid[bestMove.i][bestMove.j] = playerCharacter.toString().charAt(0);
        grid.setGrid(currentGrid);
        return grid;
    }

    @Override
    public String getLevel() {
        return "hard";
    }

    private Move minimax(char[][] currentGrid, PlayerCharacter currentPlayer){

        ArrayList<int[]> emptyCells = findEmptyCells(currentGrid);

        Grid grid = new Grid();
        grid.setGrid(currentGrid);

        if (MoveChecker.checkForFinish(grid, new EasyAI(getOpponentCharacter()))){
            return new Move(-10);
        }
        else if (MoveChecker.checkForFinish(grid, this)){
            return new Move(10);
        }
        else if (emptyCells.size() == 0){
            return new Move(0);
        }

        ArrayList<Move> moves = new ArrayList<>();

        for (int i = 0; i < emptyCells.size(); i++) {
            int firstIndex = emptyCells.get(i)[0];
            int secondIndex = emptyCells.get(i)[1];

            Move move = new Move(firstIndex, secondIndex);

            currentGrid[firstIndex][secondIndex] = currentPlayer.toString().charAt(0);

            Move resultMove;

            if (currentPlayer == playerCharacter){
                resultMove = minimax(currentGrid, getOpponentCharacter());
            }
            else {
                resultMove = minimax(currentGrid, playerCharacter);
            }
            move.score = resultMove.score;

            currentGrid[firstIndex][secondIndex] = '_';
            moves.add(move);
        }

        Move bestMove = null;

        if(currentPlayer == playerCharacter){
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < moves.size(); i++) {
                int bufScore = moves.get(i).score;
                if(bufScore > bestScore){
                    bestScore = bufScore;
                    bestMove = moves.get(i);
                }
            }
        }
        else {
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < moves.size(); i++) {
                int bufScore = moves.get(i).score;
                if(bufScore < bestScore){
                    bestScore = bufScore;
                    bestMove = moves.get(i);
                }
            }
        }

        return bestMove;
    }

    private ArrayList<int[]> findEmptyCells(char[][] grid){
        ArrayList<int[]> emptyCells = new ArrayList<int[]>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == '_'){
                    emptyCells.add(new int[]{i,j});
                }
            }
        }

        return emptyCells;
    }

    private class Move{
        public int i;
        public int j;
        public int score;

        public Move(int score){
            this.score = score;
        }

        public Move(int i, int j){
            this.i = i;
            this.j = j;
        }
    }
}