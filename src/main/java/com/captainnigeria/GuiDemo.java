package com.captainnigeria;



import javax.swing.*;

public class GuiDemo extends  JFrame {
    private JPanel guiPanel;
    private JPanel toolsPanel;
    private JPanel tittlePanel;
    private JLabel pendingTitle;
    private JPanel selectMenuPanel;
    private JButton slctDoneButton;
    private JButton sctDeleteButton;
    private JCheckBox selectAll;
    private JPanel pendingPanel;
    private JPanel outerPendingPanel;


    public GuiDemo(){
        add(guiPanel);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
    }


    private void createUIComponents() {

    }

    public static void launch() {
        new GuiDemo();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GuiDemo::launch);
    }

}