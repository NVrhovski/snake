package com.neyenvrhovski;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import com.neyenvrhovski.core.FrameTimer;
import com.neyenvrhovski.core.KeyBinder;
import com.neyenvrhovski.gameobjects.Food;
import com.neyenvrhovski.gameobjects.Snake;
import com.neyenvrhovski.gameobjects.Tile;
import com.neyenvrhovski.graphics.MainFrame;
import com.neyenvrhovski.graphics.MainPanel;
import com.neyenvrhovski.sound.SoundPlayer;


public class Main {
    static MainPanel mainPanel;
    static MainFrame mainFrame;
    static Timer timer;
    static Snake snake;
    static Tile[] tiles;
    static Tile foodTile;
    static List<Tile> snakeBodyTiles;
    public static void main(String[] args) {
        snake = new Snake();
        snakeBodyTiles = snake.getBodyTiles();
        foodTile = Food.generateFood(snakeBodyTiles);
        createPanel();
        JOptionPane.showMessageDialog(mainFrame, "Press the button to begin", "Snake", JOptionPane.PLAIN_MESSAGE);
        startTimer();
    }

    private static void startTimer(){
        ActionListener taskPerformer = e -> onFrameChange();
        timer = FrameTimer.getAndStartTimer(taskPerformer);
        timer.start();
    }

    private static void createPanel(){
        mainFrame = new MainFrame();

        tiles = new Tile[432];
        for (int iY = 0; iY < 18; iY++) {
            for (int iX = 0; iX < 24; iX++) {
                tiles[(24 * iY) + iX] = new Tile(iX * 50, iY * 50, (24 * iY) + iX, Color.BLACK);
            }
        }

        mainPanel = new MainPanel(tiles);
        mainFrame.addKeyListener(new KeyBinder(snake));
        mainFrame.add(mainPanel);
    }

    private static void onFrameChange(){
        snake.setCurrentDirection(snake.getNextDirection());
        snakeBodyTiles = snake.getBodyTiles();
        Boolean isFoodEaten = false;
        Boolean isGameOver;
        boolean hasToPlaySound = false;
        Tile prevTile = new Tile(0, 0, 0, null);
        Tile currentTile;
        for (int i = 0; i < snakeBodyTiles.size(); i++) {
            currentTile = new Tile(snakeBodyTiles.get(i).getX(), snakeBodyTiles.get(i).getY(), snakeBodyTiles.get(i).getTileNumber(), snakeBodyTiles.get(i).getColor());
            if (i == 0){
                switch (snake.getCurrentDirection()) {
                    case UP -> {
                        if (snakeBodyTiles.get(i).getTileNumber() < 24){
                            snakeBodyTiles.get(i).setTileNumber(snakeBodyTiles.get(i).getTileNumber() + 408);
                            snakeBodyTiles.get(i).setY(850);
                        } else {
                            snakeBodyTiles.get(i).setTileNumber(snakeBodyTiles.get(i).getTileNumber() - 24);
                            snakeBodyTiles.get(i).setY(snakeBodyTiles.get(i).getY() - 50);
                        }
                    }
                    case DOWN -> {
                        if (snakeBodyTiles.get(i).getTileNumber() > 407){
                            snakeBodyTiles.get(i).setTileNumber(snakeBodyTiles.get(i).getTileNumber() - 408);
                            snakeBodyTiles.get(i).setY(0);
                        } else {
                            snakeBodyTiles.get(i).setTileNumber(snakeBodyTiles.get(i).getTileNumber() + 24);
                            snakeBodyTiles.get(i).setY(snakeBodyTiles.get(i).getY() + 50);
                        }
                    }
                    case RIGHT -> {
                        if (snakeBodyTiles.get(i).getTileNumber() % 24 == 23){
                            snakeBodyTiles.get(i).setTileNumber(snakeBodyTiles.get(i).getTileNumber() - 23);
                            snakeBodyTiles.get(i).setX(0);
                        } else {
                            snakeBodyTiles.get(i).setTileNumber(snakeBodyTiles.get(i).getTileNumber() + 1);
                            snakeBodyTiles.get(i).setX(snakeBodyTiles.get(i).getX() + 50);
                        }
                    }
                    case LEFT -> {
                        if(snakeBodyTiles.get(i).getTileNumber() % 24 == 0){
                            snakeBodyTiles.get(i).setTileNumber(snakeBodyTiles.get(i).getTileNumber() + 23);
                            snakeBodyTiles.get(i).setX(1150);
                        } else {
                            snakeBodyTiles.get(i).setTileNumber(snakeBodyTiles.get(i).getTileNumber() - 1);
                            snakeBodyTiles.get(i).setX(snakeBodyTiles.get(i).getX() - 50);
                        }
                    }
                    default -> throw new AssertionError();
                }
                isFoodEaten = Food.isFoodEaten(snakeBodyTiles, foodTile);
                isGameOver = Snake.isGameOver(snakeBodyTiles);
                if (isGameOver){
                    timer.stop();
                    int option = JOptionPane.showConfirmDialog(mainFrame, "<html>You lost!<br>Press OK to restart or Cancel to exit.</html>", "Game Over", JOptionPane.ERROR_MESSAGE);
                    if (option == JOptionPane.OK_OPTION){
                        restart();
                    } else {
                        System.exit(0);
                    }
                }
            } else {
                snakeBodyTiles.get(i).setTileNumber(prevTile.getTileNumber());
            }

            tiles[snakeBodyTiles.get(i).getTileNumber()].setColor(Color.WHITE);
            tiles[foodTile.getTileNumber()].setColor(Color.WHITE);
            if (i == snakeBodyTiles.size() - 1) {
                if (isFoodEaten){
                    snakeBodyTiles.add(new Tile(currentTile.getX(), currentTile.getY(), currentTile.getTileNumber(), Color.WHITE));
                    foodTile = Food.generateFood(snakeBodyTiles);
                    isFoodEaten = false;
                    hasToPlaySound = true;
                    tiles[currentTile.getTileNumber()].setColor(Color.WHITE);
                } else {
                    tiles[currentTile.getTileNumber()].setColor(Color.BLACK);
                }
            }
            prevTile = currentTile;
        }
        if (hasToPlaySound){
            SoundPlayer.playSound("pickup.wav");
        }
        mainPanel.setTiles(tiles);
        mainPanel.repaint();
    }

    private static void restart(){
        snake = new Snake();
        snakeBodyTiles = snake.getBodyTiles();
        foodTile = Food.generateFood(snakeBodyTiles);
        createPanel();
        timer.start();
    }
}
