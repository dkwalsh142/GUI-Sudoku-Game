package edu.wm.cs.cs301.sudoku.view;

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


public class SudokuFrame {
    private final JFrame frame;

    public SudokuFrame() {
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

        frame.pack();
        frame.setMinimumSize(frame.getPreferredSize());
        frame.setLocationByPlatform(true);
        frame.setVisible(true);

        System.out.println("Frame size: " + frame.getSize());

        return frame;
    }

    private JMenuBar createMenu(){
        JMenuBar menuBar = new JMenuBar();

        JMenu restartMenu = new JMenu("Restart");
        menuBar.add(restartMenu);

        JMenu instructionsMenu = new JMenu("Instructions");
        menuBar.add(instructionsMenu);

        JMenu quitMenu = new JMenu("Quit");
        menuBar.add(quitMenu);


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
