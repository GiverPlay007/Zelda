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

  default void draw(Graphics graphics, int x, int y, boolean rightFaced) {
    draw(graphics, x, y, getWidth(), getHeight(), rightFaced);
  }

  default void draw(Graphics graphics, int x, int y, int width, int height, boolean rightFaced) {
    draw(graphics, rightFaced ? x : x + getWidth(), y, rightFaced ? width : -width, height);
  }

  BufferedImage getSprite();

  int getWidth();

  int getHeight();
}
