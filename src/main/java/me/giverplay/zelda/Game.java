package me.giverplay.zelda;

public class Game implements Runnable {

  private Thread thread;
  private boolean isRunning;

  public void tick() {

  }

  public void render() {

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

  public static void main(String[] args) {
    new Game().start();
  }
}
