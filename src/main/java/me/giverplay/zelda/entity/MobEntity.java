package me.giverplay.zelda.entity;

import java.awt.image.BufferedImage;

public class MobEntity extends Entity {

  protected float lastX;
  protected float lastY;

  protected float speed = 1.0f;

  protected boolean movingRight;
  protected boolean movingLeft;
  protected boolean movingUp;
  protected boolean movingDown;

  protected boolean isMoving;

  public MobEntity(float x, float y) {
    super(x, y);
  }

  public MobEntity(float x, float y, BufferedImage sprite) {
    super(x, y, sprite);
  }

  public MobEntity(float x, float y, int width, int height, BufferedImage sprite) {
    super(x, y, width, height, sprite);
  }

  @Override
  public void tick() {
    isMoving = false;

    float mX = 0;
    float mY = 0;

    if(movingLeft) {
      rightSided = false;
      mX -= speed;
    }

    if(movingRight) {
      rightSided = true;
      mX += speed;
    }

    if(movingUp) {
      mY -= speed;
    }

    if(movingDown) {
      mY += speed;
    }

    if(mX != 0 && game.getWorld().isTileFree((int) (x + mX), getIntY())) {
      moveX(mX);
      isMoving = true;
    }

    if(mY != 0 && game.getWorld().isTileFree(getIntX(), (int) (y + mY))) {
      moveY(mY);
      isMoving = true;
    }
  }

  public void moveX(float mX) {
    lastX = x;
    x += mX;
  }

  public void moveY(float mY) {
    lastY = y;
    y += mY;
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

  public float getLastX() {
    return lastX;
  }

  public float getLastY() {
    return lastY;
  }

  public int getIntLastX() {
    return (int) lastX;
  }

  public int getIntLastY() {
    return (int) lastY;
  }

  public float getSpeed() {
    return speed;
  }

  public void setSpeed(float speed) {
    this.speed = speed;
  }
}
