package edu.wm.cs.cs301.sudoku.view;

import edu.wm.cs.cs301.sudoku.model.SudokuPuzzle;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

/**
 * This class assembles and displays the window holding the sudoku puzzle
 */

public class SudokuFrame {
    private final JFrame frame;
    private final SudokuPuzzle model;
    private final SudokuGridPanel sudokuGridPanel;
    private int width;

    public SudokuFrame(SudokuPuzzle model) {
        this.model = model;
        this.width = 1000;
        this.sudokuGridPanel = new SudokuGridPanel(this, model, width);
        this.frame = createAndShowGUI();

    }

    private JFrame createAndShowGUI() {
        JFrame frame = new JFrame("Sudoku");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setResizable(false);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                shutdown();
            }
        });

        frame.add(createMenu(), BorderLayout.NORTH);
        frame.add(sudokuGridPanel, BorderLayout.CENTER);

        frame.pack();
        frame.setMinimumSize(frame.getPreferredSize());
        frame.setLocationByPlatform(true);
        frame.setVisible(true);

        System.out.println("Frame size: " + frame.getSize());

        return frame;
    }

    private JMenuBar createMenu(){
        JMenuBar menuBar = new JMenuBar();

        JMenuItem restartItem = new JMenuItem("Restart");
        menuBar.add(restartItem);

        JMenuItem instructionsItem = new JMenuItem("Instructions");
        instructionsItem.addActionListener(event-> new InstructionsFrame());
        menuBar.add(instructionsItem);

        JMenuItem quitItem = new JMenuItem("Quit");
        quitItem.addActionListener(event-> shutdown());
        menuBar.add(quitItem);


        return menuBar;
    }

    private class CancelAction extends AbstractAction {

        private static final long serialVersionUID = 1L;

        @Override
        public void actionPerformed(ActionEvent event) {
            shutdown();
        }

    }

    private void shutdown() {
        System.exit(0);
    }
}
