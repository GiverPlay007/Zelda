package me.giverplay.zelda.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Entity {
  public static final int SIZE = 16;

  protected float x;
  protected float y;
  protected int width;
  protected int height;

  protected BufferedImage sprite;

  public Entity(float x, float y) {
    this(x, y, null);
  }

  public Entity(float x, float y, BufferedImage sprite) {
    this(x, y, SIZE, SIZE, sprite);
  }

  public Entity(float x, float y, int width, int height, BufferedImage sprite) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.sprite = sprite;
  }

  public void tick() {

  }

  public void render(Graphics graphics) {
    if(sprite != null) {
      graphics.drawImage(sprite, getIntX(), getIntY(), width, height, null);
    }
  }

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }

  public int getIntX() {
    return (int) x;
  }

  public int getIntY() {
    return (int) y;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public void setX(float x) {
    this.x = x;
  }

  public void setY(float y) {
    this.y = y;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public void setHeight(int height) {
    this.height = height;
  }
}
