/**
 * Animal pieces
 * @author Gem Griño
 * @version 1.0
 */
 
import java.util.ArrayList;

public class Animal{
    protected String name;
    protected char symbol;
    protected int strength;
    protected int row, col;
    protected boolean isTrapped = false, inLake = false;

    public boolean isValidMove(){
        // t
    }
    public boolean canCapture(){
        return this.strength >= target.strength;
    }

    public boolean isTrapped(){
        // t
    }
    
    public boolean inLake(){
        // t
    }

    public String getName(){
        return this.name;
    }

    public char getSymbol(){
        return this.symbol;
    }
}