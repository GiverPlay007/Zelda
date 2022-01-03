package me.giverplay.zelda.entity;

import me.giverplay.zelda.graphics.Sprite;

public class LivingEntity extends MobEntity implements Damageable {

  private int life;
  private int maxLife;

  public LivingEntity(float x, float y, int maxLife, Sprite sprite) {
    this(x, y, maxLife, maxLife, sprite);
  }

  public LivingEntity(float x, float y, int maxLife, int width, int height, Sprite sprite) {
    this(x, y, width, height, maxLife, maxLife, sprite);
  }

  public LivingEntity(float x, float y, int life, int maxLife) {
    super(x, y);
    this.life = life;
    this.maxLife = maxLife;
  }

  public LivingEntity(float x, float y, int life, int maxLife, Sprite sprite) {
    super(x, y, sprite);
    this.life = life;
    this.maxLife = maxLife;
  }

  public LivingEntity(float x, float y, int width, int height, int life, int maxLife, Sprite sprite) {
    super(x, y, width, height, sprite);
    this.life = life;
    this.maxLife = maxLife;
  }

  @Override
  public void damage(int damage) {
    this.life += damage;
  }

  public boolean isAlive() {
    return life > 0;
  }

  public boolean isDead() {
    return life <= 0;
  }

  public int getLife() {
    return life;
  }

  public void setLife(int life) {
    this.life = life;
  }

  public int getMaxLife() {
    return maxLife;
  }

  public void setMaxLife(int maxLife) {
    this.maxLife = maxLife;
  }
}
