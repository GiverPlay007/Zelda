package me.giverplay.zelda.graphics;

import java.awt.image.BufferedImage;

public class StaticSprite implements Sprite {

  private final BufferedImage sprite;

  public StaticSprite(BufferedImage sprite) {
    this.sprite = sprite;
  }

  @Override
  public BufferedImage getSprite() {
    return sprite;
  }

  @Override
  public int getWidth() {
    return sprite.getWidth();
  }

  @Override
  public int getHeight() {
    return sprite.getHeight();
  }
}
