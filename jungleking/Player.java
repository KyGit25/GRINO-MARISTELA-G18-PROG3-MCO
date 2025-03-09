import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private List<Piece> pieces;

    public Player(String name) {
        this.name = name;
        this.pieces = new ArrayList<>();
    }

    public void addPiece(Piece piece) {
        pieces.add(piece);
    }

    public Piece getPiece(String pieceType) {
        for (Piece piece : pieces) {
            if (piece.getName().equalsIgnoreCase(pieceType) && !piece.isCaptured()) {
                return piece;
            }
        }
        System.out.println("\nPiece not found or has been captured! It cannot be moved.");
        return null;
    }

    public List<Piece> getAllPieces() {
        return pieces;
    }

    public List<Piece> getActivePieces() {
        List<Piece> activePieces = new ArrayList<>();
        for (Piece piece : pieces) {
            if (!piece.isCaptured()) {
                activePieces.add(piece);
            }
        }
        return activePieces;
    }

    public void movePiece(Piece piece, char direction, Board board) {
        int newRow = piece.getRow();
        int newCol = piece.getCol();
    
        if (direction == 'W') {
            newRow--;
        } else if (direction == 'S') {
            newRow++;
        } else if (direction == 'A') {
            newCol--;
        } else if (direction == 'D') {
            newCol++;
        } else {
            System.out.println("Invalid direction! Use W, A, S, or D.\n");
            return;
        }
    
        if (piece.canMove(newRow, newCol, board)) {
            Piece opponent = board.getPieceAt(newRow, newCol);
            if (opponent != null && board.isOpponentPiece(piece, newRow, newCol)) {
                if (piece.canCapture(opponent, board)) {
                    System.out.println(piece.getName() + " captured " + opponent.getName() + "!");
                    board.removePiece(opponent);
                } else {
                    System.out.println("Capture not allowed. Try another move.\n");
                    return;
                }
            }
            
            board.updateBoard(piece, newRow, newCol);

            if (board.isHomeBase(newRow, newCol, piece.getOwner().equals("Blue") ? "Green" : "Blue")) {
                System.out.println("\n" + piece.getOwner() + " has captured the opponent's home base!");
            }
        } else {
            System.out.println("Invalid move. Try again.\n");
        }
    }
    
    public boolean hasLostAllPieces() {
        for (Piece piece : pieces) {
            if (!piece.isCaptured()) {
                return false;
            }
        }
        return true;
    }

        public String getName() {
        return name;
    }
}
