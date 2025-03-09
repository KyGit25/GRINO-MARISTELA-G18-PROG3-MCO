/**
 * The HomeBase class represents a player's home base in the game.
 * It stores the position and owner of the home base.
 */
public class HomeBase {
    
    /**
     * The row position of the home base on the board.
     */
    private int row;
    
    /**
     * The column position of the home base on the board.
     */
    private int col;
    
    /**
     * The owner of the home base ("Blue" or "Green").
     */
    private String owner;

    /**
     * Constructs a HomeBase with the given row, column, and owner.
     * 
     * @param row   The row position of the home base.
     * @param col   The column position of the home base.
     * @param owner The owner of the home base ("Blue" or "Green").
     * 
     * Pre-condition: row and col must be within the board boundaries.
     * Post-condition: A new HomeBase object is created with the specified attributes.
     */
    public HomeBase(int row, int col, String owner) {
        this.row = row;
        this.col = col;
        this.owner = owner;
    }

    /**
     * Gets the row position of the home base.
     * 
     * @return The row position of the home base.
     * 
     * Pre-condition: HomeBase object must exist.
     * Post-condition: Returns the row position.
     */
    public int getRow() {
        return row;
    }

    /**
     * Gets the column position of the home base.
     * 
     * @return The column position of the home base.
     * 
     * Pre-condition: HomeBase object must exist.
     * Post-condition: Returns the column position.
     */
    public int getCol() {
        return col;
    }

    /**
     * Gets the owner of the home base.
     * 
     * @return The owner of the home base ("Blue" or "Green").
     * 
     * Pre-condition: HomeBase object must exist.
     * Post-condition: Returns the owner's name.
     */
    public String getOwner() {
        return owner;
    }
}
