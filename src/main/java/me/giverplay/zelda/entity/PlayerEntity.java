package me.giverplay.zelda.entity;

import me.giverplay.zelda.world.Camera;
import me.giverplay.zelda.world.World;

import java.awt.Graphics;

import static me.giverplay.zelda.Game.SCREEN_HEIGHT;
import static me.giverplay.zelda.Game.SCREEN_WIDTH;
import static me.giverplay.zelda.Game.TILE_SIZE;

import static me.giverplay.zelda.utils.MathUtils.clamp;

public class PlayerEntity extends MobEntity {

  private short currentSprite;
  private short currentFrame;

  public PlayerEntity(int x, int y) {
    super(x, y);
  }

  @Override
  public void tick() {
    super.tick();
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
}
