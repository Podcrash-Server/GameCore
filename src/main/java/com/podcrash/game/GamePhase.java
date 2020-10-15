package com.podcrash.game;

import org.bukkit.event.*;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockExpEvent;
import org.bukkit.event.entity.*;
import org.bukkit.plugin.RegisteredListener;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.Consumer;

public abstract class GamePhase implements IGamePhase {

    private final String name;

    protected GamePhase(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public <T extends Event> void registerListener(Class<T> eventClass, Consumer<T> consumer, EventPriority eventPriority) {
        try {
            Method method;
            if (eventClass == EntityDamageByEntityEvent.class) {
                method = EntityDamageEvent.class.getDeclaredMethod("getHandlerList");
            } else if (eventClass == BlockBreakEvent.class) {
                method = BlockExpEvent.class.getDeclaredMethod("getHandlerList");
            } else if (eventClass == PlayerDeathEvent.class) {
                method = EntityDeathEvent.class.getDeclaredMethod("getHandlerList");
            } else if (eventClass == EntityTargetLivingEntityEvent.class) {
                method = EntityTargetEvent.class.getDeclaredMethod("getHandlerList");
            } else {
                method = eventClass.getDeclaredMethod("getHandlerList");
            }
            HandlerList handlerList = (HandlerList) method.invoke(null);
            handlerList.register(new RegisteredListener(new Listener() {
                @Override
                public int hashCode() {
                    return super.hashCode();
                }
            }, (listener, event) -> {
                if (!eventClass.getName().equals(event.getClass().getName()))
                    return;
                consumer.accept((T) event);
            }, eventPriority, Game.getInstance().getPlugin(), false));
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
