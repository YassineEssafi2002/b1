import java.util.Random;
import java.util.Scanner;
import java.util.HashSet;

class Game {
    private static final int BOARD_SIZE = 10;
    private static final int NUM_SHIPS = 3;
    private int[][] board;
    private int shipsRemaining;
    private HashSet<String> guesses;



    public Game() {
        this.board = new int[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                this.board[i][j] = 0;
            }
        }


        Random random = new Random();
        int shipsPlaced = 0;
        while (shipsPlaced < NUM_SHIPS) {
            int x = random.nextInt(BOARD_SIZE);
            int y = random.nextInt(BOARD_SIZE);
            if (this.board[x][y] == 0) {
                this.board[x][y] = 1;
                shipsPlaced++;
            }
        }
        this.shipsRemaining = NUM_SHIPS;
        this.guesses = new HashSet<String>();
    }

    public void play() {

        Scanner scanner = new Scanner(System.in);
        while (this.shipsRemaining > 0) {
            displayBoard();
            System.out.print("Entrer un nombre entre 0 et 9 (x y): ");
            int x = scanner.nextInt();
            int y = scanner.nextInt();

            while (x < 0 || x >= BOARD_SIZE || y < 0 || y >= BOARD_SIZE || this.guesses.contains(x + " " + y)) {
                if (x < 0 || x >= BOARD_SIZE || y < 0 || y >= BOARD_SIZE) {
                    System.out.println(" Erreur. Veuillez entrer une coordonnée valide entre 0 et 9 " + (BOARD_SIZE - 1));
                }
                else {
                    System.out.println("Vous avez déjà deviné cette position. Veuillez entrer une nouvelle coordonnée.");
                }
                System.out.print("Entrer un nombre entre 0 et 9 (x y): ");
                x = scanner.nextInt();
                y = scanner.nextInt();
            }

            this.guesses.add(x + " " + y);

            if (this.board[x][y] == 1) {
                System.out.println("T as gagne felicitation!");
                this.board[x][y] = 2;
                this.shipsRemaining--;
            } else {
                System.out.println("Perdu!");
            }
        }

        System.out.println("Tous les navires ont été coulés ! Vous gagnez!");
    }



    public void displayBoard() {
        System.out.println("  0|1|2|3|4|5|6|7|8|9|");
        for (int row = 0; row < BOARD_SIZE; row++) {
            System.out.print(row + "|");
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (this.board[row][col] == 2) {
                    System.out.print("X");
                } else if (this.board[row][col] == 1) {
                    System.out.print("O");
                } else if (this.guesses.contains(row + " " + col)) {
                    System.out.print("-");
                } else {
                    System.out.print(" ");
                }
                if (col != BOARD_SIZE - 1) {
                    System.out.print("|");
            }
            }
            System.out.println("|" + row);
        }
        System.out.println("  0|1|2|3|4|5|6|7|8|9|");
    }

}

