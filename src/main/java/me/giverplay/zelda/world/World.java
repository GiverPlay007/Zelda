package me.giverplay.zelda.world;

import java.awt.Graphics;

public class World {

  private final String name;
  private final int[] tiles;

  private final int width;
  private final int height;

  public World(String name, int width, int height) {
    this.name = name;
    this.width = width;
    this.height = height;

    this.tiles = new int[width * height];
  }

  public void render(Graphics graphics) {

  }

  public Tile getTile(int x, int y) {
    if(!isInsideWorld(x, y)) {
      return Tile.getById(0);
    }

    return Tile.getById(tiles[x + y * width]);
  }

  public void setTile(int x, int y, Tile tile) {
    tiles[x + y * width] = tile.getId();
  }

  public boolean isInsideWorld(int x, int y) {
    return x >= 0 && x < width && y >= 0 && y < height;
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
