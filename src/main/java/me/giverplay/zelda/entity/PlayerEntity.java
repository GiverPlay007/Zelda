package me.giverplay.zelda.entity;

import me.giverplay.zelda.Game;
import me.giverplay.zelda.utils.MathUtils;
import me.giverplay.zelda.world.Camera;
import me.giverplay.zelda.world.Tile;
import me.giverplay.zelda.world.World;

import java.awt.Graphics;

import static me.giverplay.zelda.Game.SCREEN_HEIGHT;
import static me.giverplay.zelda.Game.SCREEN_WIDTH;

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
    World world = game.getWorld();

    camera.setX(MathUtils.clamp(getIntX() - SCREEN_WIDTH / 2, 0, world.getWidth() * Tile.SIZE - SCREEN_WIDTH));
    camera.setY(MathUtils.clamp(getIntY() - SCREEN_HEIGHT / 2, 0, world.getHeight() * Tile.SIZE - SCREEN_HEIGHT));
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
