package SnakeGame;

import javafx.scene.image.Image;
import javafx.scene.Node;
import javafx.scene.image.ImageView;

import static SnakeGame.Main.root;

public class SnakeNode {
  private final Image snakeImage = new Image("https://icons.iconarchive.com/icons/custom-icon-design/flatastic-6/32/Square-icon.png");
  private double xLocation;
  private double yLocation;
  Node snakeIcon;

  public double getXLocation() {
    return xLocation;
  }

  public void setXLocation(double xLocation) {
    this.xLocation = xLocation;
    snakeIcon.relocate(this.xLocation, this.yLocation);
  }

  public double getYLocation() {
    return yLocation;
  }

  public void setYLocation(double yLocation) {
    this.yLocation = yLocation;
    snakeIcon.relocate(this.xLocation, this.yLocation);
  }

  public SnakeNode(double x, double y){
    // set default start location and length
    this.xLocation = x;
    this.yLocation = y;
    snakeIcon = new ImageView(snakeImage);
    root.getChildren().add(snakeIcon);
    snakeIcon.relocate(this.xLocation, this.yLocation);
  }

  @Override
  public String toString() {
    return "SnakeNode{" +
        "snakeImage=" + snakeImage +
        ", xLocation=" + xLocation +
        ", yLocation=" + yLocation +
        ", snakeIcon=" + snakeIcon +
        '}';
  }
}
