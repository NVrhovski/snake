package com.neyenvrhovski.graphics;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import com.neyenvrhovski.gameobjects.Tile;

public class MainPanel extends JPanel{

    private Tile[] tiles;

    public MainPanel(Tile[] tiles){
        this.tiles = tiles;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        Drawer.drawTiles(this.tiles, g2D);
    }

    public void setTiles(Tile[] tiles){
        this.tiles = tiles;
    }
}
