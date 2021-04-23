package com.company;


import java.io.IOException;
import java.util.*;

public class TicTacToe {
    private final char X = 'X';
    private final char O = 'O';
    private final char horizontalSeparator = '|';
    private final char verticalSeparator = '-';
    private final char joint = '+';
    private String[][] board = new String[7][6];
    private char winner;
    private char turn = X;
    private int round = 1;
    private boolean computer = false;
    private Map<Integer, ArrayList> moveToIndexMap = new HashMap<Integer, ArrayList>();
    private HashSet<Integer> playedMovesSet = new HashSet<Integer>();


    public TicTacToe() {
        mapMovesToIndex();
    }

    /**
     * @param turn It takes which player should start first it takes a char X or O
     * @throws IllegalArgumentException if turn is not X or O
     */
    public TicTacToe(char turn) {
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
     */
    private void mapMovesToIndex() {
        /* maps numbers to indices*/
        int counter = 1;
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                ArrayList arr = new ArrayList();
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
     */
    private void printBoard() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                String item = this.board[i][j];
                if (this.board[i][j] == null) {
                    item = " ";
                }
                System.out.print(" " + item + " ");
                if (j != 5) {
                    System.out.print(horizontalSeparator);
                }
            }
            System.out.println();

            for (int j = 0; j < 5; j++) {
                System.out.print("" + verticalSeparator + verticalSeparator + verticalSeparator + joint);
                if (j == 4) {
                    System.out.print("" + verticalSeparator + verticalSeparator + verticalSeparator);
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
        if (this.playedMovesSet.contains(move)) {
            return false;
        }
        return true;
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
        this.playedMovesSet.add(move);
        ArrayList position = moveToIndexMap.get(move);
        int row = (int) position.get(0);
        int col = (int) position.get(1);
        this.board[row][col] = String.valueOf(this.turn);
        round++;
        return true;
    }

    /**
     * It alternates between X and O each turn
     * It doesn't change the turn if it is the first round
     */
    private void getTurn() {
//        if (round %2 ==0){
//            this.turn = O;
//            return;
//        }
//        this.turn =X;
//        return;

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
            for (int i = 0; i < 5; i++) {
                String row = null;
                String col =null;
                for (int j = 0; j < 4; j++) {
                    row += board[i][j];

//                    // Checks horizontal moves
//                    if (board[i][j] != null && board[i][j + 1] != null && board[i][j + 2] != null &&
//                            board[i][j].equals(board[i][j + 1]) && board[i][j + 1].equals(board[i][j + 2])) {
//                        System.out.println("Player " + board[i][j] + " has won!");
//                        return true;
//                    }
//                    // Checks vertical moves
//                    if (board[i][j] != null && board[i + 1][j] != null && board[i + 2][j] != null &&
//                            board[i][j].equals(board[i + 1][j]) && board[i + 1][j].equals(board[i + 2][j])) {
//                        System.out.println("Player " + board[i][j] + " has won!");
//                        return true;
//                    }
//                    // Checks diagonal moves
//                    if (board[i][j] != null && board[i + 1][j + 1] != null && board[i + 2][j + 2] != null &&
//                            board[i][j].equals(board[i + 1][j + 1]) && board[i + 1][j + 1].equals(board[i + 2][j + 2])) {
//                        System.out.println("Player " + board[i][j] + " has won!");
//                        return true;
//                    }
                }
                System.out.println(row.contains("XXX") || row.contains("OOO"));
            }
        }
        return false;
    }

    public void launch() {
        printBoard();
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter a number [1-42]:");
            int position = scanner.nextInt();
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
                break;
            }
        }
    }

    private void display() {

    }
}
