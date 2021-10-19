package me.giverplay.zelda.world;

import me.giverplay.zelda.Game;
import me.giverplay.zelda.entity.EnemyEntity;
import me.giverplay.zelda.entity.Entity;

import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static me.giverplay.zelda.Game.SCREEN_HEIGHT;
import static me.giverplay.zelda.Game.SCREEN_WIDTH;
import static me.giverplay.zelda.Game.TILE_SIZE;

public class World {

  private static final Game game = Game.getGame();

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

        case 0xFF0026FF -> moveEntityToIndex(game.getPlayer(), index);
        case 0xFFFF0000 -> addIndexEntity(new EnemyEntity(), index);

        default -> tiles[index] = Tile.air.id;
      }
    }
  }

  private void addIndexEntity(Entity entity, int index) {
    game.addEntity(moveEntityToIndex(entity, index));
  }

  private Entity moveEntityToIndex(Entity entity, int index) {
    entity.setTileX(index % width);
    entity.setTileY(index / width);
    tiles[index] = Tile.grass.id;

    return entity;
  }

  public void render(Graphics graphics, Camera camera) {
    int xs = camera.getX() / TILE_SIZE;
    int ys = camera.getY() / TILE_SIZE;

    int xf = xs + SCREEN_WIDTH / TILE_SIZE +1;
    int yf = ys + SCREEN_HEIGHT / TILE_SIZE +1;

    for(int x = xs; x <= xf; x++) {
      for(int y = ys; y <= yf; y++) {
        Tile tile = getTile(x, y);
        tile.render(graphics, camera.offsetX(x * TILE_SIZE), camera.offsetY(y * TILE_SIZE));
      }
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

  public int getTileWidth() {
    return width * TILE_SIZE;
  }

  public int getTileHeight() {
    return height * TILE_SIZE;
  }
}
