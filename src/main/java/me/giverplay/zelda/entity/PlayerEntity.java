package me.giverplay.zelda.entity;

import me.giverplay.zelda.Game;

public class PlayerEntity extends Entity {

  private boolean movingRight;
  private boolean movingLeft;
  private boolean movingUp;
  private boolean movingDown;

  public PlayerEntity(Game game, int x, int y) {
    super(game, x, y, game.getSpritesheet().getSprite(0, 0, SIZE, SIZE));
  }

  @Override
  public void tick() {
    if(movingRight) ++x;
    if(movingLeft) --x;
    if(movingUp) --y;
    if(movingDown) ++y;
  }

  public boolean isMovingRight() {
    return movingRight;
  }

  public void setMovingRight(boolean movingRight) {
    this.movingRight = movingRight;
  }

  public boolean isMovingLeft() {
    return movingLeft;
  }

  public void setMovingLeft(boolean movingLeft) {
    this.movingLeft = movingLeft;
  }

  public boolean isMovingUp() {
    return movingUp;
  }

  public void setMovingUp(boolean movingUp) {
    this.movingUp = movingUp;
  }

  public boolean isMovingDown() {
    return movingDown;
  }

  public void setMovingDown(boolean movingDown) {
    this.movingDown = movingDown;
  }
}
