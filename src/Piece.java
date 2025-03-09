/**
 * Abstract class representing a game piece (animal) on the board.
 */
public abstract class Piece {
    protected String name;
    protected Position position;
    protected int strength;
    protected boolean isCaptured;

    /**
     * Creates a new game piece.
     * @param name The name of the piece (e.g., "Lion", "Rat").
     * @param row The starting row position.
     * @param col The starting column position.
     * @param strength The strength level of the piece.
     */
    public Piece(String name, int row, int col, int strength) {
        this.name = name;
        this.position = new Position(row, col);
        this.strength = strength;
        this.isCaptured = false;
    }

    /**
     * Gets the name of the piece.
     * @return The piece name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the current position of the piece.
     * @return The position of the piece.
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Sets a new position for the piece.
     * @param newPosition The new position.
     */
    public void setPosition(Position newPosition) {
        this.position = newPosition;
    }

    /**
     * Checks if the piece has been captured.
     * @return True if captured.
     */
    public boolean isCaptured() {
        return isCaptured;
    }

    /**
     * Marks the piece as captured.
     */
    public void capture() {
        isCaptured = true;
    }

    /**
     * Moves the piece based on the game rules.
     * @param direction The movement direction.
     * @param board The game board.
     * @param opponent The opposing player.
     */
    public abstract void move(char direction, Board board, Player opponent);
}
