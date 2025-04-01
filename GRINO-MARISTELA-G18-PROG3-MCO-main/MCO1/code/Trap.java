/**
 * The Trap class represents a trap tile on the game board.
 * It stores the position, owner, and occupancy status of the trap.
 */
public class Trap {
    
    /**
     * The row position of the trap on the board.
     */
    private int row;
    
    /**
     * The column position of the trap on the board.
     */
    private int col;
    
    /**
     * Indicates whether the trap is occupied by a piece.
     */
    private boolean isOccupied;
    
    /**
     * The owner of the trap ("Blue" or "Green").
     */
    private String owner;

    /**
     * Constructs a Trap object with the given row, column, and owner.
     * 
     * @param row   The row position of the trap.
     * @param col   The column position of the trap.
     * @param owner The owner of the trap ("Blue" or "Green").
     * 
     * Pre-condition: row and col must be within the board boundaries.
     * Post-condition: A new Trap object is created with the specified attributes.
     */
    public Trap(int row, int col, String owner) {
        this.row = row;
        this.col = col;
        this.isOccupied = false;
        this.owner = owner;
    }

    /**
     * Gets the row position of the trap.
     * 
     * @return The row position of the trap.
     * 
     * Pre-condition: Trap object must exist.
     * Post-condition: Returns the row position.
     */
    public int getRow() {
        return row;
    }

    /**
     * Gets the column position of the trap.
     * 
     * @return The column position of the trap.
     * 
     * Pre-condition: Trap object must exist.
     * Post-condition: Returns the column position.
     */
    public int getCol() {
        return col;
    }

    /**
     * Checks if the trap is occupied by a piece.
     * 
     * @return true if the trap is occupied, false otherwise.
     * 
     * Pre-condition: Trap object must exist.
     * Post-condition: Returns the occupancy status.
     */
    public boolean isOccupied() {
        return isOccupied;
    }

    /**
     * Sets the occupancy status of the trap.
     * 
     * @param occupied true if a piece is occupying the trap, false otherwise.
     * 
     * Pre-condition: Trap object must exist.
     * Post-condition: Updates the occupancy status.
     */
    public void setOccupied(boolean occupied) {
        this.isOccupied = occupied;
    }

    /**
     * Gets the owner of the trap.
     * 
     * @return The owner of the trap ("Blue" or "Green").
     * 
     * Pre-condition: Trap object must exist.
     * Post-condition: Returns the owner's name.
     */
    public String getOwner() {
        return owner;
    }
}
