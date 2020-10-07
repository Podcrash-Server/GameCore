package com.podcrash.gamecore.kits.abilitytype;

import com.podcrash.gamecore.GameCore;
import com.podcrash.gamecore.kits.Ability;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public abstract class BowShotAbility extends Ability implements Interact, Cooldown {

    private boolean prepared = false;
    private HashMap<Arrow, Float> arrowForceMap;

    public BowShotAbility() {
        super();
        arrowForceMap = new HashMap<>();
    }

    @Override
    public ItemStack getItem() {
        return new ItemStack(Material.BOW);
    }

    @Override
    public void doAbility() {
        setPrepared(true);
        getKitPlayer().getPlayer().sendMessage(String.format("%s You have prepared: %s%s%s!", GameCore.getKitPrefix(), ChatColor.GREEN, getName(), ChatColor.GRAY));
    }

    @Override
    public List<Action> getActions() {
        return Arrays.asList(Action.RIGHT_CLICK_AIR, Action.RIGHT_CLICK_BLOCK);
    }


    public boolean isPrepared() {
        return prepared;
    }

    public void setPrepared(boolean prepped) {
        this.prepared = prepped;
    }

    /*
    Shooting the arrow
     */
    @EventHandler
    public void shootBow(EntityShootBowEvent event){
        if(!isPrepared()) return;
        if(event.isCancelled()) return;
        if(event.getEntity() instanceof Player && event.getProjectile() instanceof Arrow){
            Player player = (Player) event.getEntity();
            if(player == getKitPlayer().getPlayer()){
                setPrepared(false);
                Arrow arrow = (Arrow) event.getProjectile();
                float currentForce = event.getForce();
                arrowForceMap.put(arrow, currentForce);
                shotArrow(arrow, currentForce);

                StringBuilder builder = new StringBuilder();
                builder.append(GameCore.getKitPrefix());
                builder.append(" You shot ");
                builder.append(ChatColor.BOLD);
                builder.append(ChatColor.YELLOW);
                builder.append(getName());
                builder.append(ChatColor.GRAY);
                builder.append(".");
            }
        }
    }

    protected abstract void shotArrow(Arrow arrow, float force);


    /*
    Shooting a player
    */
    @EventHandler(priority = EventPriority.NORMAL)
    public void arrowShotPlayer(EntityDamageByEntityEvent event) {
        if (event.getCause() != EntityDamageEvent.DamageCause.PROJECTILE || event.isCancelled()) return;
        if (!(event.getDamager() instanceof Arrow)) return;
        if (!( ((Arrow) event.getDamager()).getShooter() instanceof Player)) return;
        if (! (event.getEntity() instanceof LivingEntity)) return;
        Arrow proj = (Arrow) event.getDamager();
        Player shooter = (Player) proj.getShooter();
        if(shooter == getKitPlayer().getPlayer()) return; //player can't shoot themselves
        if(!arrowForceMap.containsKey(proj)) return;
        LivingEntity victim = (LivingEntity) event.getEntity();
        getKitPlayer().getPlayer().sendMessage(getUsedMessage().replace("used", "shot"));
        shotEntity(event, shooter, victim, proj, arrowForceMap.get(proj));

    }
    protected abstract void shotEntity(EntityDamageByEntityEvent event, Player shooter, LivingEntity victim, Arrow arrow, float force);

    /*
    Shooting the ground
     */
    @EventHandler(priority = EventPriority.MONITOR)
    public void arrowShotGround(ProjectileHitEvent event){
        if(event.getEntity() instanceof Arrow ){

            Arrow arr = (Arrow) event.getEntity();
            if(arr.getShooter() instanceof Player){
                Player shooter = (Player) arr.getShooter();
                if(shooter == getKitPlayer().getPlayer() && arrowForceMap.containsKey(arr)){
                    shotGround(shooter, event.getEntity().getLocation(), arr, arrowForceMap.get(arr));
                    arrowForceMap.remove(arr);
                }

            }
        }
    }

    protected abstract void shotGround(Player shooter, Location location, Arrow arrow, float force);

}
