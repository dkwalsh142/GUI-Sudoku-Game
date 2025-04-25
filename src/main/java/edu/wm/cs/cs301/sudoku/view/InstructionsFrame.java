package edu.wm.cs.cs301.sudoku.view;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The {@code InstructionsFrame} class creates and displays a window containing
 * gameplay instructions for the Sudoku application.
 */

public class InstructionsFrame {
    private final JFrame frame;

    public InstructionsFrame() {
        this.frame = createAndShowGUI();
    }

    /**
     * Constructs and initializes the instructions window, including its content and
     * layout configuration. Sets up shutdown behavior and returns the created frame.
     *
     * @return the initialized {@code JFrame} containing the instructions content
     */

    private JFrame createAndShowGUI() {
        JFrame frame = new JFrame("Instructions");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setResizable(false);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                shutdown(event);
            }
        });

        frame.add(createMainPanel(), BorderLayout.NORTH);

        frame.pack();
        frame.setMinimumSize(frame.getPreferredSize());
        frame.setLocation(380, 240);
        frame.setVisible(true);

        System.out.println("Frame size: " + frame.getSize());

        return frame;
    }

    private JPanel createMainPanel() {
        JPanel panel = new JPanel();
        JLabel instructions = new JLabel(instructionsHTML);
        panel.add(instructions);

        return panel;
    }

    private void shutdown(WindowEvent event) {
        event.getWindow().dispose();
    }

    String instructionsHTML = """
<html>
  <body style='font-family: sans-serif;'>
    <h2>How to Play Sudoku</h2>
    <p>
      Sudoku is a logic-based number puzzle played on a 9x9 grid, divided into nine 3x3 subgrids. <br>
      Use the keypad to fill each cell with a digit from <strong>1 to 9</strong>, following these rules:
    </p>
    <ul>
      <li>Each row must contain every digit from 1 to 9 exactly once.</li>
      <li>Each column must also contain every digit from 1 to 9.</li>
      <li>Each 3x3 box must contain every digit from 1 to 9.</li>
    </ul>
    <p>
      Use logic and deduction to determine the correct placement of each number. 
      No guessing required!
    </p>
    <p><em>Tip:</em> Start with rows, columns, or boxes that are nearly full.</p>
  </body>
</html>
""";
}

