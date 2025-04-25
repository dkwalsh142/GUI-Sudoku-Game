package edu.wm.cs.cs301.sudoku.view;

import edu.wm.cs.cs301.sudoku.model.AppColors;
import edu.wm.cs.cs301.sudoku.model.SudokuPuzzle;

import java.awt.*;
import javax.swing.JPanel;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class SudokuGridPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private final int topMargin, leftMargin, letterWidth;

    private final Rectangle[][] grid;

    private final SudokuPuzzle model;

    private int selectedRow = -1;

    private int selectedCol = -1;

    public SudokuGridPanel(SudokuFrame view, SudokuPuzzle model, int width) {
        this.model = model;
        this.topMargin = 20;
        this.letterWidth = 64;

        this.leftMargin = 20;
        int height = (letterWidth) * 9 + 2 * topMargin;
        this.setPreferredSize(new Dimension(width, height));

        this.grid = calculateRectangles();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Point clickPoint = e.getPoint();
                handleCellClick(clickPoint);
            }
        });

    }

    private void handleCellClick(Point p){
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col].contains(p)) {
                    selectedRow = row;
                    selectedCol = col;
                    System.out.println("Clicked cell: " + row + ", " + col);
                    repaint(); // optional: use to highlight the cell
                    return;
                }
            }
        }
    }



    private Rectangle[][] calculateRectangles() {
        Rectangle[][] grid = new Rectangle[9][9];

        int x = leftMargin;
        int y = topMargin;

        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                grid[row][column] = new Rectangle(x, y, letterWidth,
                        letterWidth);
                x += letterWidth;
            }
            x = leftMargin;
            y += letterWidth;
        }

        return grid;
    }

    public Rectangle getGridCell(int row, int col) {
        return grid[selectedRow][selectedCol];
    }

    public int getSelectedRow() {
        return selectedRow;
    }

    public int getSelectedCol() {
        return selectedCol;
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
        int[][] startGrid = model.getSetup();

        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid[row].length; column++) {
                Rectangle r = grid[row][column];
                drawOutline(g2d, r);
                if (startGrid[row][column] != 0){
                    fillCell(g2d, r, Color.LIGHT_GRAY);
                }
                if (row == getSelectedRow() && column == getSelectedCol()){
                    fillCell(g2d, r, Color.YELLOW);
                }
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

    private void fillCell(Graphics2D g2d, Rectangle r, Color color){
        int x = r.x + 1;
        int y = r.y + 1;
        int width = r.width - 2;
        int height = r.height - 2;

        g2d.setColor(color);
        g2d.fillRect(x, y, width, height);
    }

    public void updateGrid(){
        repaint();
    }

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
                if (puzzle[i][x]!=0){
                    drawCenteredString(g2d, str, r, titleFont);
                }
                x++;
            }
            i++;
        }
    }



    private void drawCenteredString(Graphics2D g2d, String text, Rectangle rect, Font font) {
        FontMetrics metrics = g2d.getFontMetrics(font);
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2)
                + metrics.getAscent();

        g2d.setFont(font);
        g2d.setColor(Color.BLACK);
        g2d.drawString(text, x, y);
    }
}

