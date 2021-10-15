package me.giverplay.zelda.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class Tile {
  public static final int SIZE = 16;

  private static final HashMap<Integer, Tile> tiles = new HashMap<>();

  private final BufferedImage sprite;
  private final int id;

  public Tile(int id, BufferedImage sprite) {
    this.id = id;
    this.sprite = sprite;
  }

  public void render(Graphics graphics, int x, int y) {
    graphics.drawImage(sprite, x, y, SIZE, SIZE, null);
  }

  public int getId() {
    return id;
  }

  public static Tile getById(int id) {
    return tiles.get(id);
  }
}
