package com.podcrash.game.scoreboard;

import com.podcrash.game.Game;
import com.podcrash.game.utils.TickUtils;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

import java.util.concurrent.TimeUnit;

public abstract class ScoreboardObject implements IScoreboardObject {

    private final ScoreboardEntry scoreboardEntry;
    private final long time;
    private final TimeUnit timeUnit;
    private BukkitTask bukkitTask;
    private String text;

    protected ScoreboardObject(long time, TimeUnit timeUnit, String initialText) {
        this.scoreboardEntry = new ScoreboardEntry();
        this.time = time;
        this.timeUnit = timeUnit;
        this.text = initialText;
    }

    public void startTicking() {
        this.bukkitTask = Bukkit.getScheduler().runTaskTimer(
                Game.getInstance().getPlugin(),
                this::tick,
                0,
                TickUtils.fromTimeUnit(time, timeUnit)
        );
    }

    public ScoreboardEntry getScoreboardEntry() {
        return scoreboardEntry;
    }

    public long getTime() {
        return time;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public BukkitTask getBukkitTask() {
        return bukkitTask;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void stop() {
        bukkitTask.cancel();
        this.bukkitTask = null;
    }
}
