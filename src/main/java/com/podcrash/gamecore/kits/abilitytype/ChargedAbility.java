package com.podcrash.gamecore.kits.abilitytype;

import com.podcrash.gamecore.GameCore;
import com.podcrash.gamecore.kits.Ability;

public abstract class ChargedAbility extends Ability implements IAbility {

    private int taskId;

    public abstract String getChargeName();
    public abstract boolean startsWithMaxCharges();
    public abstract int getSecondsBetweenCharge();
    public abstract int getCurrentCharges();
    public abstract void addCharge();
    public abstract void removeCharge();
    public abstract int getMaxCharges();

    public void setTaskId(int id) {
        this.taskId = id;
    }

    public int getTaskId() {
        return taskId;
    }

    public String getCurrentChargeMessage() {
        return String.format("%s %s %s charges!", GameCore.getKitPrefix(), getCurrentCharges(), getChargeName());
    }

    public String getNoChargesMessage() {
        return String.format("%s No %s charges!", GameCore.getKitPrefix(), getChargeName());
    }

}
