/**
 * The Lake class represents a lake tile on the game board.
 * It stores the position of the lake and provides methods to check its location.
 */
public class Lake {
    
    /**
     * The row position of the lake on the board.
     */
    private int row;
    
    /**
     * The column position of the lake on the board.
     */
    private int col;

    /**
     * Constructs a Lake object with the given row and column position.
     * 
     * @param row The row position of the lake.
     * @param col The column position of the lake.
     * 
     * Pre-condition: row and col must be within the board boundaries.
     * Post-condition: A new Lake object is created at the specified location.
     */
    public Lake(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Gets the row position of the lake.
     * 
     * @return The row position of the lake.
     * 
     * Pre-condition: Lake object must exist.
     * Post-condition: Returns the row position.
     */
    public int getRow() {
        return row;
    }

    /**
     * Gets the column position of the lake.
     * 
     * @return The column position of the lake.
     * 
     * Pre-condition: Lake object must exist.
     * Post-condition: Returns the column position.
     */
    public int getCol() {
        return col;
    }

    /**
     * Checks if the given row and column match the lake's position.
     * 
     * @param r The row position to check.
     * @param c The column position to check.
     * 
     * @return true if the given position matches the lake's location, false otherwise.
     * 
     * Pre-condition: r and c must be valid board positions.
     * Post-condition: Returns whether the given position is a lake tile.
     */
    public boolean isLakeTile(int r, int c) {
        return this.row == r && this.col == c;
    }
}
