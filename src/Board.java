import java.util.ArrayList;
import java.util.List;

/**
 * Represents the game board and handles movement, capturing, and terrain effects.
 */
public class Board {
    private static final int ROWS = 9;
    private static final int COLS = 7;
    private List<Trap> traps;
    private List<Lake> lakes;
    private HomeBase homeBase1;
    private HomeBase homeBase2;
    private Player player1;
    private Player player2;

    /**
     * Initializes the board, placing traps, lakes, and home bases.
     */
    public Board() {
        traps = new ArrayList<>();
        lakes = new ArrayList<>();
        initializeBoard();
    }

    /**
     * Sets up traps, lakes, and home bases in their fixed positions.
     */
    private void initializeBoard() {
        // Example Trap Positions
        traps.add(new Trap(2, 2));
        traps.add(new Trap(2, 4));
        traps.add(new Trap(6, 2));
        traps.add(new Trap(6, 4));

        // Example Lake Positions
        lakes.add(new Lake(3, 1));
        lakes.add(new Lake(3, 2));
        lakes.add(new Lake(4, 1));
        lakes.add(new Lake(4, 2));

        // Home Base Positions
        homeBase1 = new HomeBase(0, 3);
        homeBase2 = new HomeBase(8, 3);
    }

    /**
     * Checks if a position is a trap.
     * @param position The position to check.
     * @return True if it's a trap.
     */
    public boolean isTrap(Position position) {
        for (Trap trap : traps) {
            if (trap.isPieceOnTrap(position)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a position is a lake.
     * @param position The position to check.
     * @return True if it's a lake.
     */
    public boolean isLake(Position position) {
        for (Lake lake : lakes) {
            if (lake.isPieceInLake(position)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a position is a home base.
     * @param position The position to check.
     * @return 1 if player 1 wins, 2 if player 2 wins, 0 otherwise.
     */
    public int checkHomeBase(Position position) {
        if (homeBase1.isPieceAtHomeBase(position)) return 1;
        if (homeBase2.isPieceAtHomeBase(position)) return 2;
        return 0;
    }

    /**
     * Checks if a Rat is at a given position on the board.
     * @param position The position to check.
     * @return True if a Rat is at this position, otherwise false.
     */
    public boolean isRatAt(Position position) {
        for (Piece piece : player1.getPieces()) {
            if (piece instanceof Rat && piece.getPosition().equals(position)) {
                return true;
            }
        }
        for (Piece piece : player2.getPieces()) {
            if (piece instanceof Rat && piece.getPosition().equals(position)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Sets the players for reference in board operations.
     * @param p1 Player 1 instance.
     * @param p2 Player 2 instance.
     */
    public void setPlayers(Player p1, Player p2) {
        this.player1 = p1;
        this.player2 = p2;
    }
}
