# TicTacToe
TictTacToe (Human vs Player or AI (Mini-Max Algorithm)).

The game GUI was created using the Java Swing UI designer in intellij. The board size is 3 x 3, and comprises of buttons which represent each board location. The user plays the game by clicking a location on the board (a button), followed by a "Done" button to confirm the clicked move. The play (X or O) is then registered on the button selected. 

When the game starts, the user can choose between playing with a human or an AI player as seen below. 


 


<p>
 <img src="src/main/java/com/captainnigeria/tictactoe/Game Snapshots/16_11_2022 16_40_38.png"  hspace=20 style="width:500px ; height:800x">
 <img src="src/main/java/com/captainnigeria/tictactoe/Game Snapshots/Screenshot 2022-11-18 014727.png"  hspace=20  style="width:300px ; height:350x; vspace: 50px">
 <img src="src/main/java/com/captainnigeria/tictactoe/Game Snapshots/Screenshot 2022-11-18 014800.png" style="width:300px ; height:350x; padding: 50x">
</p>

<p>
<img src="src/main/java/com/captainnigeria/tictactoe/Game Snapshots/Untitled video - Made with Clipchamp.gif"  hspace=100 style=" width:400px ; height:300x">
<img src="src/main/java/com/captainnigeria/tictactoe/Game Snapshots/Screenshot 2022-11-17 232715.png" style="width:400px ; height:300x">
</p>


As seen above the user can select one of three modes- Easy, Medium & Hard. On Easy mode, the AI plays at random. It does not intend to win or block the Human Player from winning, it is comletely useless. However, in the medium and hard mode, the AI is smart enough to block the Human player from winning, and further guarantees a win or a draw in hard mode. The AI in Hard mode does this by usinga brute force Algorithm called Mini-Max.



# Mini-Max Algorithm 

This algortithm can be used in solved games like tictactoe that require just two players. In this implemenation, the recursive function returns a value when a terminal state is reached i.e  +10 for winning, -10 for losing and 0 for a draw. if no terminal state is reached, it continues to go through all the available locations on the board,and  recursively calls the minimax function on each available location, and return the best value.The best move is the index of the best value. 
