package GamePackage.Player;
import GamePackage.Grid;
import java.util.Random;

public class EasyAI extends Player{

    public EasyAI(PlayerCharacter playerCharacter){
        this.playerCharacter = playerCharacter;
    }
    @Override
    public Grid makeMove(Grid grid){
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
        return "easy";
    }
}
