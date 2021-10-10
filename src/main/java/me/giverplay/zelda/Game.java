package me.giverplay.zelda;

import me.giverplay.zelda.entity.Entity;
import me.giverplay.zelda.graphics.Spritesheet;

import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Game extends Canvas implements Runnable {

  public static final int SCREEN_WIDTH = 340;
  public static final int SCREEN_HEIGHT = 220;
  public static final int SCREEN_SCALE = 3;

  private Thread thread;
  private boolean isRunning;

  private final List<Entity> entities = new ArrayList<>();
  private final BufferedImage layer;

  private final Spritesheet spritesheet;

  private Game() {
    setPreferredSize(new Dimension(getScaledWidth(), getScaledHeight()));

    layer = new BufferedImage(SCREEN_WIDTH, SCREEN_HEIGHT, BufferedImage.TYPE_INT_RGB);
    spritesheet = new Spritesheet("/Spritesheet.png");

    entities.clear();

    createWindow();
  }

  private void createWindow() {
    JFrame frame = new JFrame("Game 01 - Zelda");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);
    frame.add(this);
    frame.pack();
    frame.setLocationRelativeTo(null);

    createBufferStrategy(3);
    frame.setVisible(true);
  }

  public void tick() {
    getEntities().forEach(Entity::tick);
  }

  public void render() {
    final Graphics graphics = layer.getGraphics();
    graphics.setColor(Color.GRAY);
    graphics.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

    entities.forEach(entity -> entity.render(graphics));

    graphics.dispose();

    Graphics bsGraphics = getBufferStrategy().getDrawGraphics();
    bsGraphics.drawImage(layer, 0, 0, getScaledWidth(), getScaledHeight(), null);
    bsGraphics.dispose();

    getBufferStrategy().show();
  }

  public void start() {
    isRunning = true;
    thread = new Thread(this, "Game Loop");
    thread.start();
  }

  public void stop() {
    // Avoid death lock error
    new Thread(this::stop0, "Killer thread").start();
  }

  private void stop0() {
    isRunning = false;

    try {
      thread.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    thread = null;
  }

  @Override
  public void run() {
    long timer = System.currentTimeMillis();
    long lastTime = System.nanoTime();
    long now;

    double nsPerTick = 1_000_000_000 / 60.0D;
    double unprocessed = 0.0D;

    int currentFps = 0;

    while(isRunning) {
      now = System.nanoTime();
      unprocessed += (now - lastTime) / nsPerTick;
      lastTime = now;

      while(unprocessed >= 1) {
        tick();
        render();

        ++currentFps;
        --unprocessed;
      }

      if(System.currentTimeMillis() - timer >= 1000) {
        System.out.println("FPS: " + currentFps);
        timer += 1000;
        currentFps = 0;
      }

      // Reduce CPU usage
      try {
        Thread.sleep(2);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    System.out.println("Good bye!");
  }

  public Spritesheet getSpritesheet() {
    return spritesheet;
  }

  public List<Entity> getEntities() {
    return new ArrayList<>(entities);
  }

  public void addEntity(Entity entity) {
    entities.add(entity);
  }

  public void removeEntity(Entity entity) {
    entities.remove(entity);
  }

  public static void main(String[] args) {
    new Game().start();
  }

  public static int getScaledWidth() {
    return SCREEN_WIDTH * SCREEN_SCALE;
  }

  public static int getScaledHeight() {
    return SCREEN_HEIGHT * SCREEN_SCALE;
  }

  public static int getScreenScale() {
    return SCREEN_SCALE;
  }
}
