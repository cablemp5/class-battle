package me.cablemp5.classbattle.Listeners;

import me.cablemp5.classbattle.ClassBattle;
import me.cablemp5.classbattle.ClassManager;
import me.cablemp5.classbattle.utils.ItemBuilderUtil;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class MoveListener implements Listener {

    private final ClassBattle classBattle;

    public MoveListener(ClassBattle classBattle) {
        this.classBattle = classBattle;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e)
    {
        Location from = e.getFrom();
        Location to = e.getTo();
        Player player = e.getPlayer();
        if ((player.getLocation().add(0,-0.25,0).getBlock().getType()== Material.END_STONE_BRICK_SLAB || player.getLocation().add(0,-0.25,0).getBlock().getType()== Material.PURPUR_SLAB) && ClassManager.CLASS_MAP.get(player).equals("endassassin")) {

            if (!player.hasPotionEffect(PotionEffectType.JUMP)) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP,20,15));


            }

        }
        if (from.getBlockY() < to.getBlockY() && !player.isSwimming() && !player.isFlying() && (player.getLocation().add(0,-0.25,0).getBlock().getType()== Material.END_STONE_BRICK_SLAB || player.getLocation().add(0,-0.25,0).getBlock().getType()== Material.PURPUR_SLAB) && ClassManager.CLASS_MAP.get(player).equals("endassassin"))
        {

            player.getInventory().setChestplate(ItemBuilderUtil.generateItemWithMeta(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "End Assassin's Wings", Material.ELYTRA, 1, null, null, null, 0));

            new BukkitRunnable() {


                @Override
                public void run() {
                   if (player.getLocation().add(0,-0.1,0).getBlock().getType() != Material.AIR && player.getLocation().add(0,-1,0).getBlock().getType() != Material.GRASS && player.getLocation().add(0,-1,0).getBlock().getType() != Material.TALL_GRASS) {
                       player.getInventory().setChestplate(null);
                       this.cancel();
                   }
                }
            }.runTaskTimer(classBattle,10L,2L);


        }

    }
}
