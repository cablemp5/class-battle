package me.cablemp5.classbattle.Commands;

import me.cablemp5.classbattle.ClassBattle;
import me.cablemp5.classbattle.ClassManager;
import me.cablemp5.classbattle.utils.RandomLocationUtil;
import me.cablemp5.classbattle.utils.SpreadPlayerUtil;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class ArenaCommand implements TabExecutor {


    private final ClassBattle classBattle;

    public ArenaCommand(ClassBattle classBattle) {
        this.classBattle = classBattle;
    }

    private boolean isArena = false;

    Random random = new Random();

    HashMap<Player,BossBar> bosses = new HashMap<>();

    public boolean getIsArena() {
        return isArena;
    }
//
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {

            Player player = (Player) sender;

            if (!isArena) {

                World world = Bukkit.getServer().getWorlds().get(0);
                Location randomPos = RandomLocationUtil.generateRandomLocation(world);

                world.setSpawnLocation(randomPos);
                world.getWorldBorder().setCenter(randomPos);
                world.getWorldBorder().setSize(45);


                for (Player p : Bukkit.getOnlinePlayers()) {
                    p.setGameMode(GameMode.SURVIVAL);
                    Location spawn = SpreadPlayerUtil.generateLocationWithinRadius(randomPos,50);
                    p.setBedSpawnLocation(spawn);
                    p.teleport(spawn);
                    p.getInventory().clear();
                    for (PotionEffectType t : PotionEffectType.values()) {
                        p.removePotionEffect(t);
                    }
                    p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
                    p.setHealth(20);
                    p.setFoodLevel(20);
                    ClassManager.CLASS_MAP.remove(p);
                    ClassManager.POACHER_MAP.remove(p);
                    ClassSelectCommand.classSelect(p);

                    //world.playSound(player.getLocation(), Sound.MUSIC_DISC_PIGSTEP, 2, 0);

                }

                for (Player o: Bukkit.getOnlinePlayers()) {
                    BossBar bossBar = Bukkit.createBossBar(ChatColor.BOLD + "Proximity", BarColor.GREEN, BarStyle.SOLID);
                    bossBar.addPlayer(o);
                    bosses.put(o,bossBar);

                }


                isArena = true;

                new BukkitRunnable() {

                    @Override
                    public void run() {

                        if (!isArena) {
                            for (BossBar b:bosses.values()) {
                                b.removeAll();
                                b.setVisible(false);

                                b.setVisible(false);
                            }
                            bosses.clear();
                            this.cancel();
                        }


                        for (Player p: Bukkit.getOnlinePlayers()) {

                            ArrayList<Player> others = new ArrayList<>(Bukkit.getOnlinePlayers());
                            others.remove(p);

                            Player nearest = others.get(0);

                            for (Player pl: others) {
                                if (pl.getLocation().distance(p.getLocation()) < nearest.getLocation().distance(p.getLocation())) {
                                    nearest = pl;
                                }
                            }

                            bosses.get(p).setTitle(ChatColor.BOLD + "Proximity to " + nearest.getDisplayName());

                            double dist = nearest.getLocation().distance(p.getLocation());
                            if (dist > 50) {
                                dist = 50;
                            }

                            double percent = ((dist/50)*-1)+1;



                            if (percent > 0.9) {
                                bosses.get(p).setColor(BarColor.RED);

                            } else if (percent > 0.7) {
                                bosses.get(p).setColor(BarColor.YELLOW);

                            } else {
                                bosses.get(p).setColor(BarColor.GREEN);

                            }

                            bosses.get(p).setProgress(percent);

                        }

                    }
                }.runTaskTimer(classBattle,0L,2L);

            } else {

                World world = Bukkit.getServer().getWorlds().get(0);
                world.getWorldBorder().setSize(99999999);

                isArena = false;

                for (BossBar b:bosses.values()) {
                    b.removeAll();
                    b.setVisible(false);

                    b.setVisible(false);
                }
                bosses.clear();

            }

        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        return null;
    }
}
