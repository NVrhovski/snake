package com.neyenvrhovski.core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.neyenvrhovski.gameobjects.Snake;
import com.neyenvrhovski.gameobjects.Snake.Direction;

public class KeyBinder implements KeyListener, ActionListener {

    private final Snake snake;

    public KeyBinder(Snake snake){
        this.snake = snake;
    } 

    @Override
    public void keyPressed(KeyEvent event) {
        Direction currentDirection = this.snake.getCurrentDirection();
        switch (event.getKeyCode()) {
            case KeyEvent.VK_UP -> {
                if(currentDirection != Direction.DOWN){
                    this.snake.setNextDirection(Direction.UP);
                }
            }
            case KeyEvent.VK_DOWN -> {
                if(currentDirection != Direction.UP){
                    this.snake.setNextDirection(Direction.DOWN);
                }
            }
            case KeyEvent.VK_RIGHT -> {
                if(currentDirection != Direction.LEFT){
                    this.snake.setNextDirection(Direction.RIGHT);
                }
            }
            case KeyEvent.VK_LEFT -> {
                if(currentDirection != Direction.RIGHT){
                    this.snake.setNextDirection(Direction.LEFT);
                }
            }
            default -> {}
        }
    }

    @Override
    public void keyReleased(KeyEvent event) {}

    @Override
    public void keyTyped(KeyEvent event) {}

    @Override
    public void actionPerformed(ActionEvent event) {}

}
