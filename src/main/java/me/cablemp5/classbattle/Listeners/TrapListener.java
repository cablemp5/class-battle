package me.cablemp5.classbattle.Listeners;

import me.cablemp5.classbattle.ClassBattle;
import me.cablemp5.classbattle.ClassManager;
import org.bukkit.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class TrapListener implements Listener {


    private final ClassBattle classBattle;

    public TrapListener(ClassBattle classBattle) {
        this.classBattle = classBattle;
    }

    ArrayList<Material> groundblocks = new ArrayList<>(Arrays.asList(Material.PODZOL, Material.GRAVEL, Material.COARSE_DIRT));

    Random random = new Random();

    @EventHandler
    public void onRedStoneEvent(BlockRedstoneEvent event) {
        if (event.getBlock().getType() == Material.SPRUCE_PRESSURE_PLATE && event.getOldCurrent() == 0 && ClassManager.CLASS_MAP.containsValue("bountyhunter")) {
            Location pressurePlate = event.getBlock().getLocation();
            World world = pressurePlate.getWorld();

            world.spawnParticle(Particle.CLOUD, pressurePlate, 100, 2, 2, 2, 2);
            world.spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, pressurePlate, 100, 2, 2, 2, 2);


            Location front = new Location(world, pressurePlate.getX() + 1, pressurePlate.getY(), pressurePlate.getZ());
            Location topleft = new Location(world, pressurePlate.getX() + 1, pressurePlate.getY(), pressurePlate.getZ() + 1);
            Location topright = new Location(world, pressurePlate.getX() + 1, pressurePlate.getY(), pressurePlate.getZ() - 1);
            Location left = new Location(world, pressurePlate.getX(), pressurePlate.getY(), pressurePlate.getZ() + 1);
            Location right = new Location(world, pressurePlate.getX(), pressurePlate.getY(), pressurePlate.getZ() - 1);
            Location back = new Location(world, pressurePlate.getX() - 1, pressurePlate.getY(), pressurePlate.getZ());
            Location backleft = new Location(world, pressurePlate.getX() - 1, pressurePlate.getY(), pressurePlate.getZ() + 1);
            Location backright = new Location(world, pressurePlate.getX() - 1, pressurePlate.getY(), pressurePlate.getZ() - 1);
            Location bottom = new Location(world, pressurePlate.getX(), pressurePlate.getY() - 1, pressurePlate.getZ());
            Location top = new Location(world, pressurePlate.getX(), pressurePlate.getY() + 2, pressurePlate.getZ());

            ArrayList<Location> locs = new ArrayList<>(Arrays.asList(front, topleft, topright, left, right, back, backright, backleft));


            for (Location l : locs) {

                l = l.add(0, -1, 0);
                l.getBlock().setType(groundblocks.get(random.nextInt(groundblocks.size())));
                l = l.add(0, 1, 0);
                l.getBlock().setType(Material.DARK_OAK_FENCE);

            }



            locs.remove(front);
            locs.remove(left);
            locs.remove(back);
            locs.remove(right);

            for (Location l:locs) {
                l = l.add(0, 1, 0);

                l.getBlock().setType(Material.DARK_OAK_FENCE);


            }

            locs.add(front);
            locs.add(left);
            locs.add(back);
            locs.add(right);

            front.add(0,1,0);
            left.add(0,1,0);

            back.add(0,1,0);

            right.add(0,1,0);


            for (Location l:locs) {
                l = l.add(0, 1, 0);

                l.getBlock().setType(Material.SPRUCE_SLAB);


            }

            bottom.getBlock().setType(Material.PODZOL);
            top.getBlock().setType(Material.SPRUCE_SLAB);

            pressurePlate.getWorld().playSound(pressurePlate, Sound.BLOCK_WOODEN_TRAPDOOR_CLOSE, 2, 0);
            pressurePlate.getWorld().playSound(pressurePlate, Sound.BLOCK_LAVA_EXTINGUISH, 2, 0);


        }
    }
}
