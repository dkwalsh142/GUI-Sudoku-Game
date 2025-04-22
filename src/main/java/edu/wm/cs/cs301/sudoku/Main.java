package edu.wm.cs.cs301.sudoku;

import javax.swing.*;
//import edu.wm.cs.cs301.sudoku.controller.;
//import edu.wm.cs.cs301.sudoku.model.;
import edu.wm.cs.cs301.sudoku.view.SudokuFrame;
import edu.wm.cs.cs301.sudoku.view.InstructionsFrame;


public class Main implements Runnable {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Main());

        //Can't use the Cross-Platform Look and Feel on Windows - Needs investigation
        if (!System.getProperty("os.name").contains("Windows")) {
            //Must use cross-platform look and feel so button backgrounds work on Mac
            try {
                System.out.println("OS: " + System.getProperty("os.name"));
                // Uncomment to debug available LAFs on current system
/*
                System.out.println("Cross-Platform name: " + UIManager.getCrossPlatformLookAndFeelClassName());
                System.out.println("System name: " + UIManager.getSystemLookAndFeelClassName());
                UIManager.LookAndFeelInfo[] installed = UIManager.getInstalledLookAndFeels();
                for(UIManager.LookAndFeelInfo info : installed) {
                    System.out.println("INSTALLED: " + info);
                }
*/

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
        new SudokuFrame();
        new InstructionsFrame();
    }
}