package GamePackage;

public class Grid {
    private char[][] grid;
    public Grid(){
        grid = new char[][]{{'_','_','_'},{'_','_','_'},{'_','_','_'}};
    }

    public char[][] getGrid(){
        return grid;
    }

    public void setGrid(char[][] grid){
        this.grid = grid;
    }

    public  void printGrid(){
        System.out.println("---------");

        for (int i = 0; i < grid.length; i++) {
            System.out.println("| " + grid[i][0] + " " + grid[i][1] + " " + grid[i][2] + " |");
        }

        System.out.println("---------");
    }
}
