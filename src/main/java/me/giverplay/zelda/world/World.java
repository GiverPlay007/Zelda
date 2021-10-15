package me.giverplay.zelda.world;

import java.awt.Graphics;

public class World {

  private final String name;

  private final int width;
  private final int height;

  public World(String name, int width, int height) {
    this.name = name;
    this.width = width;
    this.height = height;
  }

  public void render(Graphics graphics) {

  }

  public String getName() {
    return name;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }
}
