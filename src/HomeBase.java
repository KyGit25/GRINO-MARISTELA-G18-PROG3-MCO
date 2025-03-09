/**
 * Represents a player's home base.
 * The game ends when a piece reaches the opponent's home base.
 */
public class HomeBase {
    private Position position;

    /**
     * Creates a home base at a specific position.
     * @param row The row index of the home base.
     * @param col The column index of the home base.
     */
    public HomeBase(int row, int col) {
        this.position = new Position(row, col);
    }

    /**
     * Gets the position of the home base.
     * @return The home base's position.
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Checks if a piece is on this home base.
     * @param piecePosition The position of the piece.
     * @return True if the piece has reached the home base.
     */
    public boolean isPieceAtHomeBase(Position piecePosition) {
        return position.equals(piecePosition);
    }
}
