public class Trap {
    private int row;
    private int col;
    private boolean isOccupied;
    private String owner;

    public Trap(int row, int col, String owner) {
        this.row = row;
        this.col = col;
        this.isOccupied = false;
        this.owner = owner;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        this.isOccupied = occupied;
    }

    public String getOwner() {
        return owner;
    }
}
