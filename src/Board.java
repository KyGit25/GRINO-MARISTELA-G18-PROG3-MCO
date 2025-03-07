/**
 * Game board
 * @author Gem Griño
 * @version 1.0
 */
 
import java.util.ArrayList;

public class Board{
    private Tile[][] grid;
    private ArrayList<Animal> animals;
    private static final int ROWS = 9, COLS = 7;
    
    public Board(){
        grid = new Tile[ROWS][COLS];
        animals = new ArrayList<Animal>();
        initializeBoard();
    }

    private void initializeBoard() {
        int i, j;

        // generate tiles

        // generate animals
    }

    private void printBoard(){
        int i, j;
        for(i = 0; i < ROWS; i++){
            for(j = 0; j < COLUMNS; j++){
               // print out initialized board
            }
        }
    }

}