package com.company;


import java.util.*;

public class TicTacToe {
    private final char X = 'X';
    private final char O = 'O';
    private String[][] board = new String[6][7];
    private Map<Integer, ArrayList<Integer>> moveToIndexMap = new HashMap<>();
    private HashSet<Integer> playedMovesSet = new HashSet<>();

    private char turn = X;
    private int round = 1;


    public TicTacToe() {
        mapMovesToIndex();
    }

    /**
     * @param turn It takes which player should start first it takes a char X or O
     * @throws IllegalArgumentException if turn is not X or O
     */
    public TicTacToe(char turn) {
        // Ensures valid input
        if (turn != 'X' && turn != 'O') {
            throw new IllegalArgumentException(" Turn takes either X or O");
        }
        this.turn = turn;
        mapMovesToIndex();
    }

    /**
     * Maps available moves to board indices
     * Example:
     * 1 to maps [0,0]
     * 2 maps to [0,1] ...etc
     * also it initializes the board
     */
    private void mapMovesToIndex() {
        /* maps numbers to indices*/
        int counter = 1;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                // initializing the board
                this.board[i][j] = String.valueOf(counter);
                ArrayList<Integer> arr = new ArrayList<>();
                arr.add(i);
                arr.add(j);
                moveToIndexMap.put(counter++, arr);
            }
        }
    }


    /**
     * Prints the Game board to the console
     * It loops over each element and separates it with horizontal separator
     * At the end of each line it separates it with vertical separator and Joint separator
     * If it is the first round it prints numbers to guide the user
     * When the first move is made it only prints the moves
     */
    private void printBoard() {
        if (round == 1) {
            System.out.println("Enter the number corresponding to the place you want to play: \n");
        }

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                // handles showing the numbers at round 1 and removes them afterwards
//                String item = board[i][j];
//                if (round == 1) {
//                    item = board[i][j];
//                } else if (!board[i][j].equals(X + "") && !board[i][j].equals(O + "")) {
////                    item = " ";
//                }
                System.out.print("\t" + board[i][j] + "\t");
                // Removes extra horizontal separator at the end
                if (j != 6) {
                    char horizontalSeparator = '|';
                    System.out.print(horizontalSeparator);
                }
            }
            System.out.println();

            for (int j = 0; j < 7; j++) {
                // fixes wrong vertical line at first col
                char verticalSeparator = '-';
                if (j == 0) {
                    System.out.print(verticalSeparator);
                }
                // prints vertical line
                for (int k = 0; k < 7; k++) {
                    System.out.print(verticalSeparator);
                }
                // removes extra joint
                if (j != 6) {
                    char joint = '+';
                    System.out.print(joint);
                }
            }
            System.out.println();
        }
    }

    /**
     * Takes a move and checks if this move is valid or not
     *
     * @param move Takes a move as an int
     */
    private boolean isValidMove(int move) {
        return !this.playedMovesSet.contains(move);
    }

    /**
     * Takes a move and play this move it if is valid.
     * It gets a new turn
     * It gets the index of the board from the move which is mapped by other function
     * Finally it adds round count to indicate a new turn
     * It returns true if it played the move else it returns false
     *
     * @param move Takes a move as int
     */
    private boolean play(int move) {
        if (!isValidMove(move)) {
            System.out.println("Invalid move");
            return false;
        }
        getTurn();
        // Adds the move to played moves
        this.playedMovesSet.add(move);
        // Gets corresponding index
        ArrayList<Integer> position = moveToIndexMap.get(move);
        int row = position.get(0);
        int col = position.get(1);
        // Inserts the move into the board
        this.board[row][col] = String.valueOf(this.turn);
        round++;
        return true;
    }

    /**
     * It alternates between X and O each turn
     * It doesn't change the turn if it is the first round
     */
    private void getTurn() {
        if (round == 1) return;
        if (this.turn == O) {
            this.turn = X;
            return;
        }
        this.turn = O;
    }


    /**
     * Returns true is there is a winner or tie else returns false
     * It checks if all rounds are played then it is a tie
     * It checks if any then
     */
    private boolean isWinner() {
        if (round == 42) {
            System.out.println("Tie");
            return true;
        }
        if (playedMovesSet.size() >= 5) {
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 7; j++) {
                    try {
                        // Checks horizontal moves
                        if (board[i][j].equals(board[i][j + 1]) && board[i][j + 1].equals(board[i][j + 2])) {
                            System.out.println("Player " + board[i][j] + " has won!");
                            return true;
                        }
                        // Checks vertical moves
                        if (board[i][j].equals(board[i + 1][j]) && board[i + 1][j].equals(board[i + 2][j])) {
                            System.out.println("Player " + board[i][j] + " has won!");
                            return true;
                        }
                        // Checks first diagonal moves
                        if (board[i][j].equals(board[i + 1][j + 1]) && board[i + 1][j + 1].equals(board[i + 2][j + 2])) {
                            System.out.println("Player " + board[i][j] + " has won!");
                            return true;
                        }
                        // Checks second diagonal moves
                        if (board[i][j].equals(board[i - 1][j + 1]) && board[i - 1][j + 1].equals(board[i - 2][j + 2])) {
                            System.out.println("Player " + board[i][j] + " has won!");
                            return true;
                        }
                    } catch (Exception IndexOutOfBound) {
                        // Handles If the index is out of bound
                    }
                }
            }
        }
        return false;
    }

    /**
     * Starts a new game by returning all the values to its initial state
     * It prints a menu to the user where he can choose between 1 and 2
     * If he enters 1 it starts a new game
     * If he enters 2 it stops the program
     */
    private void resetGame() {
        // Resets the game
        playedMovesSet.clear();
        this.round = 1;
        this.turn = X;
        int counter = 1;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                this.board[i][j] = String.valueOf(counter++);
            }
        }

        Scanner scanner = new Scanner(System.in);
        int input;
        // Keeps asking the using until he inputs a valid value
        while (true) {
            System.out.println("Choose an Option:\n 1-New Game\n 2-Exit");
            try {
                input = scanner.nextInt();
                // Exits if the input is 2
                if (input == 2) System.exit(0);
                // start a new game if the input is out
                if (input == 1) break;
                // Handles invalid input
                System.out.println("Invalid input!");
            } catch (Exception inputException) {
                // Handles invalid input type
                System.out.println("Invalid input!");
            }

        }
    }

    /**
     * It asks the user for a move and returns it if it is valid
     */
    private int playerTurn() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a valid move [1-42]: (" + turn + " turn)");
        try {
            return scanner.nextInt();
        } catch (Exception inputException) {
            // Returns a number out of range so the user gets an error
            return 100;
        }
    }


    /**
     * Main Logic of the game
     * It keeps the game working and takes input from the user and checks if its a valid move
     * If its a valid move it plays it
     * If there is a winner the game will stop
     */
    public void launch() {
        printBoard();

        while (true) {
            int position = playerTurn();

            if (position < 1 || position > 42) {
                System.out.println("Invalid move");
                continue;
            }
            boolean play = play(position);
            // It prevents printing the board again in case of error
            if (!play) {
                continue;
            }

            printBoard();
            if (isWinner()) {
                resetGame();
                printBoard();
            }
        }
    }

}
