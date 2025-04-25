package edu.wm.cs.cs301.sudoku.model;


import java.util.*;

/**
 * The {@code SudokuPuzzle} class represents the model component of a Sudoku game.
 * It manages the puzzle's initial setup, current state, and solution, and provides
 * methods for validation, and puzzle manipulation.
 */

public class SudokuPuzzle {
    public static final int NUM_ROWS = 9;
    public static final int NUM_COLS = 9;

    public static final int EMPTY = 0;

    // the current version of the puzzle including valid player moves
    private int[][] current = new int[NUM_ROWS][NUM_COLS];

    // the solution to the current puzzle
    private int[][] solution = new int[NUM_ROWS][NUM_COLS];

    // the start to the current puzzle
    private int[][] setup = new int[NUM_ROWS][NUM_COLS];

    public SudokuPuzzle(){
        fillGrid(current);
        solution = copyGrid(current);
        current = removeValues(current);
        setup = copyGrid(current);
    }

    public int[][] getCurrent() {
        return(current);
    }

    public int[][] getSetup(){
        return(setup);
    }

    public int[][] getSolution() {
        return solution;
    }

    /**
     * Recursively fills a grid with a valid Sudoku solution using backtracking.
     *
     * @param grid the 9x9 integer grid to be filled
     * @return {@code true} if a valid grid was successfully generated; {@code false} otherwise
     */

    public boolean fillGrid(int[][] grid) {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col] == 0) {
                    List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
                    Collections.shuffle(numbers);
                    for (int num : numbers) {
                        if (checkCell(grid, row, col, num)) {
                            grid[row][col] = num;
                            if (fillGrid(grid)) {
                                return true;
                            }
                            grid[row][col] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Determines whether the given Sudoku grid has a unique solution.
     *
     * @param grid the 9x9 Sudoku grid to evaluate
     * @return {@code true} if the grid has exactly one valid solution; {@code false} otherwise
     */

    public boolean findSolutions(int[][] grid){
        int counter = 0;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col] == 0) {
                    for (int num = 1; num < 10; num++) {
                        if (checkCell(grid, row, col, num)) {
                            grid[row][col] = num;
                            if (findSolutions(grid)) {
                                counter++;
                                if (counter>1){
                                    return false;
                                }
                            }
                            grid[row][col] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return counter == 1;
    }

    /**
     * Randomly removes values from a completed Sudoku grid to generate a playable puzzle,
     * ensuring that the resulting puzzle has a unique solution.
     *
     * @param grid a completed 9x9 Sudoku solution grid
     * @return the modified grid with some values removed
     */

    public int[][] removeValues (int[][] grid){
        int[][] tempGrid = grid;
        int attempts = 33;
        for (int num = 1; num <= attempts; num++) {
            int row = (int) (Math.random() * 9);
            int col = (int) (Math.random() * 9);
            if (tempGrid[row][col] != 0){
                tempGrid[row][col] = 0;
                if (!findSolutions(tempGrid)){
                    tempGrid[row][col] = grid[row][col];
                }
            }
        }
        return tempGrid;
    }

    /**
     * Checks whether a given Sudoku grid is completely filled (i.e., contains no zeros).
     *
     * @param grid the Sudoku grid to check
     * @return {@code true} if the grid is fully filled; {@code false} otherwise
     */

    public boolean checkGrid(int[][] grid){
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkCell(int[][] grid, int i, int j, int value) {
        int counter = 0;
        // Check the row
        for (int col = 0; col < grid[i].length; col++) {
            if (grid[i][col] == value) {
                counter += 1;
            }
            if (counter > 1) {
                return false;
            }
        }
        // Check the collumn
        for (int row = 0; row < grid[i].length; row++) {
            if (grid[row][j] == value) {
                counter += 1;
            }
            if (counter > 0) {
                return false;
            }
        }
        // Check the square
        int startRow = (i / 3) * 3;
        int startCol = (j / 3) * 3;

        for (int row = startRow; row < startRow + 3; row++) {
            for (int col = startCol; col < startCol + 3; col++) {
                if (grid[row][col] == value) {
                    return false;
                }
            }
        }
        return true;
    }

    private int[][] copyGrid(int[][] grid){
        int[][] empty = new int[9][9];
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                empty[row][col] = grid[row][col];
            }
        }
        return empty;
    }

    /**
     * Validates whether a proposed move is legal, considering both the original puzzle constraints
     * and Sudoku rules.
     *
     * @param row the row index of the cell
     * @param col the column index of the cell
     * @param value the value the player wants to insert
     * @return {@code true} if the move is valid; {@code false} otherwise
     */

    public boolean checkMoveLegal(int row, int col, int value){
        if (row == 10){
            return false; // deals with bad inputs passed on from parse move
        }
        if (setup[row][col] != 0){ //checks if cell was part of orig puzzle
            return false;
        }
        if (!checkCell(current, row, col, value)){ //checks if move is sudoku legal
            return false;
        }
        return true;
    }

    /**
     * Updates the current puzzle grid by placing a value at the specified location.
     *
     * @param row the row index of the cell
     * @param col the column index of the cell
     * @param value the value to insert
     */

    public void updatePuzzle(int row, int col, int value){
        current[row][col] = value;
        getCurrent();
    }
}