package edu.wm.cs.cs301.sudoku.view;

import edu.wm.cs.cs301.sudoku.model.SudokuPuzzle;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class Victory {
    private final JFrame frame;
    private final SudokuFrame gameFrame;

    public Victory(SudokuFrame panel) {
        this.gameFrame = panel;
        this.frame = createAndShowGUI();
    }

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

    private JPanel createMainPanel() {
        JPanel panel = new JPanel();
        JLabel victoryText = new JLabel(victoryTextHTML);
        panel.add(victoryText);

        return panel;
    }

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

    private void shutdown(WindowEvent event) {
        event.getWindow().dispose();
    }

    private void shutdownFull() {
        System.exit(0);
    }

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


