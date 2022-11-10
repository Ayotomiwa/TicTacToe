package com.captainnigeria.tictactoe.tictactoemenu;

import javax.swing.*;

public class MainMenu extends JDialog {
    private final MainMenu mainMenu;
    protected JPanel contentPane;
    private JButton vsPlayerButton;
    private JButton vsAIButton;

    public MainMenu() {
        mainMenu = this;
        setModalityType(ModalityType.MODELESS);
        setContentPane(contentPane);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        vsPlayerButton.addActionListener(e -> {
            setContentPane(new PvPMenu(mainMenu).contentPane);
            revalidate();
            repaint();
        });
        vsAIButton.addActionListener(e -> {
            setContentPane(new VsAIMainMenu(mainMenu).getContentPane());
            revalidate();
            repaint();
        });
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }
}
