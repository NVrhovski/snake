package com.neyenvrhovski.core;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.neyenvrhovski.gameobjects.Snake;
import com.neyenvrhovski.gameobjects.Snake.Direction;

public class KeyBinder extends KeyAdapter{

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
}
