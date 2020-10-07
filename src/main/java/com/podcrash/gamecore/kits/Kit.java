package com.podcrash.gamecore.kits;

import org.bukkit.inventory.ItemStack;
import java.util.ArrayList;
import java.util.List;

public abstract class Kit {

    private List<Ability> abilities;
    private long lastUsed;
    private String name;

    public Kit(String name, Class<? extends Ability>... abilities) {
        this.name = name;
        setUpAbilities(abilities);
    }

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

    public void addAbilityKit(Class<? extends Ability> ability) {
        //should never happen but just in-case
        if (ability == null) return;
        try {
            Ability newAbility = ability.newInstance();
            this.abilities.add(newAbility);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
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

    public String getName() {
        return name;
    }

}
