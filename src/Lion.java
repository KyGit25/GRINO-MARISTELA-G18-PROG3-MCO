/**
 * Represents the Lion piece with special lake-jumping ability.
 */
public class Lion extends Piece {
    /**
     * Creates a Lion piece.
     * @param row The starting row position.
     * @param col The starting column position.
     */
    public Lion(int row, int col) {
        super("Lion", row, col, 7);
    }

    /**
     * Moves the Lion, allowing lake leaping while following capture rules.
     * @param direction The movement direction (W, A, S, D).
     * @param board The game board.
     * @param opponent The opposing player.
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

        // Check if the Lion is moving into a lake
        if (board.isLake(newPosition)) {
            if (!isRatBlocking(board, direction)) {
                // Leap over the lake (horizontally or vertically)
                while (board.isLake(newPosition)) {
                    newRow += (direction == 'W') ? -1 : (direction == 'S') ? 1 : 0;
                    newCol += (direction == 'A') ? -1 : (direction == 'D') ? 1 : 0;

                    if (newRow < 0 || newRow >= 9 || newCol < 0 || newCol >= 7) {
                        System.out.println("Invalid leap! Cannot land outside the board.");
                        return;
                    }

                    newPosition = new Position(newRow, newCol);
                }
            } else {
                System.out.println("Lion cannot jump over the lake because a Rat is in the way!");
                return;
            }
        }

        // Check if the landing position is occupied
        Piece opponentPiece = opponent.getPieceAt(newPosition);
        if (opponentPiece != null) {
            if (this.strength >= opponentPiece.strength || board.isTrap(newPosition)) {
                System.out.println("Lion captures " + opponentPiece.getName() + "!");
                opponentPiece.capture();
                opponent.removePiece(opponentPiece);
                position = newPosition; // Move Lion to capture position
            } else {
                System.out.println("Invalid move! Lion cannot capture " + opponentPiece.getName() + " (stronger).");
                return;
            }
        } else {
            position = newPosition; // Move normally if empty
        }
    }

    /**
     * Checks if a Rat is blocking the Lion's lake leap.
     * @param board The game board.
     * @param direction The direction of movement.
     * @return True if a Rat is in the way.
     */
    private boolean isRatBlocking(Board board, char direction) {
        int checkRow = position.getRow();
        int checkCol = position.getCol();

        while (board.isLake(new Position(checkRow, checkCol))) {
            checkRow += (direction == 'W') ? -1 : (direction == 'S') ? 1 : 0;
            checkCol += (direction == 'A') ? -1 : (direction == 'D') ? 1 : 0;

            if (board.isRatAt(new Position(checkRow, checkCol))) {
                return true;
            }
        }
        return false;
    }
}
