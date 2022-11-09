package com.captainnigeria.tictactoe.tictactoemenu;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class vsAIMainMenu extends JDialog {
    private final MainMenu mainMenu;
    private JPanel contentPane;
    private JButton easyButton;
    private JButton mediumButton;
    private JButton hardButton;
    private JButton previousButton;

    public vsAIMainMenu(MainMenu mainMenu) {
        setContentPane(contentPane);
        this.mainMenu = mainMenu;
        this.mainMenu.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        JButton[] buttons = {easyButton, mediumButton, hardButton, previousButton};
        for (JButton button : buttons) {
            button.addActionListener(e -> {
                if (e.getActionCommand().equals(previousButton.getText())) {
                    this.mainMenu.setContentPane(mainMenu.contentPane);
                    mainMenu.revalidate();
                    mainMenu.repaint();
                    return;
                }
                nextPage(e.getActionCommand());
                mainMenu.revalidate();
                mainMenu.repaint();
            });
        }
//        contentPane.setPreferredSize(this.mainMenu.getPreferredSize());
//        pack();
    }

    public void nextPage(String level){
        String[] options = new String[]{"X", "O"};
        int result = JOptionPane.showOptionDialog(this.mainMenu, " Choose - First Player (X) or Second Player(O)?",
                "Level- " + level, JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]);
        System.out.println(result);
        if (result >= 0) {
            this.mainMenu.setContentPane(new vsAIDetailsMenu(level, mainMenu, result)
                    .getContentPane());
        }
    }
}

