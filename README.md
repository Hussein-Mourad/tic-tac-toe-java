###names and ids:
Hussein Mourad Kassem 6729

mohamed ahmed abdelwahab 6812

Gp:1

sec:1
#report 
###first
- we made a new class and we called it TicTacToe and we put all the methods and functions in it 

#### the methods are :
1-    public TicTacToe(char turn) -> It takes which player should start first it takes a char X or O

2-    private void mapMovesToIndex() ->Maps available moves to board indices

3-  private void printBoard() ->     
* Prints the Game board to the console
* It loops over each element and separates it with horizontal separator
* At the end of each line it separates it with vertical separator and Joint separator
* If it is the first round it prints numbers to guide the user
* When the first move is made it only prints the moves
 
4-  private boolean isValidMove(int move ) ->  Takes a move and checks if this move is valid or not


 5- private boolean play(int move) ->
* Takes a move and play this move it if is valid.
* It gets a new turn
* It gets the index of the board from the move which is mapped by other function
* Finally it adds round count to indicate a new turn
* It returns true if it played the move else it returns false

6-private void getTurn()  ->  It alternates between X and O each turn

7-private boolean isWinner()  -> Returns true is there is a winner or tie else returns false

8-private void resetGame() ->
* Starts a new game by returning all the values to its initial state  
* It prints a menu to the user where he can choose between 1 and 2
* If he enters 1 it starts a new game
* If he enters 2 it stops the program

9-private int playerTurn() -> It asks the user for a move and returns it if it is valid


10-  public void launch() ->
* the main logic of the game
* It keeps the game working and takes input from the user and checks if its a valid move
* If its a valid move it plays it
* If there is a winner the game will stop

###second:
- we called the function in the main and we ran the program and it worked well you can test it your self 

we wish you will like it :D




