# Tic-Tac-Toe game

## Group 1 Section 1

### Made by with ❤ by:

1. Hussein Mourad Kassem (6729)
2. Mohamed Ahmed Abdelwahab (6812)

## Implementation:

All the game is inside a class called `TicTacToe` and a class called `TicTacToeTest` for testing. We will go through
each method explaining it briefly.

### mapMovesToIndex()

We take a move from the user as a number between 1 and 42. This method maps each available move (Integer) to its
corresponding board indices (ArrayList). This makes it very easy to place the move into appropriate position. It is
called when constructing the class

Example:

* 1 to maps [0,0]
* 2 maps to [0,1]
* 10 maps to [1,2]...etc

Also, it initializes the board by a number between 1 and 42

```java
public class TicTacToe {
    private final String[][] board = new String[6][7];
    private final Map<Integer, ArrayList<Integer>> moveToIndexMap = new HashMap<>();

    private void mapMovesToIndex() {
        /* maps numbers to indices */
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
}
```

### printBoard:

This method prints the game board to the console. It achieves this by:

* looping over each element and separating it with horizontal separator.
* separating each row by vertical separator and joint separator.

```java
public class TicTacToe {
    private void printBoard() {
        if (round == 1) {
            System.out.println("Enter the number corresponding to the place you want to play: \n");
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print("\t" + board[i][j] + "\t");
                // Removes extra horizontal separator at the end
                if (j != 6) {
                    char horizontalSeparator = '|';
                    System.out.print(horizontalSeparator);
                }
            }
            System.out.println();

            for (int j = 0; j < board[0].length; j++) {
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
}
```

### isValidMove

Takes a move and checks if it is played or not. We store the played moves in a set. So it checks if the move is in the
set or not

* Returns true if it is a valid move
* Returns false if it is not a valid move

```java
public class TicTacToe {
    private final HashSet<Integer> playedMovesSet = new HashSet<>();

    private boolean isValidMove(int move) {
        return !this.playedMovesSet.contains(move);
    }
}
```

### play:

Takes a move and places it in the appropriate position. Firstly, It checks if the move is valid. Secondly, It gets a new
turn then It gets the index that this move maps to. It places the move into the board. Finally, It adds one the round
count

* Returns True if the move is played
* Returns False if the move is invalid

```java
public class TicTacToe {
    private final char X = 'X';
    private final char O = 'O';
    private char turn = X;
    private int round = 1;

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
}
```

### getTurn:

This method alternates between X and O each turn It doesn't change the turn if it is the first round.

```java
public class TicTacToe {
    private void getTurn() {
        if (round == 1) return;
        if (this.turn == O) {
            this.turn = X;
            return;
        }
        this.turn = O;
    }
}
```

### isWinner:

Checks if there is a winner to the game. It achieves this by:

* Looping over each element
* Checking If the element is equal to the next two elements beside it.
* Checking If the element is equal to the next two elements below it.
* Checking If the element is equal to the next two elements diagonally.
* If any of the above conditions is true then there is a winner.
* If its is round 42 and there is no winner then it is a tie.
* It handles array out of bound exception by simply continuing to the next iteration of the loop.

It returns true if there is a winner, or it is a tie else it returns false.

```java
public class TicTacToe {
    private boolean isWinner() {
        if (playedMovesSet.size() >= 5) { // no need to check before 5 moves
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
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
        if (round == 42) {
            System.out.println("Tie");
            return true;
        }
        return false;
    }
}
```

### resetGame:

When the game is done. we ask the user if he wants to start a new game. This method restarts a new game by resetting all
the values to its initial state.

* It asks the user to input 1 or 2.
* If he chooses 1 then it will start a new game.
* If he chooses 2 then it will exit.
* It clears the played moves set and set the round to 1 and empties the board

```java
public class TicTacToe {


    private void resetGame() {
        // Resets the game
        playedMovesSet.clear();
        this.round = 1;
        this.turn = X;
        int counter = 1;
        for (
                int i = 0;
                i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
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
}
```

### playerTurn

It takes input from the user and return the move

```java
public class TicTacToe {
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
}
```

### launch:

This method is the main driver of the game it keeps the game working. It prints the board, calls the different helping
methods, and checks if there is a winner.

```java
public class TicTacToe {
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
```

### test:

This method test the class as the class is encapsulated. So we test the class here.

```java
public class TicTacToe {
    public void test() {
        Integer[] moves = {9, 8, 1, 17, 3, 15, 2};
        for (Integer move : moves) {
            play(move);
        }
        printBoard();
        System.out.println();
        if (isWinner()) {
            System.out.println("Everything works as expected");
            return;
        }
        System.out.println("Doesn't work as expected");
    }
}
```

