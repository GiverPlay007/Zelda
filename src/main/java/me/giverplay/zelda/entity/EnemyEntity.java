package me.giverplay.zelda.entity;

public class EnemyEntity extends MobEntity {
  public EnemyEntity(int x, int y) {
    super(x, y, ENEMY_SPRITE);
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
}
