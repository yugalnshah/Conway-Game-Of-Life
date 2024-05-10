package conwaygame;
import java.util.ArrayList;

public class GameOfLife {

    // Instance variables
    private static final boolean ALIVE = true;
    private static final boolean  DEAD = false;

    private boolean[][] grid;    // The board has the current generation of cells
    private int totalAliveCells; // Total number of alive cells in the grid (board)

    public GameOfLife() {
        grid = new boolean[5][5];
        totalAliveCells = 5;
        grid[1][1] = ALIVE;
        grid[1][3] = ALIVE;
        grid[2][2] = ALIVE;
        grid[3][2] = ALIVE;
        grid[3][3] = ALIVE;
    }

    public GameOfLife (String file) {

        StdIn.setFile(file); //open the file to read

        int r = StdIn.readInt(); // read rows
        int c = StdIn.readInt(); // read columns

        grid = new boolean[r][c]; // initialize the grid with given rows and columns

        for (int i = 0; i < r; i++){ // loop through the each row and column to read cell states
            for (int j = 0; j < c; j++){

                grid[i][j] = StdIn.readBoolean(); // true for alive, false for dead

            }
        }
    }

    /**
     * Returns grid
     * @return boolean[][] for current grid
     */
    public boolean[][] getGrid () {
        return grid;
    }
    
    /**
     * Returns totalAliveCells
     * @return int for total number of alive cells in grid
     */
    public int getTotalAliveCells () {
        return totalAliveCells;
    }

    public boolean getCellState (int row, int col) {

        if (grid[row][col]){ // check if alive or dead through the dimensions
            return ALIVE; // alive if true
        }

        return DEAD; // dead if false
    }

    public boolean isAlive () {

        for (int r = 0; r < grid.length; r++){ // iterate through each row
            for (int c = 0; c < grid[0].length; c++){ // iterate through each column in the row

                if (grid[r][c]){ // check if alive
                    return true; // return true if any living cell is found
                }

            }
        }
        return false; // return false if no living cell
    }

    public int numOfAliveNeighbors (int row, int col) {

        int ncell = 0; // number of neighbor cells
        int below = row+1, above = row-1, left = col-1, right = col + 1; // row and columns indices for neighboring cells

        if (row == 0){ // update indices for cells at the edges 
            above = grid.length-1; // cell is at the top edge, update the above index
        } 
        else if(row == grid.length - 1){ 
            below = 0; // cell is at the bottom edge, update the below index
        }

        if (col == 0){
            left = grid[0].length - 1; // cell is at the left edge, update the left index
        } 
        else if (col == grid[0].length - 1){ 
            right = 0; // cell is at the right edge, update the right index
        }

        // check each neighbor cell and increment the counter for every neighbor alive
        if (grid[below][col]){
            ncell++;
        }
        if (grid[above][col]){
            ncell++;
        }
        if (grid[row][left]){
            ncell++;
        }
        if (grid[row][right]){
            ncell++;
        }
        if (grid[below][left]){
            ncell++;
        }
        if (grid[below][right]){
            ncell++;
        }
        if (grid[above][right]){
            ncell++;
        }
        if (grid[above][left]){
            ncell++;
        }
        return ncell; // return the total neighboring alive cells
    }

    public boolean[][] computeNewGrid () {

        boolean[][] nextgen = new boolean[grid.length][grid[0].length]; // new grid for the next generation
        int aneighbors = 0; // # of alive neighbors
        boolean check = false; // state of the current cell

        for (int r = 0; r < nextgen.length; r++){ // iterate through each cell in the grid
            for (int c = 0; c < nextgen[0].length; c++){ 

                aneighbors = numOfAliveNeighbors(r, c); // get the number of alive neighbors
                check = grid[r][c]; // get the state of the current cell

                if (aneighbors == 3){ // apply the rules of the game to determine the state of the cell in the next generation
                    nextgen[r][c] = true; // dead cells with 3 alive neighbors becomes alive
                } 
                else if (check && aneighbors == 2){
                    nextgen[r][c] = true; // live cells with 2 alive neighbors lives on to the next generation
                }

            }
        }
        return nextgen; // return the new updated grid with the next generation
    }

    public void nextGeneration () {

        boolean[][] nextgen = computeNewGrid(); // next generation of the board
        grid = nextgen; // update the current grid
        totalAliveCells = getTotalAliveCells(); // update the total alive cells counter

    }

    public void nextGeneration (int n) {

        for (int i = 0; i < n; i++){ // repeats "n" times to compute the next generation

            nextGeneration();

        }
    }

    public int numOfCommunities() {

        int ncomm = 0; // num of separated communities
        WeightedQuickUnionUF ref = new WeightedQuickUnionUF(grid.length, grid[0].length);
        int random = -1; //keep track of the root of the tree

        for (int r = 0; r < grid.length; r++){ //iterate through each cell in the grid
            for (int c = 0; c < grid[0].length; c++){

                if (grid[r][c]){
                    int below = r+1, above = r-1, left = c-1, right = c + 1; // define the row and columns for the neighboring cells

                    // update indices cells at the edges of the grid
                    if (r == 0){
                        above = grid.length-1;
                    } 
                    else if(r == grid.length - 1){
                        below = 0;
                    }
                    if (c == 0){
                        left = grid[0].length - 1;
                    } 
                    else if (c == grid[0].length - 1){
                        right = 0;
                    }

                    // connect neighboring cells
                    if (grid[below][c]){
                        ref.union(r, c, below, c);
                    }
                    if (grid[above][c]){
                        ref.union(r, c, above, c);
                    }
                    if (grid[r][left]){
                        ref.union(r, c, r, left);
                    }
                    if (grid[r][right]){
                        ref.union(r, c, r, right);
                    }
                    if (grid[below][left]){
                        ref.union(r, c, below, left);
                    }
                    if (grid[below][right]){
                        ref.union(r, c, below, right);
                    }
                    if (grid[above][right]){
                        ref.union(r, c, above, right);
                    }
                    if (grid[above][left]){
                        ref.union(r, c, above, left);
                    }

                }
            }
        }

        for (int r =   0; r < grid.length; r++){ // count the number of unique roots (communities)
            for (int c = 0; c < grid[0].length; c++){

                if (grid[r][c]){

                    if (ref.find(r, c) != random){
                    ncomm++; // increment the # of communities when the current cells is different from the root cell
                    random = ref.find(r, c);

                }
            }

            }
        }
        return ncomm; // # of communities
    }
}