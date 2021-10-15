package me.giverplay.zelda.entity;

import java.awt.Graphics;

public class PlayerEntity extends Entity {

  private boolean movingRight;
  private boolean movingLeft;
  private boolean movingUp;
  private boolean movingDown;

  private boolean isMoving;

  private short currentSprite;
  private short currentFrame;
  private byte direction;

  public PlayerEntity(int x, int y) {
    super(x, y);
  }

  @Override
  public void tick() {
    isMoving = false;

    if(movingRight) {
      isMoving = true;
      direction = 1;
      ++x;
    }

    if(movingLeft) {
      isMoving = true;
      direction = 0;
      --x;
    }

    if(movingUp) {
      isMoving = true;
      --y;
    }

    if(movingDown) {
      isMoving = true;
      ++y;
    }

    if (movingRight && movingLeft && !movingDown && !movingUp || movingUp && movingDown && !movingLeft && !movingRight) {
      isMoving = false;
    }
  }

  @Override
  public void render(Graphics graphics) {
    if(isMoving) {
      ++currentFrame;

      if(currentFrame > 15) {
        currentFrame = 0;

        ++currentSprite;

        if(currentSprite > 3) {
          currentSprite = 0;
        }
      }
    }

    graphics.drawImage(PLAYER_SPRITES[currentSprite], getIntX(), getIntY(), SIZE, SIZE, null);
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

  public boolean isMoving() {
    return isMoving;
  }
}
