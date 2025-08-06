package com.neyenvrhovski;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import com.neyenvrhovski.core.FrameTimer;
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
        mainFrame = new MainFrame(snake);

        tiles = new Tile[432];
        for (int iY = 0; iY < 18; iY++) {
            for (int iX = 0; iX < 24; iX++) {
                tiles[(24 * iY) + iX] = new Tile(iX * 50, iY * 50, (24 * iY) + iX, Color.BLACK);
            }
        }

        mainPanel = new MainPanel(tiles);
        mainFrame.add(mainPanel);
    }

    private static void onFrameChange() {
        snake.setCurrentDirection(snake.getNextDirection());
        snakeBodyTiles = snake.getBodyTiles();

        boolean isFoodEaten = moveSnake();
        boolean isGameOver = Snake.isGameOver(snakeBodyTiles);

        if (isGameOver) {
            handleGameOver();
            return;
        }

        updateTiles(isFoodEaten);

        if (isFoodEaten) {
            SoundPlayer.playSound("pickup.wav");
        }

        mainPanel.setTiles(tiles);
        mainPanel.repaint();
    }

    private static boolean moveSnake() {
        Tile prevTile = new Tile(0, 0, 0, null);
        Tile currentTile;
        boolean isFoodEaten = false;

        for (int i = 0; i < snakeBodyTiles.size(); i++) {
            currentTile = new Tile(
                snakeBodyTiles.get(i).getX(),
                snakeBodyTiles.get(i).getY(),
                snakeBodyTiles.get(i).getTileNumber(),
                snakeBodyTiles.get(i).getColor()
            );

            if (i == 0) {
                moveHead(snakeBodyTiles.get(i));
                isFoodEaten = Food.isFoodEaten(snakeBodyTiles, foodTile);
            } else {
                snakeBodyTiles.get(i).setTileNumber(prevTile.getTileNumber());
            }

            prevTile = currentTile;
        }
        return isFoodEaten;
    }

    private static void moveHead(Tile head) {
        switch (snake.getCurrentDirection()) {
            case UP -> {
                if (head.getTileNumber() < 24) {
                    head.setTileNumber(head.getTileNumber() + 408);
                    head.setY(850);
                } else {
                    head.setTileNumber(head.getTileNumber() - 24);
                    head.setY(head.getY() - 50);
                }
            }
            case DOWN -> {
                if (head.getTileNumber() > 407) {
                    head.setTileNumber(head.getTileNumber() - 408);
                    head.setY(0);
                } else {
                    head.setTileNumber(head.getTileNumber() + 24);
                    head.setY(head.getY() + 50);
                }
            }
            case RIGHT -> {
                if (head.getTileNumber() % 24 == 23) {
                    head.setTileNumber(head.getTileNumber() - 23);
                    head.setX(0);
                } else {
                    head.setTileNumber(head.getTileNumber() + 1);
                    head.setX(head.getX() + 50);
                }
            }
            case LEFT -> {
                if (head.getTileNumber() % 24 == 0) {
                    head.setTileNumber(head.getTileNumber() + 23);
                    head.setX(1150);
                } else {
                    head.setTileNumber(head.getTileNumber() - 1);
                    head.setX(head.getX() - 50);
                }
            }
            default -> throw new AssertionError();
        }
    }

    private static void updateTiles(boolean isFoodEaten) {
        for (Tile tile : tiles) {
            tile.setColor(Color.BLACK);
        }
        for (Tile bodyTile : snakeBodyTiles) {
            tiles[bodyTile.getTileNumber()].setColor(Color.WHITE);
        }
        tiles[foodTile.getTileNumber()].setColor(Color.WHITE);

        if (isFoodEaten) {
            Tile last = snakeBodyTiles.get(snakeBodyTiles.size() - 1);
            snakeBodyTiles.add(new Tile(last.getX(), last.getY(), last.getTileNumber(), Color.WHITE));
            foodTile = Food.generateFood(snakeBodyTiles);
        }
    }

    private static void handleGameOver() {
        timer.stop();
        int option = JOptionPane.showConfirmDialog(
            mainFrame,
            "<html>You lost!<br>Press OK to restart or Cancel to exit.</html>",
            "Game Over",
            JOptionPane.ERROR_MESSAGE
        );
        if (option == JOptionPane.OK_OPTION) {
            restart();
        } else {
            System.exit(0);
        }
    }

    private static void restart(){
        snake = new Snake();
        snakeBodyTiles = snake.getBodyTiles();
        foodTile = Food.generateFood(snakeBodyTiles);
        createPanel();
        timer.start();
    }
}
