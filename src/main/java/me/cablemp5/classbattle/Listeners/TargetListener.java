package me.cablemp5.classbattle.Listeners;

import me.cablemp5.classbattle.ClassBattle;
import me.cablemp5.classbattle.ClassManager;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;

public class TargetListener implements Listener {

    private final ClassBattle classBattle;

    public TargetListener(ClassBattle classBattle) {
        this.classBattle = classBattle;
    }

    @EventHandler
    public void onTarget(EntityTargetLivingEntityEvent e) {

        if (e.getEntity().getType() == EntityType.WITHER_SKELETON || e.getEntity().getType() == EntityType.SKELETON) {

            if (e.getTarget() != null && e.getTarget().getType() == EntityType.PLAYER && ClassManager.CLASS_MAP.get((Player) e.getTarget()).equalsIgnoreCase("soulreaper")) {

                e.setCancelled(true);

            }


        } else if (e.getEntity().getType() == EntityType.ENDERMITE) {

            if (e.getTarget() != null && e.getTarget().getType() == EntityType.PLAYER && ClassManager.CLASS_MAP.get((Player) e.getTarget()).equalsIgnoreCase("poacher")) {

                e.setCancelled(true);

            }


        }


    }
}
