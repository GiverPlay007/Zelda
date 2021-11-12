package me.giverplay.zelda.entity;

import me.giverplay.zelda.Game;
import me.giverplay.zelda.graphics.Spritesheet;
import me.giverplay.zelda.utils.Rectangle;
import me.giverplay.zelda.world.Camera;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.function.Consumer;

import static me.giverplay.zelda.Game.TILE_SIZE;

public class Entity {

  protected static final Game game = Game.getGame();

  protected static final BufferedImage[] PLAYER_SPRITES;

  protected static final BufferedImage ENEMY_SPRITE;

  protected float x;
  protected float y;

  protected int width;
  protected int height;

  protected boolean rightSided = true;
  protected boolean isEntityCollider = true;

  protected BufferedImage sprite;

  public Entity(float x, float y) {
    this(x, y, null);
  }

  public Entity(float x, float y, BufferedImage sprite) {
    this(x, y, TILE_SIZE, TILE_SIZE, sprite);
  }

  public Entity(float x, float y, int width, int height, BufferedImage sprite) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.sprite = sprite;
  }

  public void tick() {

  }

  public void render(Graphics graphics, Camera camera) {
    if(sprite != null) {
      graphics.drawImage(sprite, camera.offsetX(rightSided ? getIntX() : getIntX() + TILE_SIZE),
        camera.offsetY(getIntY()),
        rightSided ? TILE_SIZE : -TILE_SIZE,
        TILE_SIZE,
        null);
    }
  }

  public boolean isRemoved() {
    return !game.hasEntity(this);
  }

  public boolean isCollidingEntity(Entity other) {
    return getBox().overlaps(other.getBox());
  }

  public boolean isCollidingEntity(float x, float y) {
    Rectangle rect = new Rectangle(x, y, width, height);

    for (Entity entity : game.getEntities()) {
      if (isEntityCollider && entity != this && rect.overlaps(entity.getBox())) return true;
    }

    return false;
  }

  public Rectangle getBox() {
    return new Rectangle(x, y, width, height);
  }

  public void whenExists(Consumer<Entity> action) {
    if(!isRemoved()) action.accept(this);
  }

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }

  public int getIntX() {
    return (int) x;
  }

  public int getIntY() {
    return (int) y;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public void setX(float x) {
    this.x = x;
  }

  public void setY(float y) {
    this.y = y;
  }

  public void setTileX(int x) {
    this.x = x * TILE_SIZE;
  }

  public void setTileY(int y) {
    this.y = y * TILE_SIZE;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  static {
    Spritesheet spritesheet = game.getSpritesheet();

    PLAYER_SPRITES = new BufferedImage[4];

    for(int index = 0; index < 4; index++) {
      PLAYER_SPRITES[index] = spritesheet.getSprite(TILE_SIZE * index, 0, TILE_SIZE, TILE_SIZE);
    }

    ENEMY_SPRITE = spritesheet.getSprite(0, 2 * TILE_SIZE, TILE_SIZE, TILE_SIZE);
  }
}
