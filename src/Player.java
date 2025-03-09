import java.util.ArrayList;
import java.util.List;

/**
 * Represents a player controlling the Lion and Rat pieces.
 */
public class Player {
    private String name;
    private List<Piece> pieces;

    /**
     * Creates a player with a Lion and Rat.
     * @param name The player's name.
     * @param lion The Lion piece assigned to the player.
     * @param rat The Rat piece assigned to the player.
     */
    public Player(String name, Lion lion, Rat rat) {
        this.name = name;
        pieces = new ArrayList<>();
        pieces.add(lion);
        pieces.add(rat);
    }

    /**
     * Gets the player's name.
     * @return The player's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the list of pieces controlled by the player.
     * @return A list of the player's pieces.
     */
    public List<Piece> getPieces() {
        return pieces;
    }

    /**
     * Removes a captured piece from the player's collection.
     * @param capturedPiece The piece to remove.
     */
    public void removePiece(Piece capturedPiece) {
        pieces.remove(capturedPiece);
    }

    /**
     * Checks if the player still has any remaining pieces.
     * @return True if the player has at least one piece left.
     */
    public boolean hasPiecesLeft() {
        return !pieces.isEmpty();
    }

    /**
     * Gets the piece at a given position, if it exists.
     * @param position The position to check.
     * @return The piece at the position or null if empty.
     */
    public Piece getPieceAt(Position position) {
        for (Piece piece : pieces) {
            if (piece.getPosition().equals(position)) {
                return piece;
            }
        }
        return null;
    }
}
