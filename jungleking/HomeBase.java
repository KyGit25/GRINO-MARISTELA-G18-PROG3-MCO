public class HomeBase {
    private int row;
    private int col;
    private String owner;

    public HomeBase(int row, int col, String owner) {
        this.row = row;
        this.col = col;
        this.owner = owner;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public String getOwner() {
        return owner;
    }
}
