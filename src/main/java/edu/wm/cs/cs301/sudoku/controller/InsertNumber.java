package edu.wm.cs.cs301.sudoku.controller;

import edu.wm.cs.cs301.sudoku.model.SudokuPuzzle;
import edu.wm.cs.cs301.sudoku.view.SudokuGridPanel;
import edu.wm.cs.cs301.sudoku.view.SudokuFrame;

import java.awt.*;

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
