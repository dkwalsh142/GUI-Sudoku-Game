package edu.wm.cs.cs301.sudoku.view;

import edu.wm.cs.cs301.sudoku.model.AppColors;
import edu.wm.cs.cs301.sudoku.model.SudokuPuzzle;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.JPanel;
public class SudokuGridPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private final int topMargin, leftMargin, letterWidth;

    private final Insets insets;

    private final Rectangle[][] grid;

    private final SudokuPuzzle model;

    public SudokuGridPanel(SudokuFrame view, SudokuPuzzle model, int width) {
        this.model = model;
        this.topMargin = 0;
        this.letterWidth = 64;
        this.insets = new Insets(0, 0, 0, 0);

        int wordWidth = (letterWidth + insets.right) * 9;
        this.leftMargin = (width - wordWidth) / 2;
        int height = (letterWidth + insets.bottom) * 9
                + 2 * topMargin;
        this.setPreferredSize(new Dimension(width, height));

        this.grid = calculateRectangles();
    }

    private Rectangle[][] calculateRectangles() {
        Rectangle[][] grid = new Rectangle[9][9];

        int x = leftMargin;
        int y = topMargin;

        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                grid[row][column] = new Rectangle(x, y, letterWidth,
                        letterWidth);
                x += letterWidth + insets.right;
            }
            x = leftMargin;
            y += letterWidth + insets.bottom;
        }

        return grid;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);


        int[][] sudokuGrid = model.getCurrent();
        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid[row].length; column++) {
                Rectangle r = grid[row][column];
                int sudokuResponse = sudokuGrid[row][column];
                drawOutline(g2d, r);
            }
        }
        sendNumbers(g2d, model, grid);
    }

    private void drawOutline(Graphics2D g2d, Rectangle r) {
        int x = r.x + 1;
        int y = r.y + 1;
        int width = r.width - 2;
        int height = r.height - 2;
        g2d.setColor(AppColors.OUTLINE);
        g2d.setStroke(new BasicStroke(3f));
        g2d.drawLine(x, y, x + width, y);
        g2d.drawLine(x, y + height, x + width, y + height);
        g2d.drawLine(x, y, x, y + height);
        g2d.drawLine(x + width, y, x + width, y + height);
    }


    /**private void drawSudokuResponse(Graphics2D g2d,
                                    int wordleResponse, Rectangle r, Font titleFont) {
        if (wordleResponse != null) {
            g2d.setColor(wordleResponse.getBackgroundColor());
            g2d.fillRect(r.x, r.y, r.width, r.height);
            g2d.setColor(wordleResponse.getForegroundColor());
            drawCenteredString(g2d,
                    Character.toString(wordleResponse.getChar()), r, titleFont);
        }
    }*/

    private void sendNumbers(Graphics2D g2d, SudokuPuzzle model, Rectangle[][] grid){
        int i = 0;
        int x = 0;
        int[][] puzzle = model.getCurrent();
        Font titleFont = AppFonts.getTitleFont();

        while(i<9){
            x = 0;
            while (x<9){
                String str;
                str = String.valueOf(puzzle[i][x]);
                Rectangle r = grid[i][x];
                drawCenteredString(g2d, str, r, titleFont);
                x++;
            }
            i++;
        }
    }

    /**
     * Draw a String centered in the middle of a Rectangle.
     *
     * @param g2d  The Graphics instance.
     * @param text The String to draw.
     * @param rect The Rectangle to center the text in.
     */


    private void drawCenteredString(Graphics2D g2d, String text, Rectangle rect, Font font) {
        FontMetrics metrics = g2d.getFontMetrics(font);
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2)
                + metrics.getAscent();

        g2d.setFont(font);
        g2d.drawString(text, x, y);
    }
}

