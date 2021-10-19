package me.giverplay.zelda.world.tiles;

import me.giverplay.zelda.Game;
import me.giverplay.zelda.world.Tile;

import static me.giverplay.zelda.Game.TILE_SIZE;

public class GrassTile extends Tile {
  public GrassTile() {
    super((byte) 2, Game.getGame().getSpritesheet().getSprite(TILE_SIZE, TILE_SIZE, TILE_SIZE, TILE_SIZE));
  }
}
