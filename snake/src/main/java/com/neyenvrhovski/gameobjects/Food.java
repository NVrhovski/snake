package com.neyenvrhovski.gameobjects;

import java.awt.Color;
import java.util.List;

public class Food {
    public static Tile generateFood(List<Tile> snakeBodyTiles) {
        int x;
        int y;
        boolean isSnakeBodyTile = false;
        do {
            x = (int) (Math.random() * 24);
            y = (int) (Math.random() * 18);
            for (Tile tile : snakeBodyTiles) {
                if (tile.getX() == x * 50 && tile.getY() == y * 50) {
                    isSnakeBodyTile = true;
                    break;
                }
            }
        } while (isSnakeBodyTile);
        return new Tile(x * 50, y * 50, (24 * y) + x, Color.WHITE);
    }

    public static boolean isFoodEaten(List<Tile> snakeBodyTiles, Tile foodTile) {
        Tile head = snakeBodyTiles.get(0);
        return head.getTileNumber() == foodTile.getTileNumber();
    }
}
