package me.giverplay.zelda.entity;

import me.giverplay.zelda.Game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.function.Consumer;

public class Entity {
  public static final int SIZE = 16;

  protected final Game game;

  protected float x;
  protected float y;
  protected int width;
  protected int height;

  protected BufferedImage sprite;

  public Entity(Game game, float x, float y) {
    this(game, x, y, null);
  }

  public Entity(Game game, float x, float y, BufferedImage sprite) {
    this(game, x, y, SIZE, SIZE, sprite);
  }

  public Entity(Game game, float x, float y, int width, int height, BufferedImage sprite) {
    this.game = game;
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

  public boolean isRemoved() {
    return !game.hasEntity(this);
  }

  public void whenExists(Consumer<Entity> action) {
    if(!isRemoved()) action.accept(this);
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
