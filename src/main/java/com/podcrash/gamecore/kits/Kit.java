package com.podcrash.gamecore.kits;

import org.bukkit.inventory.ItemStack;
import java.util.ArrayList;
import java.util.List;

public abstract class Kit {

    private List<Ability> abilities;
    private long lastUsed;

    public Kit(Class<? extends Ability>... abilities) {
        setUpAbilities(abilities);
    }

    public Kit() {

    }

    public abstract String getName();
    public abstract String getPermission();
    public abstract List<String> getDescription();
    public abstract ItemStack getItem();
    public abstract ItemStack[] getArmor();
    public abstract ItemStack getWeapon();


    private void setUpAbilities(Class<? extends Ability>... abilities) {

        List<Ability> abilityList = new ArrayList<>();

        for (Class<? extends Ability> ability : abilities) {
            try {
                Ability newAbility = ability.newInstance();
                abilityList.add(newAbility);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        this.abilities = abilityList;

    }

    public List<Ability> getAbilities() {
        return abilities;
    }

    public void setLastUsed(long used) {
        this.lastUsed = used;
    }

    public long getLastUsed() {
        return lastUsed;
    }

}
