package me.giverplay.zelda.entity;

import me.giverplay.zelda.world.Camera;
import me.giverplay.zelda.world.World;

import java.awt.Graphics;

import static me.giverplay.zelda.Game.SCREEN_HEIGHT;
import static me.giverplay.zelda.Game.SCREEN_WIDTH;
import static me.giverplay.zelda.Game.TILE_SIZE;

import static me.giverplay.zelda.utils.MathUtils.clamp;

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

    int mX = 0;
    int mY = 0;

    if(movingLeft) {
      rightSided = false;
      --mX;
    }

    if(movingRight) {
      rightSided = true;
      ++mX;
    }

    if(movingUp) {
      --mY;
    }

    if(movingDown) {
      ++mY;
    }

    if(mX != 0 && game.getWorld().isTileFree(getIntX() + mX, getIntY())) {
      lastX = x;
      x += mX;
      isMoving = true;
    }

    if(mY != 0 && game.getWorld().isTileFree(getIntX(), getIntY() + mY)) {
      lastY = y;
      y += mY;
      isMoving = true;
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
      camera.offsetX(rightSided ? getIntX() : getIntX() + TILE_SIZE),
      camera.offsetY(getIntY()),
      rightSided ? TILE_SIZE : -TILE_SIZE,
      TILE_SIZE,
      null);
  }

  public void moveCamera(Camera camera) {
    World world = game.getWorld();

    camera.setX(clamp(getIntX() - SCREEN_WIDTH / 2, 0, world.getTileWidth() - SCREEN_WIDTH));
    camera.setY(clamp(getIntY() - SCREEN_HEIGHT / 2, 0, world.getTileHeight() - SCREEN_HEIGHT));
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
