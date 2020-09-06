package com.podcrash.gamecore.utils;

import org.bukkit.util.Vector;

public final class MathUtil {

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
