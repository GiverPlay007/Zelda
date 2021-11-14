package me.giverplay.zelda.graphics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public interface Sprite {

  default void draw(Graphics graphics, int x, int y) {
    draw(graphics, x, y, getWidth(), getHeight());
  }

  default void draw(Graphics graphics, int x, int y, int width, int height) {
    graphics.drawImage(getSprite(), x, y, width, height, null);
  }

  BufferedImage getSprite();

  int getWidth();

  int getHeight();
}
