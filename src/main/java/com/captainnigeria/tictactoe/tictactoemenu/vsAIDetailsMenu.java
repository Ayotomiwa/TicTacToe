package com.captainnigeria.tictactoe.tictactoemenu;

import com.captainnigeria.tictactoe.TicTacDemo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class vsAIDetailsMenu extends JDialog {
    private final MainMenu mainMenu;
    private JPanel contentPane;
    private JTextField playerNameField;
    private JLabel playerOneLabel;
    private JButton doneButton;
    private JButton previousButton;

    public vsAIDetailsMenu(String level, MainMenu mainMenu, int result) {
        setContentPane(contentPane);
        this.mainMenu = mainMenu;
        contentPane.setPreferredSize(this.mainMenu.getPreferredSize());
        this.mainMenu.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        String firstPlayerName = null;

        if (result == 1) {
            playerOneLabel.setForeground(Color.BLUE);
            playerOneLabel.setText("Player 2's Name (O)");
            firstPlayerName = "Captain AI";
        }

        final String finalFirstPlayerName = firstPlayerName;

        previousButton.addActionListener(e -> {
            this.mainMenu.setContentPane(new vsAIMainMenu(this.mainMenu).getContentPane());
            this.mainMenu.revalidate();
            this.mainMenu.repaint();
        });
          doneButton.addActionListener(e -> {
            if(playerNameField.getText().equals("")){
                return;
            }
            createGame(finalFirstPlayerName, level);
        });
    }

    public void createGame(String firstPlayerName, String level) {

        if (!playerNameField.getText().equals("")) {
            if (firstPlayerName == null) {
                new TicTacDemo(level, true, playerNameField.getText(), "Captain AI");
            } else {
                new TicTacDemo(level, true, firstPlayerName, playerNameField.getText());
            }
            mainMenu.dispose();
            dispose();
        }
    }
}
