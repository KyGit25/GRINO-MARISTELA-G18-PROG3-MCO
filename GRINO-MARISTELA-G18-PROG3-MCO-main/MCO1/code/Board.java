import java.util.ArrayList;
import java.util.List;


/**
 * The Board class represents the game board for a strategy game. It contains
 * a grid of tiles where pieces, traps, lakes, and home bases are placed.
 * */
public class Board 
{
    private String[][] grid;
    private static final int ROWS = 7;
    private static final int COLS = 9;
    private List<Piece> pieces;
    private List<Trap> traps;
    private HomeBase blueHome;
    private HomeBase greenHome;

 /**
     * Constructs a new Board object and initializes the game board.
     * 
     * Pre-condition:
     * - The board size is fixed at 7 rows by 9 columns.
     * - The grid is initially empty before setting special tiles.
     * 
     * Post-condition:
     * - The grid is initialized with empty tiles.
     * - Lakes, traps, and home bases are placed on the board.
     * - Lists for pieces and traps are created.
     */
    public Board() 
    {
        grid = new String[ROWS][COLS];
        pieces = new ArrayList<>();
        traps = new ArrayList<>();
        initializeBoard();
    }

    /**
     * Initializes the board by setting up lakes, traps, and home bases.
     * 
     * Pre-condition:
     * - The board grid is created but not yet populated.
     * - No special elements (lakes, traps, home bases) are placed.
     * 
     * Post-condition:
     * - All board tiles are set to empty ("  ").
     * - Lakes (" ~") are placed at predefined positions.
     * - Traps (" ?" for Blue, " !" for Green) are placed at predefined positions.
     * - Home bases (" #" for Blue, " $" for Green) are set at predefined locations.
     */
    private void initializeBoard() 
    {
        // Fill the board with empty spaces
        for (int i = 0; i < ROWS; i++) 
        {
            for (int j = 0; j < COLS; j++) 
            {
                grid[i][j] = "  ";
            }
        }

        // Place lakes (~) at predefined positions
        int[][] lakePositions = 
        {
            {1,3}, {1,4}, {1,5}, {2,3}, {2,4}, {2,5},
            {4,3}, {4,4}, {4,5}, {5,3}, {5,4}, {5,5}
        };

        for (int[] pos : lakePositions) 
        {
            grid[pos[0]][pos[1]] = " ~";
        }

        // // Define and place traps (" ?")
        int[][] blueTraps = {{2,0}, {4,0}, {3,1}};
        int[][] greenTraps = {{2,8}, {4,8}, {3,7}};

        for (int[] pos : blueTraps) 
        {
            traps.add(new Trap(pos[0], pos[1], "Blue"));
            grid[pos[0]][pos[1]] = " ?";
        }
        
        for (int[] pos : greenTraps) 
        {
            traps.add(new Trap(pos[0], pos[1], "Green"));
            grid[pos[0]][pos[1]] = " !";
        }

        // Create and place home base (" #")
        blueHome = new HomeBase(3, 0, "Blue");
        greenHome = new HomeBase(3, 8, "Green");

        grid[blueHome.getRow()][blueHome.getCol()] = " #";
        grid[greenHome.getRow()][greenHome.getCol()] = " $";
    }

    /**
     * Places a piece on the board at its specified position.
     * 
     * Pre-condition:
     * - The piece object has a valid row and column within board bounds.
     * - The target position is either empty or contains a removable piece.
     * 
     * Post-condition:
     * - The piece is added to the list of active pieces.
     * - The piece's symbol is displayed on the board at its position.
     * 
     * @param piece The piece to be placed on the board.
     */
    public void placePiece(Piece piece) 
    {
        // Add piece to active piece list and update board
        pieces.add(piece);
        grid[piece.getRow()][piece.getCol()] = piece.getSymbol();
    }

    /**
     * Retrieves the piece located at a specific board position.
     * 
     * Pre-condition:
     * - The row and column values are within the board's valid range.
     * 
     * Post-condition:
     * - Returns the piece at the given position if one exists.
     * - Returns null if no piece is found at the specified position.
     * 
     * @param row The row index of the board.
     * @param col The column index of the board.
     * @return The piece at the given position, or null if no piece is present.
     */

    public Piece getPieceAt(int row, int col) 
    {
        for (Piece piece : pieces) 
        {
            if (piece.getRow() == row && piece.getCol() == col) 
            {
                return piece; // Found the piece at the specified position
            }
        }

        return null; // No piece found at the given location
    }
    

        /**
     * Updates the board when a piece moves to a new position.
     * Handles trap interactions and restores previous positions appropriately.
     * 
     * Pre-condition:
     * - The piece is currently placed on the board.
     * - The new position is within board bounds.
     * 
     * Post-condition:
     * - The piece's old position is cleared or restored if it contained a trap.
     * - If the new position contains an opponent's trap, the piece is marked as trapped.
     * - The piece is moved to the new position and displayed on the board.
     * 
     * @param piece  The piece being moved.
     * @param newRow The row index of the new position.
     * @param newCol The column index of the new position.
     */

    public void updateBoard(Piece piece, int newRow, int newCol) 
    {
        int oldRow = piece.getRow();
        int oldCol = piece.getCol();
        
        // Restore trap symbol if the old position contained a trap
        if (isTrap(oldRow, oldCol)) 
        {
            String trapOwner = getTrapOwner(oldRow, oldCol);
            if (trapOwner.equals("Blue")) 
            {
                grid[oldRow][oldCol] = " ?"; // Restore Blue trap
            } 
            else if (trapOwner.equals("Green")) 
            {
                grid[oldRow][oldCol] = " !"; // Restore Green trap
            }

            piece.setTrapped(false); // Reset trapped status
        } 
        else 
        {
            grid[oldRow][oldCol] = "  "; // Clear old position if not a trap
        }
        
        // Check if the new position is a trap and handle trapping logic
        if (isTrap(newRow, newCol)) 
        {
            String trapOwner = getTrapOwner(newRow, newCol);
            if (!trapOwner.equals(piece.getOwner())) 
            {
                // Piece falls into an enemy trap
                piece.setTrapped(true);
                System.out.println(piece.getName() + " got trapped in a " + trapOwner + " trap!");
            }
        }
        
        // Move the piece to the new position and update board display
        piece.move(newRow, newCol);
        grid[newRow][newCol] = piece.getSymbol();
    }
    
    /**
     * Checks if a given board position contains a lake.
     * 
     * Pre-condition:
     * - The row and column values are within board bounds.
     * 
     * Post-condition:
     * - Returns true if the position contains a lake (" ~"), otherwise returns false.
     * 
     * @param row The row index of the board.
     * @param col The column index of the board.
     * @return True if the position is a lake, false otherwise.
     */
    public boolean isLake(int row, int col) 
    {
        // Check if the position has a lake symbol
        return grid[row][col].equals(" ~");
    }

    /**
     * Checks if a given board position contains a trap.
     * 
     * Pre-condition:
     * - The row and column values are within board bounds.
     * 
     * Post-condition:
     * - Returns true if the position contains a trap, otherwise returns false.
     * 
     * @param row The row index of the board.
     * @param col The column index of the board.
     * @return True if the position contains a trap, false otherwise.
     */
    public boolean isTrap(int row, int col) 
    {
        for (Trap trap : traps) 
        {
            if (trap.getRow() == row && trap.getCol() == col) 
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Retrieves the owner of a trap at a given board position.
     * 
     * Pre-condition:
     * - The row and column values are within board bounds.
     * - The position should contain a trap.
     * 
     * Post-condition:
     * - Returns the owner of the trap if one exists at the given position.
     * - Returns null if no trap is found at the specified position.
     * 
     * @param row The row index of the board.
     * @param col The column index of the board.
     * @return The owner of the trap ("Blue" or "Green"), or null if no trap is present.
     */
    public String getTrapOwner(int row, int col) {
        for (Trap trap : traps) 
        {
            if (trap.getRow() == row && trap.getCol() == col) {
                return trap.getOwner();
            }
        }
        return null;
    }

    /**
     * Checks if a given board position is empty.
     * 
     * Pre-condition:
     * - The row and column values are within board bounds.
     * 
     * Post-condition:
     * - Returns true if the position contains an empty space ("  "), otherwise returns false.
     * 
     * @param row The row index of the board.
     * @param col The column index of the board.
     * @return True if the position is empty, false otherwise.
     */
    public boolean isEmptyTile(int row, int col) 
    {

        return grid[row][col].equals("  ");
    }

    /**
     * Checks if a given board position contains an opponent's piece.
     * 
     * Pre-condition:
     * - The row and column values are within board bounds.
     * - The piece parameter is a valid, existing piece.
     * 
     * Post-condition:
     * - Returns true if the position contains a piece owned by the opponent.
     * - Returns false if the position is empty or contains a friendly piece.
     * 
     * @param piece The piece that is checking for an opponent.
     * @param row The row index of the board.
     * @param col The column index of the board.
     * @return True if the position contains an opponent's piece, false otherwise.
     */
    public boolean isOpponentPiece(Piece piece, int row, int col) 
    {
        Piece opponent = getPieceAt(row, col);
        if (opponent == null) 
        {
            return false;
        }
        return !opponent.getOwner().equals(piece.getOwner());
    }

    /**
     * Removes a piece from the board and marks it as captured.
     * 
     * Pre-condition:
     * - The opponent piece exists on the board.
     * - The row and column values of the piece are within board bounds.
     * 
     * Post-condition:
     * - The piece is removed from the list of active pieces.
     * - The board position is cleared ("  ").
     * - The piece is marked as captured.
     * 
     * @param opponent The piece to be removed from the board.
     */
    public void removePiece(Piece opponent) 
    {
        grid[opponent.getRow()][opponent.getCol()] = "  ";
        opponent.setCaptured(true);
        pieces.remove(opponent);
    }

    /**
     * Checks if a given board position is a player's home base.
     * 
     * Pre-condition:
     * - The row and column values are within board bounds.
     * - The player parameter is either "Blue" or "Green".
     * 
     * Post-condition:
     * - Returns true if the given position matches the home base of the specified player.
     * - Returns false if the position does not match the player's home base.
     * 
     * @param row The row index of the board.
     * @param col The column index of the board.
     * @param player The player's name ("Blue" or "Green").
     * @return True if the position is the specified player's home base, false otherwise.
     */
    public boolean isHomeBase(int row, int col, String player) 
    {
        if (player.equals("Blue")) 
        {
            return row == blueHome.getRow() && col == blueHome.getCol();
        } else if (player.equals("Green")) {
            return row == greenHome.getRow() && col == greenHome.getCol();
        }
        return false;
    }

    /**
     * Displays the current state of the board, including pieces, traps, lakes, and home bases.
     * 
     * Pre-condition:
     * - The board has been initialized with pieces and special tiles.
     * 
     * Post-condition:
     * - Prints the board grid with row and column separators.
     * - The board layout is visually structured for easy understanding.
     */
    public void displayBoard() 
    {
        System.out.println("\n   +-----------------------------------+");
        for (int i = 0; i < ROWS; i++) 
        {
            System.out.print("   |");
            for (int j = 0; j < COLS; j++) 
            {
                System.out.print(grid[i][j] + " |");
            }
            System.out.println();
            System.out.println("   +-----------------------------------+");
        }
    }

    /**
     * Retrieves the total number of rows on the board.
     * 
     * Pre-condition:
     * - The board has been initialized.
     * 
     * Post-condition:
     * - Returns the fixed number of rows (7).
     * 
     * @return The number of rows on the board.
     */
    public int getRows() 
    {
        return ROWS;
    }

    /**
     * Retrieves the total number of columns on the board.
     * 
     * Pre-condition:
     * - The board has been initialized.
     * 
     * Post-condition:
     * - Returns the fixed number of columns (9).
     * 
     * @return The number of columns on the board.
     */
    public int getCols() 
    {
        return COLS;
    }
}