package me.giverplay.zelda.graphics;

import java.awt.image.BufferedImage;

public class AnimatedSprite implements Sprite {

  private final BufferedImage[] sprites;

  private boolean isPlaying = true;

  private int frames;
  private int currentFrames;
  private int index;

  public AnimatedSprite(BufferedImage[] sprites, int frames) {
    if(sprites.length == 0) throw new IllegalArgumentException("Empty sprite array!");

    this.sprites = sprites;
    this.frames = frames;
  }

  @Override
  public BufferedImage getSprite() {
    if(isPlaying) {
      ++currentFrames;

      if(currentFrames >= frames) {
        currentFrames = 0;

        ++index;

        if(index >= sprites.length) {
          index = 0;
        }
      }
    }

    return sprites[index];
  }

  @Override
  public int getWidth() {
    return sprites[index].getWidth();
  }

  @Override
  public int getHeight() {
    return sprites[index].getHeight();
  }

  public int getFrames() {
    return frames;
  }

  public void setFrames(int frames) {
    this.frames = frames;
  }

  public int getIndex() {
    return index;
  }

  public void setPlaying(boolean playing) {
    isPlaying = playing;
  }
}
