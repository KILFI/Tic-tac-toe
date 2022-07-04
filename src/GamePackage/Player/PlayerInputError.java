package GamePackage.Player;
import GamePackage.GridOperations;

public interface PlayerInputError {
    boolean isCellOccupied(GridOperations grid, int firstNumber, int secondNumber);
    boolean isNumberEntered(String numberStr);
    boolean isCoordinatesRight(int firstNumber, int secondNumber);
}
