package com.podcrash.game.scoreboard;

public class ScoreboardEntry {

    private String text;
    private String prefix;
    private String suffix;

    public ScoreboardEntry() {}

    public ScoreboardEntry(String text, String prefix, String suffix) {
        this.text = text;
        this.prefix = prefix;
        this.suffix = suffix;
    }

    public String getText() {
        return text;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
