package me.giverplay.zelda.entity;

public class EnemyEntity extends MobEntity {
  public EnemyEntity(int x, int y) {
    super(x, y, ENEMY_SPRITE);
  }

  public EnemyEntity() {
    this(0, 0);
  }
}
