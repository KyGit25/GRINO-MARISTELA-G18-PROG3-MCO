/**
 * The Main class serves as the entry point of the game.
 * It initializes and starts the game.
 */
public class Main {
    
    /**
     * The main method that runs the game.
     * 
     * @param args Command-line arguments (not used in this program).
     * 
     * Pre-condition: The Game class must be properly implemented.
     * Post-condition: A new Game object is created and initialized.
     */
    public static void main(String[] args) {
        // Create a new game instance
        Game game = new Game();
        
        // Initialize the game
        game.initializeGame();
    }
}
