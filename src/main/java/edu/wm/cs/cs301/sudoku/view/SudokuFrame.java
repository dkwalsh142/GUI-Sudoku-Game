package edu.wm.cs.cs301.sudoku.view;

import edu.wm.cs.cs301.sudoku.model.AppColors;
import edu.wm.cs.cs301.sudoku.model.SudokuPuzzle;
import edu.wm.cs.cs301.sudoku.controller.InsertNumber;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

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
        this.width = 650;
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
        frame.add(createNumberGrid(sudokuGridPanel), BorderLayout.EAST);
        //frame.add(createDeleteButton(), BorderLayout.EAST);

        frame.pack();
        frame.setMinimumSize(frame.getPreferredSize());
        frame.setLocation(250, 100);
        frame.setVisible(true);

        System.out.println("Frame size: " + frame.getSize());

        return frame;
    }

    private JPanel createNumberGrid(SudokuGridPanel panel){
        JPanel numberGridPanel = new JPanel();

        numberGridPanel.setLayout(new GridLayout(3,3,5,5));
        Font titleFont = AppFonts.getTextFont();

        for (int i = 1; i <= 9; i++) {
            JButton button = new JButton("" + i);
            button.setForeground(Color.BLACK);
            button.setBackground(Color.LIGHT_GRAY);
            button.setFont(titleFont);
            button.setOpaque(true);

            int finalI = i;
            button.addActionListener(event ->
                {InsertNumber inserter = new InsertNumber();
                int row = panel.getSelectedRow();
                int col = panel.getSelectedCol();
                int value = finalI;
                Rectangle cell = panel.getGridCell(row,col);
                inserter.insertNumber(cell, row, col, model, value, panel, this);});

            numberGridPanel.add(button);
        }

        numberGridPanel.setBorder(BorderFactory.createEmptyBorder(210, 0, 210, 20));

        return numberGridPanel;
    }

    private JPanel createDeleteButton(){
        JPanel deletePanel = new JPanel();
        JButton deleteButton = new JButton("Delete");
        deletePanel.add(deleteButton);

        return deletePanel;
    }

    private JMenuBar createMenu(){
        JMenuBar menuBar = new JMenuBar();

        JMenu gameMenu = new JMenu("Options");

        JMenuItem restartItem = new JMenuItem("Restart");
        restartItem.addActionListener(event -> restartGame());
        gameMenu.add(restartItem);

        JMenuItem instructionsItem = new JMenuItem("Instructions");
        instructionsItem.addActionListener(event-> new InstructionsFrame());
        gameMenu.add(instructionsItem);

        JMenuItem quitItem = new JMenuItem("Quit");
        quitItem.addActionListener(event-> shutdown());
        gameMenu.add(quitItem);

        menuBar.add(gameMenu);

        return menuBar;
    }

    public void restartGame() {
        frame.dispose();

        SudokuPuzzle newModel = new SudokuPuzzle();
        new SudokuFrame(newModel);
    }

    private void shutdown() {
        System.exit(0);
    }

    private void close(WindowEvent event) {
        event.getWindow().dispose();
    }

    public void victory(){
        new Victory(this);
    }

    public void dispose(){
        frame.dispose();
    }


}
