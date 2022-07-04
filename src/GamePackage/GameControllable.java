package GamePackage;

public interface GameControllable {
    void finishGame(String finishString);
    void startGame();
    GridOperations makeMove(GridOperations grid);
}
