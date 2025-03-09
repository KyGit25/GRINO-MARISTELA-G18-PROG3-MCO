/**
 * Represents a trap on the board.
 * When an opponent's piece lands on the trap, it becomes weak.
 */
public class Trap {
    private Position position;

    /**
     * Creates a trap at a specific position.
     * @param row The row index of the trap.
     * @param col The column index of the trap.
     */
    public Trap(int row, int col) {
        this.position = new Position(row, col);
    }

    /**
     * Gets the position of the trap.
     * @return The trap's position.
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Checks if a piece is on this trap.
     * @param piecePosition The position of the piece.
     * @return True if the piece is on the trap.
     */
    public boolean isPieceOnTrap(Position piecePosition) {
        return position.equals(piecePosition);
    }
}
