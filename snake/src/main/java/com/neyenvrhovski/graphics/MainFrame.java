package com.neyenvrhovski.graphics;

import javax.swing.JFrame;

import com.neyenvrhovski.core.KeyBinder;
import com.neyenvrhovski.gameobjects.Snake;

public class MainFrame extends JFrame {
    public MainFrame(Snake snake){
        setVisible(true);
        setTitle("Snake");
        setSize(1200, 928);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(new KeyBinder(snake));
    }
}
