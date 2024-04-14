package me.cablemp5.classbattle.Listeners;

import me.cablemp5.classbattle.ClassBattle;
import me.cablemp5.classbattle.ClassManager;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import java.util.Random;

public class DamageListener implements Listener {

    private final ClassBattle classBattle;

    public DamageListener(ClassBattle classBattle) {
        this.classBattle = classBattle;
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {


        if (e.getEntity().getType() == EntityType.PLAYER && e.getCause() == EntityDamageEvent.DamageCause.CONTACT && ((Player) e.getEntity()).getLocation().getBlock().getType() != Material.POINTED_DRIPSTONE) {

            Player player = (Player) e.getEntity();

            if (ClassManager.CLASS_MAP.get(player).equals("botanist")) {
                e.setCancelled(true);
            } else {

                player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 100, 0));
                player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 100, 0));
            }


        }  else if (e.getEntity().getType() == EntityType.PLAYER && e.getCause() == EntityDamageEvent.DamageCause.FALL && ClassManager.CLASS_MAP.get((Player) e.getEntity()).equals("endassassin")) {
            e.setCancelled(true);

        }



    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {


        if (e.getEntity().getType() == EntityType.PLAYER && e.getDamager().getType() == EntityType.PLAYER && ClassManager.CLASS_MAP.get((Player)e.getDamager()).equals("scholar")) {

            ((LivingEntity)e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS,20,1));

        }
        if (e.getDamager().getType() == EntityType.PLAYER && ClassManager.CLASS_MAP.get((Player)e.getDamager()).equals("soulreaper")) {

            ((LivingEntity)e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.WITHER,20,1));

        }
        if (e.getDamager().getType() == EntityType.PLAYER && ClassManager.CLASS_MAP.get((Player)e.getDamager()).equals("scholar")) {

            PotionEffectType[] effects = PotionEffectType.values();
            Random random = new Random();
            ((LivingEntity)e.getEntity()).addPotionEffect(new PotionEffect(effects[random.nextInt(effects.length)],60,
                random.nextInt(1,4)));

        }



    }
}
