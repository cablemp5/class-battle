package me.cablemp5.classbattle.Listeners;

import me.cablemp5.classbattle.ClassBattle;
import me.cablemp5.classbattle.ClassManager;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class BlockListener implements Listener {

    private final ClassBattle classBattle;

    public BlockListener(ClassBattle classBattle) {
        this.classBattle = classBattle;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {


         if (ClassManager.CLASS_MAP.containsValue("scholar") && event.getBlock().getType() == Material.BOOKSHELF) {

            Player player = event.getPlayer();

            Location l = event.getBlock().getLocation();

            l.add(0,1,0).getBlock().setType(Material.OAK_TRAPDOOR);


        } else if (ClassManager.CLASS_MAP.containsValue("botanist") && event.getBlock().getType() == Material.JUNGLE_SAPLING) {

            Player player = event.getPlayer();

            Location l = event.getBlock().getLocation();

            Location back = new Location(l.getWorld(), l.getBlockX() + 1, l.getBlockY(), l.getBlockZ());
            Location backright = new Location(l.getWorld(), l.getBlockX() + 1, l.getBlockY(), l.getBlockZ() + 1);
            Location left = new Location(l.getWorld(), l.getBlockX(), l.getBlockY(), l.getBlockZ() + 1);

            back.getBlock().setType(Material.JUNGLE_SAPLING);
            backright.getBlock().setType(Material.JUNGLE_SAPLING);

            left.getBlock().setType(Material.JUNGLE_SAPLING);


            for (int i = 0; i < 50; i++) {

                event.getBlock().applyBoneMeal(BlockFace.UP);

            }

            new BukkitRunnable() {

                @Override
                public void run() {

                    player.spawnParticle(Particle.FLASH, player.getLocation().add(0, 1, 0), 20, 0.5, 0.5, 0.5);

                    player.teleport(new Location(player.getWorld(), player.getLocation().getX(), player.getWorld().getHighestBlockYAt(player.getLocation().getBlockX(), player.getLocation().getBlockZ()) + 1, player.getLocation().getZ()));

                    player.spawnParticle(Particle.FLASH, player.getLocation().add(0, 1, 0), 20, 0.5, 0.5, 0.5);
                    player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 2, 0);
                }
            }.runTaskLater(classBattle, 10L);


        } else if (ClassManager.CLASS_MAP.containsValue("endassassin") && event.getBlock().getType() == Material.END_STONE_BRICK_SLAB) {

            Player player = event.getPlayer();


            World world = player.getWorld();
            Location loc = event.getBlock().getLocation();

            Location corn1 = new Location(world, loc.getX() + 1, loc.getY() - 1, loc.getZ() + 1);
            Location corn2 = new Location(world, loc.getX() + 1, loc.getY() - 1, loc.getZ() - 1);
            Location corn3 = new Location(world, loc.getX() - 1, loc.getY() - 1, loc.getZ() + 1);
            Location corn4 = new Location(world, loc.getX() - 1, loc.getY() - 1, loc.getZ() - 1);
            Location purp1 = new Location(world, loc.getX() + 1, loc.getY(), loc.getZ());
            Location purp2 = new Location(world, loc.getX() - 1, loc.getY(), loc.getZ());
            Location purp3 = new Location(world, loc.getX(), loc.getY(), loc.getZ() - 1);
            Location purp4 = new Location(world, loc.getX(), loc.getY(), loc.getZ() + 1);
            Location edge1 = new Location(world, loc.getX() + 2, loc.getY() - 1, loc.getZ());
            Location edge2 = new Location(world, loc.getX() - 2, loc.getY() - 1, loc.getZ());
            Location edge3 = new Location(world, loc.getX(), loc.getY() - 1, loc.getZ() - 2);
            Location edge4 = new Location(world, loc.getX(), loc.getY() - 1, loc.getZ() + 2);


            ArrayList<Location> locs = new ArrayList<>(Arrays.asList(corn1, corn2, corn3, corn4));
            ArrayList<Location> locs2 = new ArrayList<>(Arrays.asList(purp1, purp2, purp3, purp4));
            ArrayList<Location> locs3 = new ArrayList<>(Arrays.asList(edge1, edge2, edge3, edge4));


            for (Location l : locs) {

                l.getBlock().setType(Material.END_STONE_BRICKS);

            }
            for (Location l : locs2) {

                l.getBlock().setType(Material.PURPUR_SLAB);

            }

            for (Location l : locs3) {

                l.getBlock().setType(Material.END_STONE);

            }

        } else if (ClassManager.CLASS_MAP.containsValue("firewalker") && event.getBlock().getType() == Material.FIRE) {

            Player player = event.getPlayer();


            World world = player.getWorld();
            Location loc = event.getBlock().getLocation();

            ArrayList<Material> groundblocks = new ArrayList<>(Arrays.asList(Material.NETHERRACK, Material.MAGMA_BLOCK, Material.SOUL_SAND));

            Random random = new Random();

            Location corn1 = new Location(world, loc.getX() + 1, loc.getY() - 1, loc.getZ() + 1);
            Location corn2 = new Location(world, loc.getX() + 1, loc.getY() - 1, loc.getZ() - 1);
            Location corn3 = new Location(world, loc.getX() - 1, loc.getY() - 1, loc.getZ() + 1);
            Location corn4 = new Location(world, loc.getX() - 1, loc.getY() - 1, loc.getZ() - 1);
            Location edge1 = new Location(world, loc.getX() + 1, loc.getY() - 1, loc.getZ());
            Location edge2 = new Location(world, loc.getX() - 1, loc.getY() - 1, loc.getZ());
            Location edge3 = new Location(world, loc.getX(), loc.getY() - 1, loc.getZ() - 1);
            Location edge4 = new Location(world, loc.getX(), loc.getY() - 1, loc.getZ() + 1);


            ArrayList<Location> locs = new ArrayList<>(Arrays.asList(corn1, corn2, corn3, corn4, edge1, edge2, edge3, edge4));

            for (Location l : locs) {

                l.getBlock().setType(groundblocks.get(random.nextInt(groundblocks.size())));
                l.add(random.nextInt(2), 0, random.nextInt(2));
                l = new Location(world,l.getX(),world.getHighestBlockYAt(l),l.getZ());
                l.getBlock().setType(groundblocks.get(random.nextInt(groundblocks.size())));
                if (random.nextInt(2) > 0) {
                    l.add(0, 1, 0);
                    l.getBlock().setType(Material.FIRE);
                }


            }


        } else if (ClassManager.CLASS_MAP.containsValue("soulreaper") && event.getBlock().getType() == Material.WITHER_SKELETON_SKULL) {

            Player player = event.getPlayer();


            World world = player.getWorld();
            Location loc = event.getBlock().getLocation();

            ArrayList<Material> groundblocks = new ArrayList<>(Arrays.asList(Material.SOUL_SAND, Material.SOUL_SOIL));

            Random random = new Random();

            Location corn = new Location(world, loc.getX(), loc.getY() - 1, loc.getZ());
            Location corn1 = new Location(world, loc.getX() + 1, loc.getY() - 1, loc.getZ() + 1);
            Location corn2 = new Location(world, loc.getX() + 1, loc.getY() - 1, loc.getZ() - 1);
            Location corn3 = new Location(world, loc.getX() - 1, loc.getY() - 1, loc.getZ() + 1);
            Location corn4 = new Location(world, loc.getX() - 1, loc.getY() - 1, loc.getZ() - 1);
            Location edge1 = new Location(world, loc.getX() + 1, loc.getY() - 1, loc.getZ());
            Location edge2 = new Location(world, loc.getX() - 1, loc.getY() - 1, loc.getZ());
            Location edge3 = new Location(world, loc.getX(), loc.getY() - 1, loc.getZ() - 1);
            Location edge4 = new Location(world, loc.getX(), loc.getY() - 1, loc.getZ() + 1);


            ArrayList<Location> locs = new ArrayList<>(Arrays.asList(corn, corn1, corn2, corn3, corn4, edge1, edge2, edge3, edge4));

            for (Location l : locs) {

                l.getBlock().setType(groundblocks.get(random.nextInt(groundblocks.size())));
                l.add(1,0,0);
                l = new Location(world,l.getX(),world.getHighestBlockYAt(l),l.getZ());
                l.getBlock().setType(groundblocks.get(random.nextInt(groundblocks.size())));

                l.add(0,0,1);
                l = new Location(world,l.getX(),world.getHighestBlockYAt(l),l.getZ());
                l.getBlock().setType(groundblocks.get(random.nextInt(groundblocks.size())));

                l.add(0,0,-2);
                l = new Location(world,l.getX(),world.getHighestBlockYAt(l),l.getZ());
                l.getBlock().setType(groundblocks.get(random.nextInt(groundblocks.size())));

                l.add(-1,0,0);
                l = new Location(world,l.getX(),world.getHighestBlockYAt(l),l.getZ());
                l.getBlock().setType(groundblocks.get(random.nextInt(groundblocks.size())));

                l.add(0,0,1);
                l = new Location(world,l.getX(),world.getHighestBlockYAt(l),l.getZ());
                l.getBlock().setType(groundblocks.get(random.nextInt(groundblocks.size())));

                l.add(0,0,1);
                l = new Location(world,l.getX(),world.getHighestBlockYAt(l),l.getZ());
                l.getBlock().setType(groundblocks.get(random.nextInt(groundblocks.size())));

                l.add(-1,0,0);
                l = new Location(world,l.getX(),world.getHighestBlockYAt(l),l.getZ());
                l.getBlock().setType(groundblocks.get(random.nextInt(groundblocks.size())));

                l.add(0,0,-1);
                l = new Location(world,l.getX(),world.getHighestBlockYAt(l),l.getZ());
                l.getBlock().setType(groundblocks.get(random.nextInt(groundblocks.size())));

                l.add(0,0,-1);
                l = new Location(world,l.getX(),world.getHighestBlockYAt(l),l.getZ());
                l.getBlock().setType(groundblocks.get(random.nextInt(groundblocks.size())));

                l.add(1,0,1);
                l = new Location(world,l.getX(),world.getHighestBlockYAt(l),l.getZ());
                l.getBlock().setType(groundblocks.get(random.nextInt(groundblocks.size())));


                for (int i = 0;i<30;i++) {
                    l.add(random.nextInt(-1,2),0,random.nextInt(-1,2));
                    l = new Location(world,l.getX(),world.getHighestBlockYAt(l),l.getZ());
                    l.getBlock().setType(groundblocks.get(random.nextInt(groundblocks.size())));

                }

            }

        } else if (ClassManager.CLASS_MAP.containsValue("bountyhunter") && event.getBlock().getType() == Material.SPRUCE_PRESSURE_PLATE) {



             Location l = event.getBlock().getLocation();

             Location top = l.clone().add(1,0,0);
             top = new Location(top.getWorld(),top.getX(),top.getWorld().getHighestBlockYAt(top)+1,top.getZ());
             top.getBlock().setType(Material.SPRUCE_PRESSURE_PLATE);

             Location back = l.clone().add(-1,0,0);
             back = new Location(back.getWorld(),back.getX(),back.getWorld().getHighestBlockYAt(back)+1,back.getZ());
             back.getBlock().setType(Material.SPRUCE_PRESSURE_PLATE);

             Location left = l.clone().add(0,0,-1);
             left = new Location(left.getWorld(),left.getX(),left.getWorld().getHighestBlockYAt(left)+1,left.getZ());
             left.getBlock().setType(Material.SPRUCE_PRESSURE_PLATE);

             Location right = l.clone().add(0,0,1);
             right = new Location(right.getWorld(),right.getX(),right.getWorld().getHighestBlockYAt(right)+1,right.getZ());
             right.getBlock().setType(Material.SPRUCE_PRESSURE_PLATE);
             




         }

    }
}
