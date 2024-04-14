package me.cablemp5.classbattle.Listeners;

import me.cablemp5.classbattle.ClassBattle;
import me.cablemp5.classbattle.ClassManager;
import me.cablemp5.classbattle.Commands.ArenaCommand;
import org.bukkit.GameMode;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.ArrayList;
import java.util.Arrays;

public class DeathListener implements Listener {

    private final ClassBattle classBattle;

    public DeathListener(ClassBattle classBattle) {
        this.classBattle = classBattle;
    }

    ArrayList<EntityType> eggs = new ArrayList<>(Arrays.asList(EntityType.WARDEN,EntityType.BLAZE,EntityType.ENDERMAN));


    @EventHandler
    public void onDeath(EntityDeathEvent event) {

        if (((LivingEntity)event.getEntity()).getKiller() != null && ClassManager.CLASS_MAP.get(((LivingEntity)event.getEntity()).getKiller()).equals("poacher")) {

            LivingEntity e = event.getEntity();

            switch (e.getType()) {
                case ENDERMAN:
                case WARDEN:
                case BLAZE: {
                    ClassManager.POACHER_MAP.put(((LivingEntity) event.getEntity()).getKiller(), e.getType());
                    break;
                }
            }


        }
        if (event.getEntity().getKiller() != null && event.getEntity() instanceof Player && classBattle.getArenaCommand()
            .getIsArena()) {

            Player p = (Player) event.getEntity();

            p.setGameMode(GameMode.SPECTATOR);

        }
    }
//
}
