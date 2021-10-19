package me.giverplay.zelda.entity;

import me.giverplay.zelda.Game;
import me.giverplay.zelda.world.Camera;

import java.awt.Graphics;

public class PlayerEntity extends Entity {

  private boolean movingRight;
  private boolean movingLeft;
  private boolean movingUp;
  private boolean movingDown;

  private boolean isMoving;
  private boolean rightSided = true;

  private short currentSprite;
  private short currentFrame;

  public PlayerEntity(int x, int y) {
    super(x, y);
  }

  @Override
  public void tick() {
    isMoving = false;

    if(movingLeft) {
      isMoving = true;
      rightSided = false;
      --x;
    }

    if(movingRight) {
      isMoving = true;
      rightSided = true;
      ++x;
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

    moveCamera(game.getCamera());
  }

  @Override
  public void render(Graphics graphics, Camera camera) {
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

    graphics.drawImage(PLAYER_SPRITES[currentSprite],
      camera.offsetX(rightSided ? getIntX() : getIntX() + SIZE),
      camera.offsetY(getIntY()),
      rightSided ? SIZE : -SIZE,
      SIZE,
      null);
  }

  public void moveCamera(Camera camera) {
    camera.setX(getIntX() - Game.SCREEN_WIDTH / 2);
    camera.setY(getIntY() - Game.SCREEN_HEIGHT / 2);
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
