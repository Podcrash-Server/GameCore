package com.podcrash.game.scoreboard.objects;

import com.podcrash.game.scoreboard.ScoreboardObject;

import java.util.concurrent.TimeUnit;

public class CountdownSBObject extends ScoreboardObject {

    private long timeLeft;

    public CountdownSBObject(long timeLeft) {
        super(1, TimeUnit.SECONDS, "0 seconds");
        this.timeLeft = timeLeft;
    }

    @Override
    public void init() {
        getScoreboardEntry().setText(" seconds");
        getScoreboardEntry().setPrefix("0");
    }

    @Override
    public void tick() {
        if (timeLeft == 0) {
            stop();
            return;
        }
        String[] parts = formatTime(timeLeft);
        getScoreboardEntry().setPrefix(parts[0]);
        getScoreboardEntry().setText(parts[1]);
    }

    private String[] formatTime(long seconds) {
        String[] parts = new String[]{String.valueOf(seconds)," seconds"};
        if (seconds > 60) {
            parts[0] = String.valueOf(round(seconds / 60d, 1));
            parts[1] = " minutes";
        }
        return parts;
    }

    private static double round(double number, int decimal) {
        double multiplier = Math.pow(10, decimal);
        return Math.round(number * multiplier) / multiplier;
    }
}
