package me.giverplay.zelda.world;

import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class World {

  private final String name;
  private final byte[] tiles;

  private final int width;
  private final int height;

  public World(String name, int width, int height) {
    this.name = name;
    this.width = width;
    this.height = height;

    this.tiles = new byte[width * height];

    load();
  }

  private void load() {
    BufferedImage image;

    try {
      image = ImageIO.read(getClass().getResourceAsStream("/worlds/" + name + ".png"));
    } catch (IOException e) {
      e.printStackTrace();
      return;
    }

    int[] pixels = new int[width * height];
    image.getRGB(0, 0, width, height, pixels, 0, width);

    for (int index = 0; index < pixels.length; index++) {
      switch (pixels[index]) {
        case 0xFFFFFFFF -> tiles[index] = Tile.grass.id;
        case 0xFF000000 -> tiles[index] = Tile.stone.id;

        default -> tiles[index] = Tile.air.id;
      }
    }
  }

  public void render(Graphics graphics) {
    for (int index = 0; index < tiles.length; index++) {
      Tile tile = Tile.getById(tiles[index]);
      tile.render(graphics, index % width * Tile.SIZE, index / width * Tile.SIZE);
    }
  }

  public Tile getTile(int x, int y) {
    if(!isInsideWorld(x, y)) {
      return Tile.air;
    }

    return Tile.getById(tiles[x + y * width]);
  }

  public void setTile(int x, int y, Tile tile) {
    tiles[x + y * width] = tile.id;
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
