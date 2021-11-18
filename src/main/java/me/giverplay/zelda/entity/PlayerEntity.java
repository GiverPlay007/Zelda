package me.giverplay.zelda.entity;

import me.giverplay.zelda.graphics.AnimatedSprite;
import me.giverplay.zelda.world.Camera;
import me.giverplay.zelda.world.World;

import java.awt.Graphics;

import static me.giverplay.zelda.Game.SCREEN_HEIGHT;
import static me.giverplay.zelda.Game.SCREEN_WIDTH;
import static me.giverplay.zelda.utils.MathUtils.clamp;

public class PlayerEntity extends MobEntity {

  public PlayerEntity(int x, int y) {
    super(x, y, PLAYER_SPRITE);

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
