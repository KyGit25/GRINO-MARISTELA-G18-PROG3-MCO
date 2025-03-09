/**
 * Represents the Rat piece, which can swim in lakes.
 */
public class Rat extends Piece {
    /**
     * Creates a Rat piece.
     * @param row The starting row position.
     * @param col The starting column position.
     */
    public Rat(int row, int col) {
        super("Rat", row, col, 1);
    }

    /**
     * Moves the Rat, allowing swimming and normal movement.
     * @param direction The movement direction (W, A, S, D).
     * @param board The game board.
     */
    @Override
    public void move(char direction, Board board, Player opponent) {
        int newRow = position.getRow();
        int newCol = position.getCol();

        switch (direction) {
            case 'W': newRow--; break;
            case 'S': newRow++; break;
            case 'A': newCol--; break;
            case 'D': newCol++; break;
            default:
                System.out.println("Invalid input! Use W, A, S, or D.");
                return;
        }

        // Prevent moving outside board boundaries
        if (newRow < 0 || newRow >= 9 || newCol < 0 || newCol >= 7) {
            System.out.println("Invalid move! Cannot move outside the board.");
            return;
        }

        Position newPosition = new Position(newRow, newCol);

        // Ensure Rat moves only one tile at a time
        if (Math.abs(newRow - position.getRow()) + Math.abs(newCol - position.getCol()) > 1) {
            System.out.println("Invalid move! Rat can only move one tile at a time.");
            return;
        }

        // If on land, check for an opponent to capture
        if (!board.isLake(newPosition)) {
            Piece opponentPiece = opponent.getPieceAt(newPosition);
            if (opponentPiece != null) {
                if (this.strength >= opponentPiece.strength || board.isTrap(newPosition)) {
                    System.out.println("Rat captures " + opponentPiece.getName() + "!");
                    opponentPiece.capture();
                    opponent.removePiece(opponentPiece);
                    position = newPosition; // Move Rat to capture position
                } else {
                    System.out.println("Invalid move! Rat cannot capture " + opponentPiece.getName() + " (stronger).");
                    return;
                }
            }
        }

        // Move if valid
        position = newPosition;
    }
}
