public abstract class Piece {
    protected String name;
    protected String symbol;
    protected String owner;
    protected int row;
    protected int col;
    protected boolean isTrapped, inLake;
    protected boolean isCaptured;
    protected int strength;

    public Piece(String name, int row, int col, String symbol, String owner, int strength) {
        this.name = name;
        this.row = row;
        this.col = col;
        this.symbol = symbol;
        this.owner = owner;
        this.isTrapped = false;
        this.inLake = false;
        this.isCaptured = false;
        this.strength = strength;
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
            System.out.println("Cannot capture own piece.\n");
            return false; // Cannot capture own pieces
        }
        
        // Check if opponent is in a trap
        if (board.isTrap(opponent.getRow(), opponent.getCol())) {
            String trapOwner = board.getTrapOwner(opponent.getRow(), opponent.getCol());
            if (trapOwner != null && trapOwner.equals(this.owner)) {
                return true; // capture opponent in owned trap
            }
        }
        
        // Lake rules
        if (this.inLake && !opponent.inLake) {
            return false; // lake piece CANT capture land piece
        }
        
        if (!this.inLake && opponent.inLake) {
            return false; // land piece CANT capture lake piece
        }
        
        return this.strength >= opponent.strength;
    }

    public String getOwner() {
        return owner;
    }
    
    public int getStrength() {
        return strength;
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
    
    public boolean isInLake() {
        return inLake;
    }
    
    public boolean isCaptured() {
        return isCaptured;
    }
    
    public void setTrapped(boolean trapped) {
        this.isTrapped = trapped;
    }

    public void setInLake(boolean inLake) {
        this.inLake = inLake;
    }
    
    public void setCaptured(boolean captured) {
        this.isCaptured = captured;
    }
}