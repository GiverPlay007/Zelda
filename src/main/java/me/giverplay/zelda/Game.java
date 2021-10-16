package me.giverplay.zelda;

import me.giverplay.zelda.entity.Entity;
import me.giverplay.zelda.entity.PlayerEntity;
import me.giverplay.zelda.graphics.Spritesheet;
import me.giverplay.zelda.world.World;

import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Game extends Canvas implements Runnable, KeyListener {

  public static final int SCREEN_WIDTH = 340;
  public static final int SCREEN_HEIGHT = 220;
  public static final int SCREEN_SCALE = 3;

  private static Game game;

  private final List<Entity> entities = new ArrayList<>();

  private BufferedImage layer;
  private Thread thread;

  private Spritesheet spritesheet;
  private PlayerEntity player;
  private World world;

  private boolean isRunning;

  private Game() {
    Game.game = this;

    setPreferredSize(new Dimension(getScaledWidth(), getScaledHeight()));

    loadAssets();
    loadWorld();
    createWindow();
    registerListeners();
  }

  private void loadAssets() {
    layer = new BufferedImage(SCREEN_WIDTH, SCREEN_HEIGHT, BufferedImage.TYPE_INT_RGB);
    spritesheet = new Spritesheet("/Spritesheet.png");
  }

  private void loadWorld() {
    entities.clear();

    player = new PlayerEntity(0, 0);
    entities.add(player);

    world = new World("Origin", 30, 20);
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

  private void registerListeners() {
    addKeyListener(this);
    requestFocus();
  }

  public void tick() {
    getEntities().forEach(entity -> entity.whenExists(Entity::tick));
  }

  public void render() {
    final Graphics graphics = layer.getGraphics();
    graphics.setColor(Color.GRAY);
    graphics.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

    world.render(graphics);
    entities.forEach(entity -> entity.render(graphics));

    graphics.dispose();

    Graphics bsGraphics = getBufferStrategy().getDrawGraphics();
    bsGraphics.drawImage(layer, 0, 0, getScaledWidth(), getScaledHeight(), null);
    bsGraphics.dispose();

    getBufferStrategy().show();
  }

  public synchronized void start() {
    isRunning = true;
    thread = new Thread(this, "Game Loop");
    thread.start();
  }

  public synchronized void stop() {
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

  private void switchKey(int key, boolean pressed) {
    switch (key) {
      case KeyEvent.VK_RIGHT, KeyEvent.VK_D -> player.setMovingRight(pressed);
      case KeyEvent.VK_LEFT,  KeyEvent.VK_A -> player.setMovingLeft(pressed);
      case KeyEvent.VK_UP,    KeyEvent.VK_W -> player.setMovingUp(pressed);
      case KeyEvent.VK_DOWN,  KeyEvent.VK_S -> player.setMovingDown(pressed);
    }
  }

  @Override
  public void keyPressed(KeyEvent e) {
    switchKey(e.getKeyCode(), true);
  }

  @Override
  public void keyReleased(KeyEvent e) {
    switchKey(e.getKeyCode(), false);
  }

  @Override
  public void keyTyped(KeyEvent e) {

  }

  public Spritesheet getSpritesheet() {
    return spritesheet;
  }

  public List<Entity> getEntities() {
    return new ArrayList<>(entities);
  }

  public boolean hasEntity(Entity entity) {
    return entities.contains(entity);
  }

  public void addEntity(Entity entity) {
    entities.add(entity);
  }

  public void removeEntity(Entity entity) {
    entities.remove(entity);
  }

  public World getWorld() {
    return world;
  }

  public PlayerEntity getPlayer() {
    return player;
  }

  public static void main(String[] args) {
    new Game().start();
  }

  public static Game getGame() {
    return Game.game;
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
