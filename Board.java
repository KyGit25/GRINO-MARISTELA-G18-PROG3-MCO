import java.util.ArrayList;
import java.util.List;

public class Board {
    private String[][] grid;
    private static final int ROWS = 7;
    private static final int COLS = 9;
    private List<Piece> pieces;
    private List<Trap> traps;
    private HomeBase blueHome;
    private HomeBase greenHome;

    public Board() {
        grid = new String[ROWS][COLS];
        pieces = new ArrayList<>();
        traps = new ArrayList<>();
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                grid[i][j] = "  ";
            }
        }

        // Place lakes (~)
        int[][] lakePositions = {
            {1,3}, {1,4}, {1,5}, {2,3}, {2,4}, {2,5},
            {4,3}, {4,4}, {4,5}, {5,3}, {5,4}, {5,5}
        };
        for (int[] pos : lakePositions) {
            grid[pos[0]][pos[1]] = " ~";
        }

        // Place traps
        int[][] blueTraps = {{2,0}, {4,0}, {3,1}};
        int[][] greenTraps = {{2,8}, {4,8}, {3,7}};

        for (int[] pos : blueTraps) {
            traps.add(new Trap(pos[0], pos[1], "Blue"));
            grid[pos[0]][pos[1]] = " ?";
        }
        for (int[] pos : greenTraps) {
            traps.add(new Trap(pos[0], pos[1], "Green"));
            grid[pos[0]][pos[1]] = " !";
        }

        // Place home bases
        blueHome = new HomeBase(3, 0, "Blue");
        greenHome = new HomeBase(3, 8, "Green");
        grid[blueHome.getRow()][blueHome.getCol()] = " #";
        grid[greenHome.getRow()][greenHome.getCol()] = " $";
    }

    public void placePiece(Piece piece) {
        pieces.add(piece);
        grid[piece.getRow()][piece.getCol()] = piece.getSymbol();
    }

    public Piece getPieceAt(int row, int col) {
        for (Piece piece : pieces) {
            if (piece.getRow() == row && piece.getCol() == col) {
                return piece;
            }
        }
        return null;
    }
    
    public void updateBoard(Piece piece, int newRow, int newCol) {
        int oldRow = piece.getRow();
        int oldCol = piece.getCol();
        
        // Restore trap
        if (isTrap(oldRow, oldCol)) {
            String trapOwner = getTrapOwner(oldRow, oldCol);
            if (trapOwner.equals("Blue")) {
                grid[oldRow][oldCol] = " ?";
            } else if (trapOwner.equals("Green")) {
                grid[oldRow][oldCol] = " !";
            }
            // Restore piece status
            piece.setTrapped(false);
        } else {
            grid[oldRow][oldCol] = "  "; // Clear old position if not a trap
        }
        
        // Piece is trapped
        if (isTrap(newRow, newCol)) {
            String trapOwner = getTrapOwner(newRow, newCol);
            if (!trapOwner.equals(piece.getOwner())) {
                piece.setTrapped(true);
                System.out.println(piece.getName() + " got trapped in a " + trapOwner + " trap!");
            }
        }
        
        piece.move(newRow, newCol);
        grid[newRow][newCol] = piece.getSymbol();
    }
    

    public boolean isLake(int row, int col) {
        return grid[row][col].equals(" ~");
    }

    public boolean isTrap(int row, int col) {
        for (Trap trap : traps) {
            if (trap.getRow() == row && trap.getCol() == col) {
                return true;
            }
        }
        return false;
    }

    public String getTrapOwner(int row, int col) {
        for (Trap trap : traps) {
            if (trap.getRow() == row && trap.getCol() == col) {
                return trap.getOwner();
            }
        }
        return null;
    }

    public boolean isEmptyTile(int row, int col) {
        return grid[row][col].equals("  ");
    }

    public boolean isOpponentPiece(Piece piece, int row, int col) {
        Piece opponent = getPieceAt(row, col);
        if (opponent == null) {
            return false;
        }
        return !opponent.getOwner().equals(piece.getOwner());
    }

    public void removePiece(Piece opponent) {
        grid[opponent.getRow()][opponent.getCol()] = "  ";
        opponent.setCaptured(true);
        pieces.remove(opponent);
    }

    public boolean isHomeBase(int row, int col, String player) {
        if (player.equals("Blue")) {
            return row == blueHome.getRow() && col == blueHome.getCol();
        } else if (player.equals("Green")) {
            return row == greenHome.getRow() && col == greenHome.getCol();
        }
        return false;
    }

    public void displayBoard() {
        System.out.println("\n   +-----------------------------------+");
        for (int i = 0; i < ROWS; i++) {
            System.out.print("   |");
            for (int j = 0; j < COLS; j++) {
                System.out.print(grid[i][j] + " |");
            }
            System.out.println();
            System.out.println("   +-----------------------------------+");
        }
    }

    public int getRows() {
        return ROWS;
    }

    public int getCols() {
        return COLS;
    }
}
