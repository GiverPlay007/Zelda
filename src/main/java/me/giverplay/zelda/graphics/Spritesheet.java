package me.giverplay.zelda.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Spritesheet {
  private final BufferedImage sprites;

  public Spritesheet(String path) {
    InputStream spriteResource = getClass().getResourceAsStream(path);

    if(spriteResource == null) throw new IllegalArgumentException("Resource " + path + " does not exists");

    try {
      sprites = ImageIO.read(spriteResource);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public BufferedImage getSprite(int x, int y, int width, int height) {
    return sprites.getSubimage(x, y, width, height);
  }
}
