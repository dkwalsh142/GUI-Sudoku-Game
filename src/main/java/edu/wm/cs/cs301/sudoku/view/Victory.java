package edu.wm.cs.cs301.sudoku.view;

import edu.wm.cs.cs301.sudoku.model.SudokuPuzzle;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

/**
 * The {@code Victory} class creates and displays a pop-up window when the user
 * successfully completes the Sudoku puzzle. It provides congratulatory text along
 * with buttons to either start a new game or quit the application.
 */

public class Victory {
    private final JFrame frame;
    private final SudokuFrame gameFrame;

    public Victory(SudokuFrame panel) {
        this.gameFrame = panel;
        this.frame = createAndShowGUI();
    }

    /**
     * Initializes the victory window by setting up its layout, behavior, and content.
     * Configures the frame to prevent default closing and attaches a shutdown listener.
     *
     * @return the initialized {@code JFrame} representing the victory screen
     */
    private JFrame createAndShowGUI() {
        JFrame frame = new JFrame("VICTORY!");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setResizable(false);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                shutdown(event);
            }
        });

        frame.add(createMainPanel(), BorderLayout.NORTH);
        frame.add(createButtonsPanel(), BorderLayout.CENTER);

        frame.pack();
        frame.setMinimumSize(frame.getPreferredSize());
        frame.setLocation(380, 240);
        frame.setVisible(true);

        return frame;
    }

    /**
     * Creates the main panel containing the victory message in styled HTML format.
     *
     * @return a {@code JPanel} containing the congratulatory text
     */
    private JPanel createMainPanel() {
        JPanel panel = new JPanel();
        JLabel victoryText = new JLabel(victoryTextHTML);
        panel.add(victoryText);

        return panel;
    }

    /**
     * Creates a panel with two buttons: one to restart the game and another to quit
     * the application.
     *
     * @return a {@code JPanel} containing the play again and quit buttons
     */
    private JPanel createButtonsPanel(){
        JPanel buttonsPanel = new JPanel();
        JButton playAgain = new JButton("Play Again");
        playAgain.addActionListener(event -> restartGame());

        JButton quit = new JButton("Quit");
        quit.addActionListener(event -> shutdownFull());

        buttonsPanel.add(playAgain);
        buttonsPanel.add(quit);

        return buttonsPanel;
    }

    /**
     * Disposes of the victory frame when the user attempts to close the window manually.
     *
     * @param event the {@code WindowEvent} triggered by the close request
     */
    private void shutdown(WindowEvent event) {
        event.getWindow().dispose();
    }

    /**
     * Exits the entire application immediately when the user clicks the "Quit" button.
     */
    private void shutdownFull() {
        System.exit(0);
    }

    /**
     * Restarts the Sudoku game by closing both the current victory window and the
     * existing game frame. A new puzzle model and frame are created to start fresh.
     */
    private void restartGame() {
        gameFrame.dispose();
        frame.dispose();

        SudokuPuzzle newModel = new SudokuPuzzle();
        new SudokuFrame(newModel);

    }

    String victoryTextHTML = """
        <html>
          <body style="text-align:center; font-family:sans-serif; background-color:#f0f8ff; padding:20px;">
            <h1 style="color:#2e8b57; font-size:32px;">🎉 Congratulations!</h1>
            <p style="font-size:18px; color:#333;">
              You've successfully completed the Sudoku puzzle!
            </p>
            <p style="font-size:16px; color:#666;">
              Take a moment to bask in your logic and number mastery.
            </p>
            <hr style="margin:20px 0;">
          </body>
        </html>""";
}


