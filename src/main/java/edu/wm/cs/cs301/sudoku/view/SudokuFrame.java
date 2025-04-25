package edu.wm.cs.cs301.sudoku.view;

import edu.wm.cs.cs301.sudoku.model.SudokuPuzzle;
import edu.wm.cs.cs301.sudoku.controller.InsertNumber;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

/**
 * The {@code SudokuFrame} class serves as the main GUI window for the Sudoku application.
 * It assembles all core components including the puzzle grid, number input buttons,
 * and menu bar. It also handles user interaction events such as restarting the game,
 * quitting the application, and launching the instructions or victory screen.
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
        JPanel wrapperPanel = new JPanel();
        wrapperPanel.setLayout(new BoxLayout(wrapperPanel, BoxLayout.Y_AXIS));

        JPanel numberGridPanel = new JPanel();
        numberGridPanel.setLayout(new GridLayout(3,3,5,5));
        numberGridPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
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

        JButton deleteButton = new JButton("Delete");
        deleteButton.setForeground(Color.BLACK);
        deleteButton.setBackground(Color.LIGHT_GRAY);
        deleteButton.setFont(titleFont);
        deleteButton.setOpaque(true);

        deleteButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        deleteButton.setMaximumSize(new Dimension(Short.MAX_VALUE, deleteButton.getPreferredSize().height));

        deleteButton.addActionListener(event ->
        {InsertNumber inserter = new InsertNumber();
            int row = panel.getSelectedRow();
            int col = panel.getSelectedCol();
            int value = 0;
            Rectangle cell = panel.getGridCell(row,col);
            inserter.insertNumber(cell, row, col, model, value, panel, this);});


        wrapperPanel.add(Box.createVerticalStrut(200));
        wrapperPanel.add(numberGridPanel);
        wrapperPanel.add(Box.createVerticalStrut(10));
        wrapperPanel.add(deleteButton);
        wrapperPanel.add(Box.createVerticalGlue());

        wrapperPanel.setBorder(BorderFactory.createEmptyBorder(25, 0, 125, 30));

        return wrapperPanel;
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

    /**
     * Restarts the game by disposing of the current game window and launching a new instance
     * of {@code SudokuFrame} with a fresh {@code SudokuPuzzle}.
     */
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

    /**
     * Triggers the display of the victory screen by launching a new {@code Victory} window.
     */
    public void victory(){
        new Victory(this);
    }

    /**
     * Disposes of the main game frame, effectively closing the window without exiting the program.
     */
    public void dispose(){
        frame.dispose();
    }


}
