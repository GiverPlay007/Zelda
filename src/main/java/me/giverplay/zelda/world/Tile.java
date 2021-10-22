package me.giverplay.zelda.world;

import me.giverplay.zelda.world.tiles.AirTile;
import me.giverplay.zelda.world.tiles.GrassTile;
import me.giverplay.zelda.world.tiles.StoneTile;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import static me.giverplay.zelda.Game.TILE_SIZE;

public abstract class Tile {

  private static final Tile[] tiles = new Tile[Byte.MAX_VALUE];

  public static final Tile air = new AirTile();
  public static final Tile stone = new StoneTile();
  public static final Tile grass = new GrassTile();

  public final byte id;
  protected BufferedImage sprite;

  public Tile(byte id, BufferedImage sprite) {
    this.id = id;
    this.sprite = sprite;
    tiles[id] = this;
  }

  public Tile(byte id) {
    this(id, null);
  }

  public void render(Graphics graphics, int x, int y) {
    graphics.drawImage(this.sprite, x, y, TILE_SIZE, TILE_SIZE, null);
  }

  public static Tile getById(byte id) {
    return tiles[id];
  }

  public boolean canPassThru() {
    return true;
  }
}
