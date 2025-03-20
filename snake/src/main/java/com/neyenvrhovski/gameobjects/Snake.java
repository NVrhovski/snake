package com.neyenvrhovski.gameobjects;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Snake {

    public enum Direction {
        UP,
        DOWN,
        RIGHT,
        LEFT
    }

    private int length;
    private Direction currentDirection;
    private Direction nextDirection;
    private final List<Tile> bodyTiles;

    public Snake() {
        this.currentDirection = Direction.RIGHT;
        this.nextDirection = Direction.RIGHT;
        this.length = 1;
        this.bodyTiles = new ArrayList<>();
        this.bodyTiles.add(new Tile(550, 400, 227, Color.WHITE)); 
    }

    public Direction getCurrentDirection() {
        return this.currentDirection;
    }

    public void setCurrentDirection(Direction direction) {
        this.currentDirection = direction;
    }

    public Direction getNextDirection() {
        return nextDirection;
    }

    public void setNextDirection(Direction nextDirection) {
        this.nextDirection = nextDirection;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public List<Tile> getBodyTiles() {
        return bodyTiles;
    }

    public static boolean isGameOver(List<Tile> snakeBodyTiles) {
        Tile head = snakeBodyTiles.get(0);
        for (int i = 1; i < snakeBodyTiles.size(); i++) {
            if (head.getTileNumber() == snakeBodyTiles.get(i).getTileNumber()) {
                return true;
            }
        }
        return false;
    }
    
}
