package com.captainnigeria.tictactoe;

import com.captainnigeria.tictactoe.tictactoemenu.MainMenu;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class TicTacDemo extends JFrame {

    private final TicTacDemo demo;
    private final String level;
    private JPanel panel1;
    private JButton button1;
    private JButton button4;
    private JButton button7;
    private JButton button2;
    private JButton button5;
    private JButton button8;
    private JButton button3;
    private JButton button6;
    private JButton button9;
    private final JButton[] boardButtons = {button1, button2, button3, button4, button5,
            button6, button7, button8, button9};

    private JButton buttonChosen;
    private JLabel player1Score;
    private JLabel player2Score;
    private JLabel player1Name;
    private JLabel player2Name;
    private JLabel playerLabel;

    private JButton doneButton;
    private JButton restartButton;
    private JButton nextRoundButton;
    private JButton menuButton;

    private char aiPlayer = 'O';
    private char firstHumanPlayer = 'X';
    private char currentPlayer;

    private volatile boolean playerWins = false;
    private volatile boolean firstPlayer = true;
    private volatile boolean roundPlayed;
    private volatile int boardPlay = 9;
    private volatile boolean humanOnly = false;
    private volatile boolean aiTurn;
    private volatile boolean maximumReached = false;
    private final String empty = "";


    public TicTacDemo(String level, boolean aiPlaying, String... playerNames) {
        demo = this;
        this.level = level;

        demo.addWindowFocusListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
            }
        });

        player1Name.setText(player1Name.getText() + "- " + playerNames[0]);
        player2Name.setText(player2Name.getText() + "- " + playerNames[1]);


        if (aiPlaying) {
            aiTurn = playerNames[0].equals("Captain AI");
            if (aiTurn) {
                aiPlayer = 'X';
                currentPlayer = aiPlayer;
                firstHumanPlayer = 'O';
            }
        } else {
            humanOnly = true;
            currentPlayer = firstHumanPlayer;
        }

        for (JButton button : boardButtons) {
            button.setText(empty);
            button.setForeground(Color.BLACK);
            button.addActionListener(e -> humanPlay(button));
        }
        doneButton.addActionListener(e -> setPlay());
        restartButton.addActionListener(e -> restartPlay());
        nextRoundButton.addActionListener(e -> doNextRound());
        menuButton.addActionListener(e -> goToMainMenu());

        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(850, 700);
        setVisible(true);
        setLocationRelativeTo(null);

        if (Objects.equals(currentPlayer, aiPlayer)) {
            startAI();
        }

    }

    public void startAI() {
        Thread aiThread = new Thread(this::playAiGame);
        if (!humanOnly) {
            aiThread.setDaemon(true);
            aiThread.start();
        }
    }

    public void goToMainMenu(){
        int result = JOptionPane.showConfirmDialog(demo, "Do you want to go back to the main menu ",
                "TicTacToe", JOptionPane.YES_NO_OPTION);
        if (result == 1){
            return;
        }
        demo.setVisible(false);
        new MainMenu();
        demo.dispose();
    }

    public void playAiGame() {
        currentPlayer = aiPlayer;
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int position = aiPlay();
        roundPlayed = true;
        aiTurn = false;
        aiSelectPosition(position);
    }

    public void aiSelectPosition(int position) {
        SwingUtilities.invokeLater(() -> {
            boardButtons[position].setText(String.valueOf(aiPlayer));
            buttonChosen = boardButtons[position];
            setPlay();
        });
    }

    public void humanPlay(JButton button) {

        if (playerWins || aiTurn) {
            return;
        }

        for (JButton b : boardButtons) {
            if (b.getForeground().equals(Color.BLACK) && !b.equals(button)) {
                b.setText(empty);
            }
        }

        if (button.getText().equals(empty)) {
            roundPlayed = true;
            buttonChosen = button;
            currentPlayer = firstHumanPlayer;
            if (humanOnly) {
                if (!firstPlayer) {
                    currentPlayer = 'O';
                    button.setText(String.valueOf(currentPlayer));
                    return;
                }
            }
            button.setText(String.valueOf(currentPlayer));
            return;
        }

        if (button.getForeground().equals(Color.BLACK)) {
            button.setText(empty);
            roundPlayed = false;
        }
    }

    public void setPlay() {

        if (!roundPlayed || playerWins) {
            if (maximumReached){
                doMaximumScoreReached(currentPlayer);
            }
            return;
        }

        synchronized (this) {
            boardPlay--;
        }

        String turn = "Player 2's Turn";
        Color playerColor = Color.red;
        Color labelColor = Color.blue;

        if (!firstPlayer) {
            playerColor = Color.blue;
            turn = ("Player 1's Turn");
            labelColor = Color.red;
        }
        buttonChosen.setForeground(playerColor);

        boolean xWins = checkWin(boardButtons, 'X');
        boolean oWins = checkWin(boardButtons, 'O');

        System.out.println("X wins- " + xWins);
        System.out.println("O wins- " + oWins);

        if (xWins || oWins) {
            JLabel scoreBoard = player1Score;
            String winningChant = "🎉🎊 🎉🎉 🎊🎊 Player 1 wins!!!!!!";
            char player = 'X';

            if (oWins){
                scoreBoard = player2Score;
                winningChant = "🎉 🎊🎉 🎉🎊 🎊 Player 2 wins!!!!!!";
                player = 'O';
            }
            scoreBoard.setText(scoreBoard.getText() + player);
            playerWins =true;
            JOptionPane.showMessageDialog(this, winningChant, "TicTacToe", JOptionPane.PLAIN_MESSAGE);
            revalidate();
            repaint();
            if(isMaximumWinReached(scoreBoard.getText())){
                maximumReached = true;
                doMaximumScoreReached(player);
                return;
            }
            nextRound();
            return;
        }  else {
            if (boardPlay == 0) {
                JOptionPane.showMessageDialog(this, "Game is a draw!!", "TicTacToe", JOptionPane.PLAIN_MESSAGE);
                nextRound();
                return;
            }
        }
        playerLabel.setBackground(labelColor);
        playerLabel.setText(turn);
        synchronized (this) {
            firstPlayer = !firstPlayer;
        }
        roundPlayed = false;
        if (!humanOnly && currentPlayer != aiPlayer) {
            aiTurn = true;
            startAI();
        }
    }

    public void restartPlay() {
        if (playerWins) {
            doNextRound();
            return;
        }
        if(maximumReached){
            doMaximumScoreReached(currentPlayer);
            return;
        }
        for (JButton button : boardButtons) {
            button.setText(empty);
            button.setForeground(Color.BLACK);
            button.setContentAreaFilled(false);
        }
        playerLabel.setText("Player 1's Turn");
        playerLabel.setBackground(Color.red);
        boardPlay = 9;
        playerWins = false;
        firstPlayer = true;
        aiTurn = player1Name.getText().contains("Captain AI");
        if (aiTurn) {
            currentPlayer = aiPlayer;
            playAiGame();
        }
    }

    public void nextRound() {
        playerLabel.setVisible(false);
        nextRoundButton.setVisible(true);
    }

    public void doMaximumScoreReached(char player){
        int result = JOptionPane.showOptionDialog(this, player + " has won this game " +
                        "\nStart a new Game?",
                "Level- " + level, JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, new String[]{"New Round?", "Main Menu?"}, "New Round?");
        if (result == 1){
            maximumReached = false;
            goToMainMenu();
        }

        else if (result == 0){
            restartPlay();
        }
    }

    public boolean isMaximumWinReached(String s){
        return s.length() == 5;
    }

    public void doNextRound() {

        int result = JOptionPane.showConfirmDialog(demo, "Start a new round",
                "TicTacToe", JOptionPane.YES_NO_OPTION);
        if (result == 0) {
            playerWins = false;
            if (maximumReached) {
                maximumReached = false;
                player1Score.setText("");
                player2Score.setText("");
            }
            restartPlay();
            nextRoundButton.setVisible(false);
            playerLabel.setVisible(true);
        }
    }

    public int aiPlay() {
        int[] possibleMovesIndex = availableMoves(boardButtons);
        int aiMove = possibleMovesIndex[new Random().nextInt(boardPlay)];

        switch (this.level) {
            case "Easy" -> aiMove = playEasyAI(possibleMovesIndex);
            case "Medium" -> aiMove = playMediumAI(possibleMovesIndex, firstHumanPlayer);
            case "Hard" -> aiMove = playHardAI(possibleMovesIndex);
        }
        System.out.println("AI MOVE " + aiMove);
        return aiMove;
    }

    public int playEasyAI(int[] possibleMovesIndex) {
        return possibleMovesIndex[new Random().nextInt(boardPlay)];
    }


    public int playMediumAI(int[] possibleMovesIndex, char player) {

        if (aiCanWinEarly() && player != aiPlayer){
            player = aiPlayer;
        }

        int[][] winningPositions = new int[][]{{0, 1, 2}, {0, 4, 8}, {0, 3, 6}, {1, 4, 7}, {2, 4, 6}, {2, 5, 8}, {3, 4, 5}, {6, 7, 8}};

        for (int[] winningPosition : winningPositions) {
            int blockIndex = getWinningMove(player, winningPosition);
            for (int possibleMove : possibleMovesIndex) {
                if (possibleMove == blockIndex) {
                    return blockIndex;
                }
            }
        }
        return possibleMovesIndex[new Random().nextInt(boardPlay)];
    }

    public boolean aiCanWinEarly(){
        int[][] winningPositions = new int[][]{{0, 1, 2}, {0, 4, 8}, {0, 3, 6}, {1, 4, 7}, {2, 4, 6}, {2, 5, 8}, {3, 4, 5}, {6, 7, 8}};

       return !Arrays.stream(winningPositions).map((s)-> getWinningMove(aiPlayer, s )).allMatch((s)-> s == -1);
    }

    public int playHardAI(int[] possibleMovesIndex) {

        boolean firstPlay = boardPlay == 9;

        if (firstPlay) {
            return playEasyAI(possibleMovesIndex);
        }

        if (aiCanWinEarly()){
            return playMediumAI(possibleMovesIndex, currentPlayer);
        }

        JButton[] practiceButtons = new JButton[boardButtons.length];

        for (int i = 0; i < boardButtons.length; i++) {
            practiceButtons[i] = new JButton();
            practiceButtons[i].setText(boardButtons[i].getText());
        }

        int bestMove = 0;
        int bestScore = -10000;
        for (int availableMove : availableMoves(practiceButtons)) {
            practiceButtons[availableMove].setText(String.valueOf(aiPlayer));
            int score = miniMax(practiceButtons, false);
            practiceButtons[availableMove].setText(empty);
            if (score > bestScore) {
                bestScore = score;
                bestMove = availableMove;
            }
        }
        return bestMove;
    }

    public int miniMax(JButton[] practiceButtons, boolean isMaximizing) {

        int playCount = 9;

        for (JButton buttons : practiceButtons) {
            if (buttons.getText().equals("X") || buttons.getText().equals("O")) {
                playCount--;
            }
        }

        if (checkWin(practiceButtons, aiPlayer)) {
            return 10;
        } else if (checkWin(practiceButtons, firstHumanPlayer)) {
            return -10;
        } else if (playCount == 0) {
            return 0;
        }

        int bestScore;

        if (isMaximizing) {
            bestScore = -10000;
            for (int availableMove : availableMoves(practiceButtons)) {
                practiceButtons[availableMove].setText(String.valueOf(aiPlayer));
                int score = miniMax(practiceButtons, false);
                practiceButtons[availableMove].setText(empty);
                if (score > bestScore) {
                    bestScore = score;
                }
            }
        } else {
            bestScore = 10000;
            for (int availableMove : availableMoves(practiceButtons)) {
                practiceButtons[availableMove].setText(String.valueOf(firstHumanPlayer));
                int score = miniMax(practiceButtons, true);
                practiceButtons[availableMove].setText(empty);
                if (score < bestScore) {
                    bestScore = score;
                }
            }
        }

        return bestScore;
    }

    public int[] availableMoves(JButton[] buttons) {

        int playCount = 9;
        for (JButton button : buttons) {
            if (button.getText().equals("X") || button.getText().equals("O")) {
                playCount--;
            }
        }
        int[] possibleMovesIndex = new int[playCount];

        int index = 0;
        for (int i = 0; i < buttons.length; i++) {
            if (!buttons[i].getText().equals("O") && !buttons[i].getText().equals("X")) {
                possibleMovesIndex[index] = i;
                index++;
            }
        }
        return possibleMovesIndex;
    }

    public int getWinningMove(char player, int[] moves) {
        int count = 0;
        int move = -1;

        for (int s : moves) {
            if (boardButtons[s].getText().equals(String.valueOf(player))) {
                count++;
            }
        }
        if (count == 2) {
            for (int i = 0; i < 3; i++) {
                if (boardButtons[moves[i]].getText().equals(empty)) {
                    move = moves[i];
                }
            }
        }
        return move;
    }

    public void setWinningBackground(Color color, JButton... buttons){

        for (JButton button : buttons){
            button.setForeground(Color.black);
            button.setContentAreaFilled(true);
            button.setBackground(color);
        }
    }


    public boolean checkWin(JButton[] buttons, char player) {

        if (buttons[0].getText().equals(buttons[1].getText()) && buttons[0].getText().equals(buttons[2].getText())) {
            if (buttons[0].getText().equals(String.valueOf(player))) {
                setWinningBackground(buttons[0].getForeground(), buttons[0], buttons[1], buttons[2]);
                return buttons[0].getText().equals(String.valueOf(player));
            }
        }
        if (buttons[0].getText().equals(buttons[4].getText()) && buttons[0].getText().equals(buttons[8].getText())) {
            if (buttons[0].getText().equals(String.valueOf(player))){
                setWinningBackground(buttons[0].getForeground(), buttons[0], buttons[4], buttons[8]);
                return buttons[0].getText().equals(String.valueOf(player));
        }
        }
        if (buttons[0].getText().equals(buttons[3].getText()) && buttons[0].getText().equals(buttons[6].getText())) {
            if (buttons[0].getText().equals(String.valueOf(player))) {
                setWinningBackground(buttons[0].getForeground(), buttons[0], buttons[3], buttons[6]);
                return buttons[0].getText().equals(String.valueOf(player));
            }
        }
        if (buttons[1].getText().equals(buttons[4].getText()) && buttons[1].getText().equals(buttons[7].getText())) {
            if (buttons[1].getText().equals(String.valueOf(player))) {
                setWinningBackground(buttons[1].getForeground(), buttons[1], buttons[4], buttons[7]);
                return buttons[1].getText().equals(String.valueOf(player));
            }
        }
        if (buttons[2].getText().equals(buttons[4].getText()) && buttons[2].getText().equals(buttons[6].getText())) {
            if (buttons[2].getText().equals(String.valueOf(player))) {
                setWinningBackground(buttons[2].getForeground(), buttons[2], buttons[4], buttons[6]);
                return buttons[2].getText().equals(String.valueOf(player));
            }
        }
        if (buttons[2].getText().equals(buttons[5].getText()) && buttons[2].getText().equals(buttons[8].getText())) {
            if (buttons[2].getText().equals(String.valueOf(player))) {
                setWinningBackground(buttons[2].getForeground(), buttons[2], buttons[5], buttons[8]);
                return buttons[2].getText().equals(String.valueOf(player));
            }
        }
        if (buttons[3].getText().equals(buttons[4].getText()) && buttons[3].getText().equals(buttons[5].getText())) {
            if (buttons[3].getText().equals(String.valueOf(player))) {
                setWinningBackground(buttons[3].getForeground(), buttons[3], buttons[4], buttons[5]);
                return buttons[3].getText().equals(String.valueOf(player));
            }
        }
        if (buttons[6].getText().equals(buttons[7].getText()) && buttons[6].getText().equals(buttons[8].getText())) {
            if (buttons[6].getText().equals(String.valueOf(player))){
                setWinningBackground(buttons[6].getForeground(), buttons[6], buttons[7], buttons[8]);
                return buttons[7].getText().equals(String.valueOf(player));
            }

        }
        return false;
    }
}

