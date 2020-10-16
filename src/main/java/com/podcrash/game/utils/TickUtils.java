package com.podcrash.game.utils;

import java.util.concurrent.TimeUnit;

public class TickUtils {

    public static long fromTimeUnit(long time, TimeUnit timeUnit) {
        return Math.max(timeUnit.toMillis(time) / 50, 1);
    }
}
