public class Player {
    private String name;
    private Piece dog;
    private Piece cat;

    public Player(String name, Piece dog, Piece cat) {
        this.name = name;
        this.dog = dog;
        this.cat = cat;
    }

    public String getName() {
        return name;
    }

    public Piece getPiece(String pieceType) {
        if (pieceType.equalsIgnoreCase("dog")) {
            return this.dog; 
        } else if (pieceType.equalsIgnoreCase("cat")) {
            return this.cat; 
        } else {
            return null;
        }
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
            System.out.println("Invalid direction! Use W, A, S, or D.");
            return;
        }
    
        if (piece.canMove(newRow, newCol, board)) {
            Piece opponent = board.getPieceAt(newRow, newCol);
            if (opponent != null && board.isOpponentPiece(piece, newRow, newCol)) {
                if (piece.canCapture(opponent, board)) {
                    System.out.println(piece.getName() + " captured " + opponent.getName() + "!");
                    board.removePiece(opponent);
                } else {
                    System.out.println("Capture not allowed. Try another move.");
                    return;
                }
            }
    
            board.updateBoard(piece, newRow, newCol);
    

            if (board.isHomeBase(newRow, newCol, piece.getOwner().equals("Blue") ? "Green" : "Blue")) {
                System.out.println(piece.getOwner() + " wins by capturing the opponent's home base!");
                System.exit(0); // End the game
            }
    
        } else {
            System.out.println("Invalid move. Try again.");
        }
    }
    
    
}
