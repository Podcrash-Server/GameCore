package com.podcrash.game.utils;

import org.bukkit.util.Vector;

import java.util.Random;

public final class MathUtil {

    private static final Random random = new Random();

    public static boolean randomBool() {
        return random.nextBoolean();
    }

    public static int randomInt(int bound) {
        return random.nextInt(bound);
    }

    public static int randomRange(int n, int k) {
        return n+random.nextInt(k - n + 1);
    }

    public static double random() {
        return random.nextDouble();
    }

    public static double dumbMinecraftDegrees(double n) {
        return (n > 179.9) ? (-180.0 + (n - 179.9)) : n;
    }

    public static double offset2d(Vector a, Vector b) {
        a.setY(0);
        b.setY(0);
        return a.subtract(b).length();
    }

    public static double offset(Vector a, Vector b) {
        return a.subtract(b).length();
    }

}
