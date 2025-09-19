import java.util.HashMap;
import java.util.Map;

class Spreadsheet {

    // A 2D array to store the spreadsheet data.
    private int[][] grid;
    // The number of rows in the spreadsheet.
    private int rows;
    // The number of columns is fixed at 26 ('A' to 'Z').
    private int cols = 26;

    /**
     * Initializes a spreadsheet with the given number of rows.
     * All cells are set to 0 by default.
     *
     * @param rows The number of rows for the spreadsheet.
     */
    public Spreadsheet(int rows) {
        this.rows = rows;
        this.grid = new int[rows][cols];
    }

    /**
     * Sets the value of a specific cell.
     *
     * @param cell  The cell reference (e.g., "A1", "B10").
     * @param value The integer value to set in the cell.
     */
    public void setCell(String cell, int value) {
        int[] coords = parseCell(cell);
        int row = coords[0];
        int col = coords[1];
        grid[row][col] = value;
    }

    /**
     * Resets the value of a specific cell to 0.
     *
     * @param cell The cell reference to reset.
     */
    public void resetCell(String cell) {
        setCell(cell, 0);
    }

    /**
     * Evaluates a formula of the form "=X+Y" and returns the sum.
     * X and Y can be cell references or non-negative integers.
     *
     * @param formula The formula string to evaluate.
     * @return The computed sum.
     */
    public int getValue(String formula) {
        // Remove the leading '=' sign
        String expression = formula.substring(1);
        // Split the expression by the '+' sign
        String[] terms = expression.split("\\+");
        
        // Evaluate each term and sum them up
        int value1 = evaluateTerm(terms[0]);
        int value2 = evaluateTerm(terms[1]);
        
        return value1 + value2;
    }

    /**
     * Helper method to parse a cell reference string (e.g., "A1") into
     * 0-indexed row and column coordinates.
     *
     * @param cell The cell reference string.
     * @return An integer array containing [row, col].
     */
    private int[] parseCell(String cell) {
        int col = cell.charAt(0) - 'A';
        int row = Integer.parseInt(cell.substring(1)) - 1;
        return new int[]{row, col};
    }

    /**
     * Helper method to evaluate a single term, which can be either a
     * cell reference or an integer literal.
     *
     * @param term The term string to evaluate.
     * @return The integer value of the term.
     */
    private int evaluateTerm(String term) {
        // Check if the term is a cell reference (starts with a letter)
        if (Character.isLetter(term.charAt(0))) {
            int[] coords = parseCell(term);
            int row = coords[0];
            int col = coords[1];
            // Return the value from the grid, defaults to 0 if not set
            return grid[row][col];
        } else {
            // Otherwise, it's a number, so parse it
            return Integer.parseInt(term);
        }
    }
}

/**
 * Your Spreadsheet object will be instantiated and called as such:
 * Spreadsheet obj = new Spreadsheet(rows);
 * obj.setCell(cell,value);
 * obj.resetCell(cell);
 * int param_3 = obj.getValue(formula);
 */