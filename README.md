# TicTacToe
TictTacToe Game vs Player or vs AI (Mini-Max Algorithm).

The game UI was created using Java Swing UI designer. The board size is 3 x 3, and comprises of buttons which represent each board location. The user plays the game by clicking a location on the board(a button), followed by a "Done" button to confirm the clicked move. The play (X or O) is then registered on the the button selected. 

When the game starts, the user can choose between playing with human or AI player. The 


<img src="src/main/java/com/captainnigeria/tictactoe/images/16_11_2022 16_40_38.png" style=" width:500px ; height:400x" >







On Easy mode, the AI plays at random. It does not intend to win or block the Human Player from winning, it is comletely useless. However, in the medium and hard mode, the AI is smart enough to block the Human player from winning, and further guarantees a win or a draw in hard mode. The AI in Hard mode does this by usinga brute force Algorithm called Mini-Max


# Mini-Max Algorithm 

This algortithm can be used in solved games like tictactoe that require just two players. In this implemenation, the recursive function returns a value when a terminal state is reached i.e  +10 for winning, -10 for losing and 0 for a draw. if no terminal state is reached, it continues to go through all the available locations on the board,and  recursively calls the minimax function on each available location, and return the best value.The best move is the index of the best value. 
