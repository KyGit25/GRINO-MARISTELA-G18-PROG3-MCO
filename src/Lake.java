/**
 * Represents a lake on the board.
 * Only Rats can swim in the lake, while Lions and Tigers can leap over it.
 */
public class Lake {
    private Position position;

    /**
     * Creates a lake at a specific position.
     * @param row The row index of the lake.
     * @param col The column index of the lake.
     */
    public Lake(int row, int col) {
        this.position = new Position(row, col);
    }

    /**
     * Gets the position of the lake.
     * @return The lake's position.
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Checks if a given position is in the lake.
     * @param piecePosition The position of the piece.
     * @return True if the piece is on the lake.
     */
    public boolean isPieceInLake(Position piecePosition) {
        return position.equals(piecePosition);
    }
}
