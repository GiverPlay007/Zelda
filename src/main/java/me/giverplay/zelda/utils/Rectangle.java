package me.giverplay.zelda.utils;

public class Rectangle {

  public float x;
  public float y;

  public float width;
  public float height;

  public Rectangle(float width, float height) {
    this(0, 0, width, height);
  }

  public Rectangle(float x, float y, float width, float height) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }

  public boolean overlaps(Rectangle other) {
    // java.awt.Rectangle#intersects
    float tw = this.width;
    float th = this.height;
    float rw = other.width;
    float rh = other.height;

    if (rw <= 0 || rh <= 0 || tw <= 0 || th <= 0) {
      return false;
    }

    float tx = this.x;
    float ty = this.y;
    float rx = other.x;
    float ry = other.y;

    rw += rx;
    rh += ry;
    tw += tx;
    th += ty;

    return ((rw < rx || rw > tx) &&
      (rh < ry || rh > ty) &&
      (tw < tx || tw > rx) &&
      (th < ty || th > ry));
  }
}
