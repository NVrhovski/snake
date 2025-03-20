package com.neyenvrhovski.graphics;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import com.neyenvrhovski.gameobjects.Tile;

public class Drawer {
    public static void drawTiles(Tile[] tiles, Graphics2D drawer){
        for (Tile tile : tiles) {
            Rectangle2D square = new Rectangle2D.Double(tile.getX(), tile.getY(), 50, 50);
            drawer.setColor(tile.getColor());
            drawer.fillRect(tile.getX(), tile.getY(), 50, 50);
            drawer.draw(square);
        }

    }
}
