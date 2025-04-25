package edu.wm.cs.cs301.sudoku.controller;

import edu.wm.cs.cs301.sudoku.model.SudokuPuzzle;
import edu.wm.cs.cs301.sudoku.view.SudokuGridPanel;

import java.awt.*;

public class InsertNumber {
    public void insertNumber(Rectangle r, int row, int column, SudokuPuzzle model, int value, SudokuGridPanel panel){
        if(model.checkMoveLegal(row, column, value)){
            model.updatePuzzle(row, column, value);
            panel.updateGrid();
            if(model.checkGrid(model.getCurrent())){
                panel.victory();
            }
        }
    }
}
