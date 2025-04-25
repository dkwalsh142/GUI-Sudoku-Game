package edu.wm.cs.cs301.sudoku.controller;

import edu.wm.cs.cs301.sudoku.model.SudokuPuzzle;
import edu.wm.cs.cs301.sudoku.view.SudokuGridPanel;
import edu.wm.cs.cs301.sudoku.view.SudokuFrame;

import java.awt.*;

/**
 * The {@code InsertNumber} class provides the logic for attempting to insert a number
 * into a Sudoku puzzle. It checks whether a move is legal, updates the puzzle model,
 * triggers a UI refresh, and handles victory detection.
 */


/**
 * Attempts to insert a number into the Sudoku grid at the specified location.
 * If the move is legal, the model is updated, the view is refreshed, and victory
 * logic is triggered if the puzzle is complete and correct.
 *
 * param r the rectangle representing the target cell on the UI grid
 * param row the row index of the cell
 * param column the column index of the cell
 * param model the current Sudoku puzzle model
 * param value the numeric value to insert
 * param panel the panel responsible for rendering the Sudoku grid
 * param parentFrame the main Sudoku frame used to launch the victory screen
 */
public class InsertNumber {
    public void insertNumber(Rectangle r, int row, int column, SudokuPuzzle model, int value, SudokuGridPanel panel, SudokuFrame parentFrame){
        if(model.checkMoveLegal(row, column, value)){
            model.updatePuzzle(row, column, value);
            panel.updateGrid();
            if(model.checkGrid(model.getCurrent())){
                parentFrame.victory();
            }
        }
    }
}
