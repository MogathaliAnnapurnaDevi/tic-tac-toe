import java.util.Scanner;

// Exception for invalid moves
class InvalidMoveException extends Exception {
    public InvalidMoveException(String message) {
        super(message);
    }
}

// Exception for trying to play after the game is over
class GameAlreadyOverException extends Exception {
    public GameAlreadyOverException(String message) {
        super(message);
    }
}

// Player class for holding player name and symbol
class Player {
    private String name;
    private char symbol; // 'X' or 'O'

    public Player(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public char getSymbol() {
        return symbol;
    }
}

// Board class to represent the Tic Tac Toe grid
class Board {
    private char[][] grid;

    public Board() {
        grid = new char[3][3];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                grid[i][j] = ' ';
    }

    public void displayBoard() {
        for (int i = 0; i < 3; i++) {
            System.out.printf("%c|%c|%c\n", grid[i][0], grid[i][1], grid[i][2]);
            if (i < 2) System.out.println("-----");
        }
    }

    public boolean isCellEmpty(int row, int col) {
        return grid[row][col] == ' ';
    }

    public void makeMove(int row, int col, char symbol) {
        grid[row][col] = symbol;
    }

    public boolean isFull() {
        for (char[] row : grid)
            for (char cell : row)
                if (cell == ' ') return false;
        return true;
    }

    public char[][] getGrid() {
        return grid;
    }
}

// Game service to manage the game logic
class GameService {
    private Board board;
    private Player currentPlayer;
    private Player player1;
    private Player player2;
    private boolean gameOver;

    public GameService(Player p1, Player p2) {
        this.board = new Board();
        this.player1 = p1;
        this.player2 = p2;
        this.currentPlayer = p1;
        this.gameOver = false;
    }

    public void playMove(int row, int col) throws InvalidMoveException, GameAlreadyOverException {
        if (gameOver) throw new GameAlreadyOverException("The game is already over.");

        if (row < 0 || row >= 3 || col < 0 || col >= 3 || !board.isCellEmpty(row, col))
            throw new InvalidMoveException("Invalid move! Cell is either occupied or out of bounds.");

        board.makeMove(row, col, currentPlayer.getSymbol());
        board.displayBoard();

        if (checkWinner(currentPlayer.getSymbol())) {
            System.out.println("Player " + currentPlayer.getName() + " wins!");
            gameOver = true;
        } else if (board.isFull()) {
            System.out.println("It's a draw!");
            gameOver = true;
        } else {
            switchPlayer();
        }
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    private boolean checkWinner(char symbol) {
        char[][] grid = board.getGrid();

        // Check rows, columns, and diagonals
        for (int i = 0; i < 3; i++)
            if ((grid[i][0] == symbol && grid[i][1] == symbol && grid[i][2] == symbol) ||
                (grid[0][i] == symbol && grid[1][i] == symbol && grid[2][i] == symbol))
                return true;

        return (grid[0][0] == symbol && grid[1][1] == symbol && grid[2][2] == symbol) ||
               (grid[0][2] == symbol && grid[1][1] == symbol && grid[2][0] == symbol);
    }
}

// Main class to run the Tic Tac Toe game
public class TicTacToeApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("--- Tic Tac Toe Game ---");
        System.out.print("Enter Player 1 Name: ");
        Player p1 = new Player(sc.nextLine(), 'X');

        System.out.print("Enter Player 2 Name: ");
        Player p2 = new Player(sc.nextLine(), 'O');

        GameService game = new GameService(p1, p2);

        while (!game.isGameOver()) {
            System.out.println();
            System.out.print(game.getCurrentPlayer().getName() + " (" + game.getCurrentPlayer().getSymbol() + "), enter your move (row[0-2] and column[0-2]): ");
            try {
                int row = sc.nextInt();
                int col = sc.nextInt();
                game.playMove(row, col);
            } catch (InvalidMoveException | GameAlreadyOverException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter integers between 0 and 2.");
                sc.nextLine(); // Clear invalid input
            }
        }

        sc.close();
    }
}