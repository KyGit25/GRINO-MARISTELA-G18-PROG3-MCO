import java.util.Random;
import java.util.Scanner;

public class Game {
    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Scanner scanner;

    public Game() {
        board = new Board();
        scanner = new Scanner(System.in);
    }

    public void initializeGame() {
        System.out.println("=== Jungle King ===");
        System.out.println("1. Start Game");
        System.out.println("2. Exit Program");
        System.out.print("Choose an option: ");

        int choice = scanner.nextInt();
        if (choice != 1) {
            System.out.println("Exiting game...");
            return;
        }

        shuffleAndAssignPieces();
        playGame();
    }

    private void shuffleAndAssignPieces() {
        Random rand = new Random();
        boolean dogFirst = rand.nextBoolean(); // Randomly assign Dog to 1 or 2
    
        int dogNumber;
        int catNumber;
    
        if (dogFirst) {
            dogNumber = 1;
            catNumber = 2;
        } else {
            dogNumber = 2;
            catNumber = 1;
        }
    
        System.out.println("\nShuffling pieces...");
        System.out.println("Two hidden numbers (1 or 2) contain a Dog or a Cat.");
        
        int player1Choice = 0;
        while (player1Choice != 1 && player1Choice != 2) {
            System.out.print("Player 1, pick a number (1 or 2): ");
            player1Choice = scanner.nextInt();
            if (player1Choice != 1 && player1Choice != 2) {
                System.out.println("Invalid choice. Choose 1 or 2.");
            }
        }
    
        int player2Choice = 0;
        if (player1Choice == 1) {
            player2Choice = 2;
        } else {
            player2Choice = 1;
        }
    
        System.out.println("Player 2, your number is: " + player2Choice);
    
        // Initialize the players
        Piece p1Dog = new Dog("Dog", 5, 1, "BD", "Blue");
        Piece p1Cat = new Cat("Cat", 1, 1, "BC", "Blue");
        Piece p2Dog = new Dog("Dog", 1, 7, "GD", "Green");
        Piece p2Cat = new Cat("Cat", 5, 7, "GC", "Green");
    
        player1 = new Player("Player 1 (Blue)", p1Dog, p1Cat);
        player2 = new Player("Player 2 (Green)", p2Dog, p2Cat);
    
        // Reveal the stronger animal & set `currentPlayer`
        System.out.println("\nRevealing hidden animals...");
        if (player1Choice == dogNumber) {
            System.out.println("Player 1 selected: Dog");
            System.out.println("Player 2 selected: Cat");
            currentPlayer = player1;
        } else {
            System.out.println("Player 1 selected: Cat");
            System.out.println("Player 2 selected: Dog");
            currentPlayer = player2;
        }
    
        System.out.println("\n" + currentPlayer.getName() + " goes first!\n");
    
        // Place the initialized pieces on the board
        board.placePiece(p1Dog);
        board.placePiece(p1Cat);
        board.placePiece(p2Dog);
        board.placePiece(p2Cat);
    }
    
    private void playGame() {
        while (true) {
            board.displayBoard();
            System.out.println(currentPlayer.getName() + "'s Turn");

            Piece selectedPiece = selectPiece();
            char direction = getDirectionInput();

            currentPlayer.movePiece(selectedPiece, direction, board);

            if (checkWinCondition(selectedPiece)) {
                System.out.println(currentPlayer.getName() + " wins the game!");
                break;
            }

            switchTurn();
        }

        System.out.println("Game Over!");
    }

    private Piece selectPiece() {
        Piece selectedPiece = null;
        while (selectedPiece == null) {
            System.out.print("Select a piece to move (Dog/Cat): ");
            String pieceType = scanner.next();
            selectedPiece = currentPlayer.getPiece(pieceType);
            if (selectedPiece == null) {
                System.out.println("Invalid selection. Try again.");
            }
        }
        return selectedPiece;
    }

    private char getDirectionInput() {
        char direction;
        while (true) {
            System.out.print("Enter direction (W = Up, S = Down, A = Left, D = Right): ");
            direction = scanner.next().toUpperCase().charAt(0);
            if (direction == 'W' || direction == 'S' || direction == 'A' || direction == 'D') {
                break;
            }
            System.out.println("Invalid direction. Use W, A, S, or D.");
        }
        return direction;
    }

    private boolean checkWinCondition(Piece piece) {
        if (piece.getOwner().equals("Blue") && board.isHomeBase(piece.getRow(), piece.getCol(), "Green")) {
            return true; // Blue wins by capturing Green's home base
        }
        if (piece.getOwner().equals("Green") && board.isHomeBase(piece.getRow(), piece.getCol(), "Blue")) {
            return true; // Green wins by capturing Blue's home base
        }
        return false;
    }
    

    private void switchTurn() {
        if (currentPlayer == player1) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }
    }
}
