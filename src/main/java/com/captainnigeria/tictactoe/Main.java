package com.captainnigeria.tictactoe;

import com.captainnigeria.tictactoe.tictactoemenu.MainMenu;

import javax.swing.*;

public class Main {

    public static void launch() {
        new MainMenu();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::launch);
    }

}
