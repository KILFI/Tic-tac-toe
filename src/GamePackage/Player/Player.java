package GamePackage.Player;
import GamePackage.Grid;
import GamePackage.GridOperations;

public abstract class Player {
    protected PlayerCharacter playerCharacter;
    public PlayerCharacter getPlayerCharacter(){return playerCharacter;}
    public abstract GridOperations makeMove(GridOperations grid);
    public abstract String getLevel();
    public PlayerCharacter getOpponentCharacter(){
        if(playerCharacter == PlayerCharacter.X){
            return PlayerCharacter.O;
        }
        else {
            return PlayerCharacter.X;
        }
    }
}