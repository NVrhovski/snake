package com.neyenvrhovski.graphics;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
    public MainFrame(){
        setVisible(true);
        setTitle("Snake");
        setSize(1200, 928);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
