package me.giverplay.zelda.world.tiles;

import me.giverplay.zelda.Game;
import me.giverplay.zelda.world.Tile;

public class StoneTile extends Tile {
  public StoneTile() {
    super((byte) 1, Game.getGame().getSpritesheet().getSprite(0, SIZE, SIZE, SIZE));
  }
}
