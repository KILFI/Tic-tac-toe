package GamePackage.Player;
import GamePackage.Grid;
import GamePackage.GridOperations;

import java.util.Scanner;

public class UserPlayer extends Player implements PlayerInputError {
    public boolean isExitEntered = false;
    public UserPlayer(PlayerCharacter playerCharacter){
        this.playerCharacter = playerCharacter;
    }

    @Override
    public GridOperations makeMove(GridOperations inputGrid) {
        GridOperations grid = inputGrid;
        Scanner scanner = new Scanner(System.in);
        String numberStr = scanner.nextLine();

        if(numberStr.equals("exit")){
            isExitEntered = true;
            return null;
        }
        else {
            String[] numbers;
            int firstNumber;
            int secondNumber;
            char[][] currentGrid;

            if(isNumberEntered(numberStr)) {
                numbers = numberStr.split(" ");
                firstNumber = Integer.parseInt(numbers[0]);
                secondNumber = Integer.parseInt(numbers[1]);
            }
            else {
                System.out.println("You should enter numbers!");
                return null;
            }

            if(isCoordinatesRight(firstNumber, secondNumber)){
                currentGrid = grid.getGrid();
            }
            else{
                System.out.println("Coordinates should be from 1 to 3!");
                return  null;
            }

            if(!isCellOccupied(grid, firstNumber, secondNumber)){
                currentGrid[firstNumber - 1][secondNumber - 1] = playerCharacter.toString().charAt(0);
            }
            else {
                System.out.println("This cell is occupied! Choose another one!");
                return  null;
            }
        }

       return grid;
    }

    @Override
    public String getLevel() {
        return "user";
    }

    @Override
    public boolean isCellOccupied(GridOperations grid, int firstNumber, int secondNumber) {
        if (grid.getGrid()[firstNumber - 1][secondNumber - 1] == '_') {
            return false;
        }

        return true;
    }

    @Override
    public boolean isNumberEntered(String numberStr) {
        String[] numbers;
        if (numberStr.contains(" ")) {
            numbers = numberStr.split(" ");
            if (numbers[0].charAt(0) >= 48 && numbers[0].charAt(0) <= 57 && numbers[1].charAt(0) >= 48 && numbers[1].charAt(0) <= 57) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean isCoordinatesRight(int firstNumber, int secondNumber) {
        if (firstNumber > 0 && firstNumber < 4 && secondNumber > 0 && secondNumber < 4) {
            return true;
        }

        return false;
    }
}
