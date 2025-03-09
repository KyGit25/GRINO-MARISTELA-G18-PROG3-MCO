/**
 * Represents a position on the board.
 */
public class Position {
    private int row;
    private int col;

    /**
     * Creates a new position on the board.
     * @param row The row index (0-8).
     * @param col The column index (0-6).
     */
    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Gets the row index.
     * @return The row index.
     */
    public int getRow() {
        return row;
    }

    /**
     * Gets the column index.
     * @return The column index.
     */
    public int getCol() {
        return col;
    }

    /**
     * Sets the row index.
     * @param row The new row index.
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * Sets the column index.
     * @param col The new column index.
     */
    public void setCol(int col) {
        this.col = col;
    }

    /**
     * Checks if this position is equal to another position.
     * @param other The position to compare.
     * @return True if both positions are the same.
     */
    public boolean equals(Position other) {
        return this.row == other.row && this.col == other.col;
    }

    /**
     * Converts the position to a string format.
     * @return A string representation of the position.
     */
    @Override
    public String toString() {
        return "(" + row + ", " + col + ")";
    }
}
