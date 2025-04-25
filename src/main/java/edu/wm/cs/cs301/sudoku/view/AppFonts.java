package edu.wm.cs.cs301.sudoku.view;

import java.awt.Font;

/**
 * The {@code AppFonts} utility class provides application-wide access to
 * standardized font styles used for rendering titles and text in the Sudoku GUI.
 */


public class AppFonts {

    /**
     * Returns the font used for large title text in the Sudoku application.
     * @return a {@code Font} object with bold styling and size 36
     */
    public static Font getTitleFont() {
        return new Font("Dialog", Font.BOLD, 36);
    }

    /**
     * Returns the font used for general UI text, such as button labels and cell numbers.
     * @return a {@code Font} object with bold styling and size 24
     */
    public static Font getTextFont() {
        return new Font("Dialog", Font.BOLD, 24);
    }
}