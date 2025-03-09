public class Lake {
    private int row;
    private int col;

    public Lake(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean isLakeTile(int r, int c) {
        return this.row == r && this.col == c;
    }
}
