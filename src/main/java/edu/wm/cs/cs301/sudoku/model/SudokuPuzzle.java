package edu.wm.cs.cs301.sudoku.model;

import java.util.Scanner;
import java.util.*;

/**
 * This class creates the sudoku puzzle as a 9x9 array
 * It gets the current state and checks legality of moves and win condition
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

    public int[][] removeValues (int[][] grid){
        int[][] tempGrid = grid;
        int attempts = 30;
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

    public void printGrid(int[][] grid) {
        Object[][] printGrid = new Object[9][9];
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if(grid[row][col] == 0){
                    printGrid[row][col] = " ";
                }
                else{
                    printGrid[row][col] = grid[row][col];
                }
            }
        }

        System.out.println(
                "    A B C   D E F   G H I\n" +
                        "  +-----------------------+\n" +
                        "A | " + printGrid[0][0] + " " + printGrid[0][1] + " " + printGrid[0][2] + " " + "|" + " " + printGrid[0][3] + " " + printGrid[0][4] + " " + printGrid[0][5] + " " + "|" + " " + printGrid[0][6] + " " + printGrid[0][7] + " " + printGrid[0][8] + " " + "|\n" +
                        "B | " + printGrid[1][0] + " " + printGrid[1][1] + " " + printGrid[1][2] + " " + "|" + " " + printGrid[1][3] + " " + printGrid[1][4] + " " + printGrid[1][5] + " " + "|" + " " + printGrid[1][6] + " " + printGrid[1][7] + " " + printGrid[1][8] + " " + "|\n" +
                        "C | " + printGrid[2][0] + " " + printGrid[2][1] + " " + printGrid[2][2] + " " + "|" + " " + printGrid[2][3] + " " + printGrid[2][4] + " " + printGrid[2][5] + " " + "|" + " " + printGrid[2][6] + " " + printGrid[2][7] + " " + printGrid[2][8] + " " + "|\n" +
                        "  +-------+-------+-------+\n" +
                        "D | " + printGrid[3][0] + " " + printGrid[3][1] + " " + printGrid[3][2] + " " + "|" + " " + printGrid[3][3] + " " + printGrid[3][4] + " " + printGrid[3][5] + " " + "|" + " " + printGrid[3][6] + " " + printGrid[3][7] + " " + printGrid[3][8] + " " + "|\n" +
                        "E | " + printGrid[4][0] + " " + printGrid[4][1] + " " + printGrid[4][2] + " " + "|" + " " + printGrid[4][3] + " " + printGrid[4][4] + " " + printGrid[4][5] + " " + "|" + " " + printGrid[4][6] + " " + printGrid[4][7] + " " + printGrid[4][8] + " " + "|\n" +
                        "F | " + printGrid[5][0] + " " + printGrid[5][1] + " " + printGrid[5][2] + " " + "|" + " " + printGrid[5][3] + " " + printGrid[5][4] + " " + printGrid[5][5] + " " + "|" + " " + printGrid[5][6] + " " + printGrid[5][7] + " " + printGrid[5][8] + " " + "|\n" +
                        "  +-------+-------+-------+\n" +
                        "G | " + printGrid[6][0] + " " + printGrid[6][1] + " " + printGrid[6][2] + " " + "|" + " " + printGrid[6][3] + " " + printGrid[6][4] + " " + printGrid[6][5] + " " + "|" + " " + printGrid[6][6] + " " + printGrid[6][7] + " " + printGrid[6][8] + " " + "|\n" +
                        "H | " + printGrid[7][0] + " " + printGrid[7][1] + " " + printGrid[7][2] + " " + "|" + " " + printGrid[7][3] + " " + printGrid[7][4] + " " + printGrid[7][5] + " " + "|" + " " + printGrid[7][6] + " " + printGrid[7][7] + " " + printGrid[7][8] + " " + "|\n" +
                        "I | " + printGrid[8][0] + " " + printGrid[8][1] + " " + printGrid[8][2] + " " + "|" + " " + printGrid[8][3] + " " + printGrid[8][4] + " " + printGrid[8][5] + " " + "|" + " " + printGrid[8][6] + " " + printGrid[8][7] + " " + printGrid[8][8] + " " + "|\n" +
                        "  +-------+-------+-------+"
        );
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

    public String getMove(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public int[] parseMove(String move){
        // will return {0,0,0} if quit, {10,10,10} if invalid, and if valid {row, col, value}
        move = move.trim();
        int[] values = new int[3];
        char firstLetter = move.charAt(0);
        char secondLetter = move.charAt(1);
        char number = move.charAt(2);

        if (move.equals("Quit")) {
            values = new int[]{100, 100, 100};
            return values;
        }

        if (move.length() != 3){
            values = new int[]{10, 10, 10};
            return values;
        }

        switch (firstLetter){
            case 'A':
                values[0] = 0;
                break;
            case 'B':
                values[0] = 1;
                break;
            case 'C':
                values[0] = 2;
                break;
            case 'D':
                values[0] = 3;
                break;
            case 'E':
                values[0] = 4;
                break;
            case 'F':
                values[0] = 5;
                break;
            case 'G':
                values[0] = 6;
                break;
            case 'H':
                values[0] = 7;
                break;
            case 'I':
                values[0] = 8;
                break;
            default:
                values = new int[]{10, 10, 10};
                return values;
        }
        switch (secondLetter){
            case 'A':
                values[1] = 0;
                break;
            case 'B':
                values[1] = 1;
                break;
            case 'C':
                values[1] = 2;
                break;
            case 'D':
                values[1] = 3;
                break;
            case 'E':
                values[1] = 4;
                break;
            case 'F':
                values[1] = 5;
                break;
            case 'G':
                values[1] = 6;
                break;
            case 'H':
                values[1] = 7;
                break;
            case 'I':
                values[1] = 8;
                break;
            default:
                values = new int[]{10, 10, 10};
                return values;
        }
        switch (number){
            case '1':
                values[2] = 1;
                break;
            case '2':
                values[2] = 2;
                break;
            case '3':
                values[2] = 3;
                break;
            case '4':
                values[2] = 4;
                break;
            case '5':
                values[2] = 5;
                break;
            case '6':
                values[2] = 6;
                break;
            case '7':
                values[2] = 7;
                break;
            case '8':
                values[2] = 8;
                break;
            case '9':
                values[2] = 9;
                break;
            default:
                values = new int[]{10, 10, 10};
                break;
        }
        return values;
    }

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

    public void updatePuzzle(int row, int col, int value){
        current[row][col] = value;
        getCurrent();
    }
}