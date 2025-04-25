package edu.wm.cs.cs301.sudoku;

import javax.swing.*;
//import edu.wm.cs.cs301.sudoku.controller.;
import edu.wm.cs.cs301.sudoku.model.SudokuPuzzle;
import edu.wm.cs.cs301.sudoku.view.SudokuFrame;
import edu.wm.cs.cs301.sudoku.view.InstructionsFrame;


public class Main implements Runnable {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Main());

        if (!System.getProperty("os.name").contains("Windows")) {
            try {
                System.out.println("OS: " + System.getProperty("os.name"));
                UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
            }
            catch (Exception e) {
                System.out.println("Failed to load Cross-Platform Look and Feel on non-Windows system.");
                System.out.println("GUI may not function correctly");
            }
        }
    }

    @Override
    public void run() {
        new SudokuFrame(new SudokuPuzzle());
        new InstructionsFrame();
    }
}