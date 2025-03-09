public class Cat extends Piece {
    public Cat(String name, int row, int col, String symbol, String owner) {
        super(name, row, col, symbol, owner);
    }

    @Override
    public boolean canMove(int newRow, int newCol, Board board) {
        if (newRow < 0 || newRow >= board.getRows() || newCol < 0 || newCol >= board.getCols()) {
            return false; // Out of bounds
        }
    
        if (board.isLake(newRow, newCol)) {
            return false; // Cats cannot enter lakes
        }
    
        // ✅ Allow moving into opponent's home base
        if (board.isHomeBase(newRow, newCol, this.getOwner().equals("Blue") ? "Green" : "Blue")) {
            return true;
        }
    
        if (board.isEmptyTile(newRow, newCol)) {
            return true;
        }
    
        if (board.isTrap(newRow, newCol)) {
            return true;
        }
    
        Piece opponent = board.getPieceAt(newRow, newCol);
        if (opponent != null && board.isOpponentPiece(this, newRow, newCol)) {
            return this.canCapture(opponent, board);
        }
    
        return false;
    }
    
    
}
