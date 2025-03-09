public abstract class Piece {
    protected String name;
    protected int row;
    protected int col;
    protected String symbol;
    protected boolean isTrapped;
    protected String owner; // "Blue" or "Green"

    public Piece(String name, int row, int col, String symbol, String owner) {
        this.name = name;
        this.row = row;
        this.col = col;
        this.symbol = symbol;
        this.owner = owner;
        this.isTrapped = false;
    }

    public abstract boolean canMove(int newRow, int newCol, Board board);

    public void move(int newRow, int newCol) {
        this.row = newRow;
        this.col = newCol;
    }

    public boolean canCapture(Piece opponent, Board board) {
        if (opponent == null) {
            return false;
        }
    
        if (this.owner.equals(opponent.owner)) {
            return false; // Cannot capture own pieces
        }
    
        if (this instanceof Dog) {
            return true; // Dogs can capture all opponent pieces
        }
    
        if (this instanceof Cat && opponent instanceof Cat) {
            return true; // Cats can capture opponent's Cats
        }
    
        if (this instanceof Cat && opponent instanceof Dog) {
            if (board.isTrap(opponent.getRow(), opponent.getCol())) {
                String trapOwner = board.getTrapOwner(opponent.getRow(), opponent.getCol());
                if (trapOwner != null && trapOwner.equals(this.owner)) {
                    return true; // Cats can capture Dogs only when trapped
                }
            }
        }
    
        return false;
    }
    

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean isTrapped() {
        return isTrapped;
    }

    public void setTrapped(boolean trapped) {
        this.isTrapped = trapped;
    }

    public String getOwner() {
        return owner;
    }
}
