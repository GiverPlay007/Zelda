package me.giverplay.zelda.entity;

import me.giverplay.zelda.graphics.AnimatedSprite;
import me.giverplay.zelda.world.Camera;

import java.awt.Graphics;

import static me.giverplay.zelda.Game.TILE_SIZE;

public class EnemyEntity extends MobEntity {
  public EnemyEntity(int x, int y) {
    super(x, y, game.getSpritesheet().getSprite(0, TILE_SIZE * 2, TILE_SIZE, TILE_SIZE, 8, 8));
  }

  public EnemyEntity() {
    this(0, 0);
  }

  @Override
  public void tick() {
    PlayerEntity player = game.getPlayer();

    movingRight = x < player.x;
    movingLeft = x > player.x;
    movingUp = y > player.y;
    movingDown = y < player.y;

    super.tick();
  }

  @Override
  public void render(Graphics graphics, Camera camera) {
    ((AnimatedSprite) sprite).setPlaying(isMoving);
    sprite.draw(graphics, camera.offsetX(getIntX()), camera.offsetY(getIntY()), rightSided);
  }
}
