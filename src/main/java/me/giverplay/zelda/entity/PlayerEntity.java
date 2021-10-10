package me.giverplay.zelda.entity;

import me.giverplay.zelda.Game;

public class PlayerEntity extends Entity {
  public PlayerEntity(Game game, int x, int y) {
    super(game, x, y, game.getSpritesheet().getSprite(0, 0, SIZE, SIZE));
  }
}
