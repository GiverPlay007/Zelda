package me.giverplay.zelda.world.tiles;

import me.giverplay.zelda.Game;
import me.giverplay.zelda.world.Tile;

import static me.giverplay.zelda.Game.TILE_SIZE;

public class StoneTile extends Tile {
  public StoneTile() {
    super((byte) 1, Game.getGame().getSpritesheet().getSprite(0, TILE_SIZE, TILE_SIZE, TILE_SIZE));
  }

  @Override
  public boolean canPassThru() {
    return false;
  }
}
