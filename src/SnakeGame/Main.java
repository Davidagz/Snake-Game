package SnakeGame;

import java.lang.String;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
  public static boolean gameOver = false;
  public static Group root;

  @Override
  public void start(Stage stage) {

    // Create JavaFX Scene
    final int width = 800, height = 800;
    root = new Group();
    Scene scene = new Scene(root, width, height, Color.LIGHTSEAGREEN);
    stage.setResizable(false);
    stage.setScene(scene);
    stage.setTitle("Snake");
    stage.show();

    // Create and setup snake and food
    SnakeNode snakeHead = new SnakeNode(416, 416);
    Snake snake = new Snake(snakeHead);
    Food food = new Food();

    // Timer to update snake every 200 MS
    AnimationTimer timer = new AnimationTimer() {
      private long lastUpdate = 0;
      @Override
      public void handle(long now) {
        if (snake.hasCollided()){
          gameOver = true;
          this.stop();
          System.out.println("Wall or snake tail hit, game over");
        }

        // wait until at least 200ms have passed, then update snake
        else if (now - lastUpdate >= 150_000_000) {
          snake.update();

          // check if snake has found food
          if (snake.getHead().getXLocation() == food.getXLocation() &&
              snake.getHead().getYLocation() == food.getYLocation()){
            snake.addNode();
            food.spawnFood();
          }
          lastUpdate = now;
        }
      }
    };

    // Key listener to handle direction changes and starting the game
    scene.setOnKeyPressed(new EventHandler<>() {
      @Override
      public void handle(KeyEvent keyEvent) {
        if (gameOver) {
          scene.removeEventHandler(KeyEvent.KEY_PRESSED, this);
        }
        switch (keyEvent.getCode()) {
          case ENTER -> {
            snake.setDirection(Snake.Direction.LEFT);
            timer.start();
          }
          case UP -> snake.setDirection(Snake.Direction.UP);
          case DOWN -> snake.setDirection(Snake.Direction.DOWN);
          case LEFT -> snake.setDirection(Snake.Direction.LEFT);
          case RIGHT -> snake.setDirection(Snake.Direction.RIGHT);
        }
      }
    });


  }


  public static void main(String[] args) {
    launch(args);
  }
}
