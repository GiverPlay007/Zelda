package me.giverplay.zelda.world;

public class Camera {
  private int x;
  private int y;

  public Camera() {
    this(0, 0);
  }

  public Camera(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int offsetX(int x) {
    return x - this.x;
  }

  public int offsetY(int y) {
    return y - this.y;
  }

  public void setX(int x) {
    this.x = x;
  }

  public void setY(int y) {
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }
}
