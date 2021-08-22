package SnakeGame;

import java.util.ArrayList;

public class Snake {

  public enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT
  }
  private Direction direction;
  private final ArrayList<SnakeNode> snakeArray;
  private final SnakeNode snakeHead;

  public SnakeNode getHead(){
    return this.snakeHead;
  }

  public void setDirection(Direction direction) { this.direction = direction; }

  // checks head collision with snake tail and walls
  public boolean hasCollided(){
    if (snakeHead.getXLocation() > 768 || snakeHead.getYLocation() > 768 ||
        snakeHead.getXLocation() < 0 || snakeHead.getYLocation() < 0){
      return true;
    }

    for (int i = 1; i < snakeArray.size(); i++){
      if (snakeHead.getXLocation() == snakeArray.get(i).getXLocation() &&
          snakeHead.getYLocation() == snakeArray.get(i).getYLocation()){
        return true;
      }
    }
    return false;
  }

  // Adds a node to the end of the snake, based on the direction snake is going
  public void addNode(){
    SnakeNode node;
    SnakeNode lastNode = snakeArray.get(snakeArray.size() -1);

    switch (this.direction){
      case UP -> node = new SnakeNode(lastNode.getXLocation(), lastNode.getYLocation() - 32);
      case DOWN -> node = new SnakeNode(lastNode.getXLocation(), lastNode.getYLocation() + 32);
      case LEFT -> node = new SnakeNode(lastNode.getXLocation() + 32, lastNode.getYLocation());
      case RIGHT -> node = new SnakeNode(lastNode.getXLocation() - 32, lastNode.getYLocation());
      default -> throw new IllegalStateException("Unexpected value: " + this.direction);
    }
    snakeArray.add(node);
  }

  // Function is run for every tick in the game to update the location of the snake
  // Starts from the tail and moves to the head, updating location to prev node
  public void update(){
    SnakeNode prev, current;
    for (int i = snakeArray.size()-1; i>0; --i){
      prev = snakeArray.get(i-1);
      current = snakeArray.get(i);
      current.setXLocation(prev.getXLocation());
      current.setYLocation(prev.getYLocation());
    }
    switch (this.direction){
      case UP -> snakeHead.setYLocation(snakeHead.getYLocation() - 32);
      case DOWN -> snakeHead.setYLocation(snakeHead.getYLocation() + 32);
      case LEFT -> snakeHead.setXLocation(snakeHead.getXLocation() - 32);
      case RIGHT -> snakeHead.setXLocation(snakeHead.getXLocation() + 32);
      default -> throw new IllegalStateException("Unexpected value: " + this.direction);
    }

  }

  public Snake(SnakeNode head){
    snakeArray = new ArrayList<>();
    snakeArray.add(head);
    this.snakeHead = head;
    this.direction = Direction.LEFT;
    this.addNode();
    this.addNode();
  }

}
