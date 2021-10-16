package me.giverplay.zelda.world.tiles;

import me.giverplay.zelda.Game;
import me.giverplay.zelda.world.Tile;

public class GrassTile extends Tile {
  public GrassTile() {
    super((byte) 2, Game.getGame().getSpritesheet().getSprite(SIZE, SIZE, SIZE, SIZE));
  }
}
