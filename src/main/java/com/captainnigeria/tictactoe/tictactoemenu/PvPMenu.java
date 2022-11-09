package com.captainnigeria.tictactoe.tictactoemenu;

import com.captainnigeria.tictactoe.TicTacDemo;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PvPMenu extends JDialog {
    public JPanel contentPane;
    private JTextField playerOneField;
    private JButton previousButton;
    private JButton doneButton;
    private JTextField playerTwoField;


    public PvPMenu(MainMenu mainMenu) {
        setContentPane(contentPane);
        mainMenu.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
        previousButton.addActionListener(e -> {
            mainMenu.setContentPane(mainMenu.contentPane);
            revalidate();
            repaint();
        });
        doneButton.addActionListener(e -> {
            if(playerOneField.getText().equals("") || playerTwoField.getText().equals("")) {
                return;
            }
            mainMenu.dispose();
            dispose();
            new TicTacDemo(null, false, playerOneField.getText(), playerTwoField.getText());
        });
       // pack();
    }
}
