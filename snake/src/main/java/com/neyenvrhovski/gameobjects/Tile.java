package com.neyenvrhovski.gameobjects;

import java.awt.Color;

public class Tile {

    private int x;
    private int y;
    private int tileNumber;
    private Color color;

    public Tile(int x, int y, int tilenumber, Color color){
        this.x = x;
        this.y = y;
        this.tileNumber = tilenumber;
        this.color = color;
    }

    public int getX(){
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY(){
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Color getColor(){
        return this.color;
    }

    public void setColor(Color color){
        this.color = color;
    }

    public int getTileNumber() {
        return tileNumber;
    }

    public void setTileNumber(int tileNumber) {
        this.tileNumber = tileNumber;
    }

}
