package me.giverplay.zelda.utils;

public class MathUtils {
  private MathUtils() { }

  public static int clamp(int number, int min, int max) {
    if(number < min) number = min;
    if(number > max) number = max;

    return number;
  }
}
