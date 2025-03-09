import java.util.Collections;
import java.util.List;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Main game class that manages game flow, turns, and interactions.
 */
public class Game {
    private Board board;
    private Player player1, player2;
    private Player currentPlayer;
    private Scanner scanner;

    /**
     * Initializes the game with players and their pieces.
     */
    public Game() {
        board = new Board();
        scanner = new Scanner(System.in);
        initializePlayers();
    }

    /**
     * Initializes players and assigns their Lion and Rat pieces at correct positions.
     */
    private void initializePlayers() {
        // Player 1 (Top Side)
        Lion lion1 = new Lion(0, 3);
        Rat rat1 = new Rat(1, 3);
        player1 = new Player("Player 1 (White)", lion1, rat1);

        // Player 2 (Bottom Side)
        Lion lion2 = new Lion(8, 3);
        Rat rat2 = new Rat(7, 3);
        player2 = new Player("Player 2 (Black)", lion2, rat2);

        determineFirstPlayer();
    }

    /**
     * Determines which player moves first by allowing them to pick from shuffled, hidden pieces.
     */
    private void determineFirstPlayer() {
        System.out.println("\nShuffling pieces to determine first turn...");

        // Create a list of Lion and Rat to shuffle
        List<String> pieces = Arrays.asList("Lion", "Rat");
        Collections.shuffle(pieces); // Randomize their order

        // Display choices (players don't know which is which)
        System.out.println("Each player will now choose a hidden piece to determine who goes first.");
        System.out.println("Choose a piece (1 or 2):");

        // Player 1 picks first
        System.out.print("Player 1 (White), choose (1 or 2): ");
        int choice1 = scanner.nextInt();
        while (choice1 != 1 && choice1 != 2) {
            System.out.print("Invalid choice. Select 1 or 2: ");
            choice1 = scanner.nextInt();
        }
        String player1Pick = pieces.get(choice1 - 1);

        // Player 2 picks the remaining piece
        String player2Pick = (choice1 == 1) ? pieces.get(1) : pieces.get(0);

        // Display what they picked
        System.out.println("Player 1 (White) selected: " + player1Pick);
        System.out.println("Player 2 (Black) selected: " + player2Pick);

        // Determine who goes first based on strength
        int player1Strength = getStrength(player1Pick);
        int player2Strength = getStrength(player2Pick);

        while (player1Strength == player2Strength) { // If tie, shuffle again
            System.out.println("Tie! Re-shuffling...");
            Collections.shuffle(pieces);
            choice1 = (int) (Math.random() * 2) + 1;
            player1Pick = pieces.get(choice1 - 1);
            player2Pick = (choice1 == 1) ? pieces.get(1) : pieces.get(0);

            System.out.println("Player 1 (White) selected: " + player1Pick);
            System.out.println("Player 2 (Black) selected: " + player2Pick);

            player1Strength = getStrength(player1Pick);
            player2Strength = getStrength(player2Pick);
        }

        // Assign the first player
        if (player1Strength > player2Strength) {
            currentPlayer = player1;
        } else {
            currentPlayer = player2;
        }

        System.out.println(currentPlayer.getName() + " goes first!");
    }

    /**
     * Returns the strength value of a given animal.
     * @param animal The name of the selected animal piece.
     * @return Strength value (higher means stronger).
     */
    private int getStrength(String animal) {
        switch (animal) {
            case "Lion": return 7;
            case "Rat": return 1;
            default: return 0;
        }
    }

    /**
     * Starts the game loop, alternating player turns.
     */
    public void startGame() {
        while (true) {
            System.out.println("\n" + currentPlayer.getName() + "'s turn.");
            displayBoard();
            movePiece();

            // Check if game ends
            for (Piece piece : currentPlayer.getPieces()) {
                int result = board.checkHomeBase(piece.getPosition());
                if (result == 1) {
                    System.out.println("Player 1 wins!");
                    return;
                } else if (result == 2) {
                    System.out.println("Player 2 wins!");
                    return;
                }
            }

            switchTurn();
        }
    }

    /**
     * Handles player movement input.
     */
    private void movePiece() {
        System.out.println("Select a piece (Lion or Rat): ");
        String pieceChoice = scanner.next().toLowerCase();

        Piece selectedPiece = null;
        for (Piece piece : currentPlayer.getPieces()) {
            if (piece.getName().toLowerCase().equals(pieceChoice)) {
                selectedPiece = piece;
                break;
            }
        }

        if (selectedPiece == null) {
            System.out.println("Invalid piece selection! Choose either 'Lion' or 'Rat'.");
            return;
        }

        System.out.println("Enter direction (W = Up, S = Down, A = Left, D = Right): ");
        String input = scanner.next().toUpperCase();
        
        if (input.length() != 1 || !"WASD".contains(input)) {
            System.out.println("Invalid direction! Use W, A, S, or D.");
            return;
        }
        
        char direction = input.charAt(0);

        // Ensure correct opponent is passed for capturing logic
        Player opponent = (currentPlayer == player1) ? player2 : player1;
        selectedPiece.move(direction, board, opponent);
    }

    /**
     * Switches turn to the other player.
     */
    private void switchTurn() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    /**
     * Displays the current state of the board.
     */
    private void displayBoard() {
        System.out.println("\n[Game Board Display Here - Future Implementation]");
    }

    /**
     * Entry point for the program.
     * Starts the game menu.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.displayMenu();
    }

    /**
     * Displays the game menu.
     */
    public void displayMenu() {
        while (true) {
            System.out.println("\n=== Jungle King ===");
            System.out.println("1. Start New Game");
            System.out.println("2. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.next();
            if (choice.equals("1")) {
                startGame();
            } else if (choice.equals("2")) {
                System.out.println("Exiting game. Goodbye!");
                break;
            } else {
                System.out.println("Invalid choice. Please enter 1 or 2.");
            }
        }
    }
}
