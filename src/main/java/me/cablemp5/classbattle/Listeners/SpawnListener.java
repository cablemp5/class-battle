package me.cablemp5.classbattle.Listeners;

import me.cablemp5.classbattle.ClassBattle;
import me.cablemp5.classbattle.ClassManager;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntitySpawnEvent;

import java.util.ArrayList;
import java.util.Arrays;

public class SpawnListener implements Listener {

    private final ClassBattle classBattle;

    public SpawnListener(ClassBattle classBattle) {
        this.classBattle = classBattle;
    }

    ArrayList<EntityType> eggs = new ArrayList<>(Arrays.asList(EntityType.WARDEN,EntityType.BLAZE,EntityType.ENDERMAN));


    @EventHandler
    public void onSpawn(CreatureSpawnEvent event) {

        if (event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.SPAWNER_EGG && eggs.contains(event.getEntity().getType()) && ClassManager.CLASS_MAP.containsValue("poacher")) {

            LivingEntity e = event.getEntity();
            e.setHealth(1);


        }

    }


}
