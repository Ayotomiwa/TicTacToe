# TicTacToe
TictTacToe (Human vs Player or AI (Mini-Max Algorithm)).

The game GUI was created using the Java Swing UI designer in intellij IDE. The board size is 3 x 3, and comprises of buttons which represent each cell on board. The user plays the game by clicking a cell on the board, followed by a "Done" button to confirm the clicked move. The play (X or O) is then registered on the button selected. 

When the game starts, the user can choose between playing with a human or an AI player as seen below. 

<p>
 <img src="src/main/java/com/captainnigeria/tictactoe/Game Snapshots/GameMenu.png" hspace=100 style="width:400px ; height:300x">
 <img src="src/main/java/com/captainnigeria/tictactoe/Game Snapshots/playerMenu.png" style="width:400px ; height:300x; vspace: 50px">
</p>


<p>
<img src="src/main/java/com/captainnigeria/tictactoe/Game Snapshots/AIMenu1.png" hspace=100 style=" width:400px ; height:350x">
<img src="src/main/java/com/captainnigeria/tictactoe/Game Snapshots/AIMenu2.png" style="width:400px ; height:300x">
</p>


As seen above, the user can select one of three modes- Easy, Medium & Hard. On Easy mode, the AI plays at random. It does not intend to win or block the Human Player from winning, it is comletely useless. However, in the medium and hard mode, the AI is smart enough to win or block the Human player from winning. A win or draw is guaranteed by the AI if the user selects Hard Mode, the AI uses a brute force Algorithm called Mini-Max to stay several steps ahead of the human player. 

# Mini-Max Algorithm 

In this MiniMax implementation, the recursive mininmax function returns a value when a terminal state is reached i.e  +10 for winning, -10 for losing and 0 for a draw. if no terminal state is reached, the AI continues to go through all the available locations on the board, and recursively calls the minimax function on each available location. The AI plays for both players until a terminal state is reached and no location is left. The returned value of the function is the best value and the best move is the index of the best value. 

I made several Hard-Coded improvements to the Hard Mode, so it does not fully rely on the Mini-Max Algorithm. First, the AI is able to play it's first at random if the board is empty to avoid repetitive moves by the AI. Also, the AI is smart enough to take an early win when the human Player makes an error. 

<p>
<img src="src/main/java/com/captainnigeria/tictactoe/Game Snapshots/Untitled video - Made with Clipchamp.gif" hspace=80 style=" width:400px ; height:300x">
<img src="src/main/java/com/captainnigeria/tictactoe/Game Snapshots/AIWins.png" style="width:400px ; height:500x">
</p>




