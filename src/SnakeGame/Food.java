package SnakeGame;

import javafx.scene.image.Image;
import javafx.scene.Node;
import javafx.scene.image.ImageView;

import static SnakeGame.Main.root;

public class Food {

  private double xLocation;
  private double yLocation;

  private final Image foodImage = new Image("https://icons.iconarchive.com/icons/martin-berube/food/32/apple-icon.png");
  Node foodIcon;

  public double getXLocation() { return xLocation; }

  public double getYLocation() { return yLocation; }

  public void spawnFood(){
    // Generate a random x and y location to spawn food
    int min = 0;
    int max = 24;
    int xFactor = (int)Math.floor(Math.random()*(max-min+1)+min);
    int yFactor = (int)Math.floor(Math.random()*(max-min+1)+min);
    this.xLocation = xFactor * 32;
    this.yLocation = yFactor * 32;
    foodIcon.relocate(xLocation, yLocation);
    System.out.println("Food spawned at X: " + this.xLocation + " Y: " + this.yLocation);
  }

  public Food(){
    // Generate a random x and y location to spawn food
    foodIcon = new ImageView(foodImage);
    root.getChildren().add(foodIcon);
    spawnFood();
  }

}
