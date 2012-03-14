package com.outbrain.selenium.util;

import java.util.concurrent.TimeUnit;

public class ThreadUtils {
  public static interface WaitCondition {
    public boolean checkCondition(long elapsedTimeInMs);
  }

  public static void sleep(final TimeUnit timeUnit, final long duration) {
    try {
      timeUnit.sleep(duration);
    } catch (final InterruptedException ex) {
      Thread.currentThread().interrupt();
    }
  }

  public static boolean waitFor(final WaitCondition condition) {
    boolean result = false;
    if (condition != null) {
      final long startTime = System.currentTimeMillis();

      while (!(result = condition.checkCondition(System.currentTimeMillis() - startTime))) {
        try {
          Thread.sleep(100);
        } catch (final InterruptedException ex) {
          Thread.currentThread().interrupt();
        }
      }
    }

    return result;
  }

  public static boolean waitFor(final WaitCondition condition, final TimeUnit timeUnit, final long timeoutDuration) {
    final long timeout = timeUnit.toMillis(timeoutDuration);

    boolean result = false;
    if (condition != null) {
      final long startTime = System.currentTimeMillis();
      long curTime = startTime;

      while (!(result = condition.checkCondition(curTime - startTime)) && (curTime - startTime < timeout)) {
        try {
          Thread.sleep(100);
        } catch (final InterruptedException ex) {
          Thread.currentThread().interrupt();
        }
        curTime = System.currentTimeMillis();
      }
    }

    return result;
  }

  public static boolean waitFor(final WaitCondition condition, final TimeUnit timeUnitTimeout, final long timeoutDuration,
      final TimeUnit timeUnitSleep, final long sleepDuration) {
    final long timeout = timeUnitTimeout.toMillis(timeoutDuration);
    final long sleepBetween = timeUnitSleep.toMillis(sleepDuration);

    boolean result = false;
    if (condition != null) {
      final long startTime = System.currentTimeMillis();
      long curTime = startTime;

      while (!(result = condition.checkCondition(curTime - startTime)) && (curTime - startTime < timeout)) {
        try {
          Thread.sleep(sleepBetween);
        } catch (final InterruptedException ex) {
          Thread.currentThread().interrupt();
        }
        curTime = System.currentTimeMillis();
      }
    }

    return result;
  }
}