package me.giverplay.zelda.entity;

import me.giverplay.zelda.graphics.AnimatedSprite;
import me.giverplay.zelda.world.Camera;
import me.giverplay.zelda.world.World;

import java.awt.Graphics;

import static me.giverplay.zelda.Game.SCREEN_HEIGHT;
import static me.giverplay.zelda.Game.SCREEN_WIDTH;
import static me.giverplay.zelda.Game.TILE_SIZE;
import static me.giverplay.zelda.utils.MathUtils.clamp;

public class PlayerEntity extends LivingEntity {

  private static final AnimatedSprite SPRITE =
    (AnimatedSprite) game.getSpritesheet().getSprite(0, 0, TILE_SIZE, TILE_SIZE, 4, 15);

  public PlayerEntity(int x, int y) {
    super(x, y, 100, SPRITE);

    speed = 1.5f;
    isEntityCollider = false;
  }

  @Override
  public void tick() {
    super.tick();
    moveCamera(game.getCamera());
  }

  @Override
  public void render(Graphics graphics, Camera camera) {
    ((AnimatedSprite) sprite).setPlaying(isMoving);
    sprite.draw(graphics, camera.offsetX(getIntX()), camera.offsetY(getIntY()), rightSided);
  }

  public void moveCamera(Camera camera) {
    World world = game.getWorld();

    camera.setX(clamp(getIntX() - SCREEN_WIDTH / 2, 0, world.getTileWidth() - SCREEN_WIDTH));
    camera.setY(clamp(getIntY() - SCREEN_HEIGHT / 2, 0, world.getTileHeight() - SCREEN_HEIGHT));
  }
}
