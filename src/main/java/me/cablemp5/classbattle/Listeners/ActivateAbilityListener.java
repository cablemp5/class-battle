package me.cablemp5.classbattle.Listeners;

import me.cablemp5.classbattle.ClassBattle;
import me.cablemp5.classbattle.ClassManager;
import me.cablemp5.classbattle.utils.ItemBuilderUtil;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CrossbowMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;
import org.checkerframework.checker.units.qual.A;
import sun.util.resources.cldr.ext.TimeZoneNames_ps_PK;

import java.util.*;

public class ActivateAbilityListener implements Listener {

    private final ClassBattle classBattle;

    public ActivateAbilityListener(ClassBattle classBattle) {
        this.classBattle = classBattle;
    }


    ArrayList<Player> gladiatorPlayersOnCooldown = new ArrayList<>();

    private final int gladiatorCooldown = 200;
    ArrayList<Player> reaperPlayersOnCooldown = new ArrayList<>();
    private final int reaperCooldown = 400;

    ArrayList<Player> firePlayersOnCooldown = new ArrayList<>();

    private final int fireCooldown = 100;

    ArrayList<Player> assPlayersOnCooldown = new ArrayList<>();

    private final int assCooldown = 300;

    ArrayList<Player> bountPlayersOnCooldown = new ArrayList<>();

    private final int bountCooldown = 400;

    ArrayList<Player> botPlayersOnCooldown = new ArrayList<>();

    private final int botCooldown = 300;

    ArrayList<Player> scholarPlayersOnCooldown = new ArrayList<>();

    private final int scholarCooldown = 150;

    ArrayList<Player> poacherPlayersOnCooldown = new ArrayList<>();

    private final int poacherCooldown = 100;

    private final int frostCooldown = 200;
    ArrayList<Player> frostPlayersOnCooldown = new ArrayList<>();

    @EventHandler
    public void onClick(PlayerInteractEvent event) {

        Player player = event.getPlayer();

        if (ClassManager.CLASS_MAP.containsKey(player) && (event.getAction().equals(Action.RIGHT_CLICK_AIR) && !event.getPlayer().getInventory().getItemInMainHand().getType().isEdible() && !event.getPlayer().getInventory().getItemInMainHand().getType().isInteractable() ) || (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && player.getInventory().getItemInMainHand().getType() == Material.ENCHANTED_BOOK)) {

            switch (ClassManager.CLASS_MAP.get(player)) {
                case "gladiator": {

                    if (!gladiatorPlayersOnCooldown.contains(player)) {

                        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.RED + "" + ChatColor.BOLD + "RAGE " + ChatColor.WHITE + "◇ ◇ ◇ ◇ ◇ ◇ ◇ ◇ ◇ ◇ "));


                        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 60, 2));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 60, 2));

                        gladiatorPlayersOnCooldown.add(player);

                        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_EVOKER_PREPARE_ATTACK, 2, 1);

                        player.spawnParticle(Particle.VILLAGER_ANGRY, player.getLocation().add(0, 1, 0), 25, 0.3, 0.3, 0.3);

                        new BukkitRunnable() {


                            @Override
                            public void run() {
                                gladiatorPlayersOnCooldown.remove(player);
                                if (ClassManager.CLASS_MAP.get(player).equals("gladiator"))
                                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.RED + "" + ChatColor.BOLD + "RAGE " + ChatColor.AQUA + "◆ ◆ ◆ ◆ ◆ ◆ ◆ ◆ ◆ ◆ "));
                                player.getWorld().playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 3, 0);


                            }
                        }.runTaskLater(classBattle, (long) gladiatorCooldown);

                        new BukkitRunnable() {

                            float timeLeft = gladiatorCooldown;

                            @Override
                            public void run() {

                                timeLeft -= 2;

                                if (timeLeft <= 0 || !ClassManager.CLASS_MAP.get(player).equals("gladiator")) {
                                    this.cancel();

                                }

                                int percent = 10 - (Math.round((timeLeft / gladiatorCooldown) * 10));

                                String bar = "◇ ◇ ◇ ◇ ◇ ◇ ◇ ◇ ◇ ◇";

                                for (int i = 0; i < percent; i++) {
                                    bar = "◆ " + bar;
                                }

                                bar = bar.substring(0, 19);


                                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.RED + "" + ChatColor.BOLD + "RAGE " + ChatColor.RESET + bar));

                            }
                        }.runTaskTimer(classBattle, 0L, 2L);


                    }
                    break;

                }
                case "soulreaper": {
                    if (!reaperPlayersOnCooldown.contains(player)) {

                        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "SUMMON " + ChatColor.WHITE + "◇ ◇ ◇ ◇ ◇ ◇ ◇ ◇ ◇ ◇ "));


                        if (player.getHealth() + 8 > player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue()) {
                            player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());
                        } else {
                            player.setHealth(player.getHealth() + 8);
                        }


                        reaperPlayersOnCooldown.add(player);


                        World world = player.getWorld();
                        Location loc = player.getLocation();

                        Location skel1loc = new Location(world, loc.getX() + 2, loc.getY() + 0.3, loc.getZ() - 3);
                        Location skel2loc = new Location(world, loc.getX() + 2, loc.getY() + 0.3, loc.getZ() + 3);
                        Location skel3loc = new Location(world, loc.getX() - 3, loc.getY() + 0.3, loc.getZ());
                        Location wskel1loc = new Location(world, loc.getX() - 2, loc.getY() + 0.3, loc.getZ() + 3);
                        Location wskel2loc = new Location(world, loc.getX() - 2, loc.getY() + 0.3, loc.getZ() - 3);
                        Location wskel3loc = new Location(world, loc.getX() + 3, loc.getY() + 0.3, loc.getZ());

                        LivingEntity e1 = (LivingEntity) world.spawnEntity(skel1loc, EntityType.SKELETON);
                        LivingEntity e2 = (LivingEntity) world.spawnEntity(skel2loc, EntityType.SKELETON);
                        LivingEntity e3 = (LivingEntity) world.spawnEntity(skel3loc, EntityType.SKELETON);


                        e1.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE,999,9,false,false));
                        e2.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE,999,9,false,false));
                        e3.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE,999,9,false,false));




                        LivingEntity le1 = (LivingEntity)world.spawnEntity(wskel1loc, EntityType.WITHER_SKELETON);
                        LivingEntity le2 = (LivingEntity)world.spawnEntity(wskel2loc, EntityType.WITHER_SKELETON);
                        LivingEntity le3 = (LivingEntity) world.spawnEntity(wskel3loc, EntityType.WITHER_SKELETON);

                        le1.setHealth(2);
                        le2.setHealth(2);
                        le3.setHealth(2);


                        ArrayList<Location> locs = new ArrayList<>(Arrays.asList(skel1loc, skel2loc, skel3loc, wskel1loc, wskel2loc, wskel3loc));

                        for (Location l : locs) {
                            player.spawnParticle(Particle.GLOW_SQUID_INK, l.add(0, -1, 0), 100, 0.3, 0, 0.3, 0.3);


                        }

                        player.getWorld().playSound(player.getLocation(), Sound.BLOCK_END_PORTAL_SPAWN, 1, 1);


                        new BukkitRunnable() {

                            @Override
                            public void run() {
                                reaperPlayersOnCooldown.remove(player);
                                if (ClassManager.CLASS_MAP.get(player).equals("soulreaper"))
                                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "SUMMON " + ChatColor.AQUA + "◆ ◆ ◆ ◆ ◆ ◆ ◆ ◆ ◆ ◆ "));
                                player.getWorld().playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 2, 0);


                            }
                        }.runTaskLater(classBattle, (long) reaperCooldown);

                        new BukkitRunnable() {

                            float timeLeft = reaperCooldown;

                            @Override
                            public void run() {

                                timeLeft -= 2;

                                if (timeLeft <= 0 || !ClassManager.CLASS_MAP.get(player).equals("soulreaper")) {
                                    this.cancel();
                                }

                                int percent = 10 - (Math.round((timeLeft / reaperCooldown) * 10));

                                String bar = "◇ ◇ ◇ ◇ ◇ ◇ ◇ ◇ ◇ ◇";

                                for (int i = 0; i < percent; i++) {
                                    bar = "◆ " + bar;
                                }

                                bar = bar.substring(0, 19);

                                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "SUMMON " + ChatColor.RESET + bar));

                            }
                        }.runTaskTimer(classBattle, 0L, 2L);
                    }
                    break;

                }
                case "firewalker": {
                    if (!firePlayersOnCooldown.contains(player)) {

                        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GOLD + "" + ChatColor.BOLD + "MAGMA " + ChatColor.WHITE + "◇ ◇ ◇ ◇ ◇ ◇ ◇ ◇ ◇ ◇ "));


                        HashSet<Material> passThroughMaterials = new HashSet<>(Arrays.asList(Material.AIR, Material.WATER, Material.TALL_GRASS, Material.GRASS));
                        Location target = player.getTargetBlock((Set<Material>) passThroughMaterials, 10).getLocation();
                        target = target.add(0, 1, 0);

                        target.getBlock().setType(Material.LAVA);

                        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_BLAZE_SHOOT, 2, 1);

                        player.spawnParticle(Particle.SMALL_FLAME, target.add(0, 0, 0), 200, 0.5, 0.5, 0.5, 0.1);


                        firePlayersOnCooldown.add(player);


                        new BukkitRunnable() {

                            @Override
                            public void run() {
                                firePlayersOnCooldown.remove(player);
                                if (ClassManager.CLASS_MAP.get(player).equals("firewalker"))
                                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GOLD + "" + ChatColor.BOLD + "MAGMA " + ChatColor.AQUA + "◆ ◆ ◆ ◆ ◆ ◆ ◆ ◆ ◆ ◆ "));
                                player.getWorld().playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 2, 0);


                            }
                        }.runTaskLater(classBattle, (long) fireCooldown);

                        new BukkitRunnable() {

                            float timeLeft = fireCooldown;

                            @Override
                            public void run() {

                                timeLeft -= 2;

                                if (timeLeft <= 0 || !ClassManager.CLASS_MAP.get(player).equals("firewalker")) {
                                    this.cancel();
                                }

                                int percent = 10 - (Math.round((timeLeft / fireCooldown) * 10));

                                String bar = "◇ ◇ ◇ ◇ ◇ ◇ ◇ ◇ ◇ ◇";

                                for (int i = 0; i < percent; i++) {
                                    bar = "◆ " + bar;
                                }

                                bar = bar.substring(0, 19);

                                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GOLD + "" + ChatColor.BOLD + "MAGMA " + ChatColor.RESET + bar));


                            }
                        }.runTaskTimer(classBattle, 0L, 2L);
                    }
                    break;

                }
                case "endassassin": {

                    if (!assPlayersOnCooldown.contains(player)) {

                        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "ASSASSINATE " + ChatColor.WHITE + "◇ ◇ ◇ ◇ ◇ ◇ ◇ ◇ ◇ ◇ "));


                        ArrayList<Player> others = new ArrayList<>(Bukkit.getOnlinePlayers());

                        others.remove(player);

                        if (others.size() > 0) {


                            Bukkit.getLogger().warning(others.toString());


                            Player enemy = others.get(0);

                            for (Player p : others) {

                                if (p.getLocation().distance(player.getLocation()) < enemy.getLocation().distance(player.getLocation())) {
                                    enemy = p;
                                }

                            }

                            player.teleport(enemy.getLocation());

                            player.spawnParticle(Particle.FLASH, player.getLocation().add(0, 1, 0), 5, 0.5, 0.5, 0.5);


                            enemy.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 60, 9));

                            enemy.addPotionEffect((new PotionEffect(PotionEffectType.SLOW, 60, 0)));

                        }

                        player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 120, 0));


                        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 2, 1);

                        player.spawnParticle(Particle.DRAGON_BREATH, player.getLocation().add(0, 1, 0), 100, 0.3, 0.3, 0.3);


                        assPlayersOnCooldown.add(player);


                        new BukkitRunnable() {

                            @Override
                            public void run() {
                                assPlayersOnCooldown.remove(player);
                                if (ClassManager.CLASS_MAP.get(player).equals("endassassin"))
                                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "ASSASSINATE " + ChatColor.AQUA + "◆ ◆ ◆ ◆ ◆ ◆ ◆ ◆ ◆ ◆ "));
                                player.getWorld().playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 2, 0);


                            }
                        }.runTaskLater(classBattle, (long) assCooldown);

                        new BukkitRunnable() {

                            float timeLeft = assCooldown;

                            @Override
                            public void run() {

                                timeLeft -= 2;

                                if (timeLeft <= 0 || !ClassManager.CLASS_MAP.get(player).equals("endassassin")) {
                                    this.cancel();
                                }

                                int percent = 10 - (Math.round((timeLeft / assCooldown) * 10));

                                String bar = "◇ ◇ ◇ ◇ ◇ ◇ ◇ ◇ ◇ ◇";

                                for (int i = 0; i < percent; i++) {
                                    bar = "◆ " + bar;
                                }

                                bar = bar.substring(0, 19);

                                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "ASSASSINATE " + ChatColor.RESET + bar));

                            }
                        }.runTaskTimer(classBattle, 0L, 2L);
                    }
                    break;

                }
                case "bountyhunter": {

                    if (!bountPlayersOnCooldown.contains(player)) {

                        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "RAPIDBOW " + ChatColor.WHITE + "◇ ◇ ◇ ◇ ◇ ◇ ◇ ◇ ◇ ◇ "));


                        ItemStack crossbow = ItemBuilderUtil.generateItemWithMeta(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "Bounty Hunter's Crossbow", Material.CROSSBOW, 1, null, null, Enchantment.PIERCING, 2);

                        CrossbowMeta crossmeta = (CrossbowMeta) crossbow.getItemMeta();
                        crossmeta.setChargedProjectiles(Arrays.asList(new ItemStack(Material.ARROW, 1)));
                        crossbow.setItemMeta(crossmeta);


                        BukkitTask task = new BukkitRunnable() {


                            @Override
                            public void run() {


                                player.getInventory().setItem(1, crossbow);


                            }
                        }.runTaskTimer(classBattle, 0L, 8L);


                        new BukkitRunnable() {


                            @Override
                            public void run() {
                                task.cancel();
                            }
                        }.runTaskLater(classBattle, 80L);

                        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_VILLAGER_WORK_FLETCHER, 2, 1);
                        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_VILLAGER_WORK_FLETCHER, 2, 1);
                        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_VILLAGER_WORK_FLETCHER, 2, 1);


                        player.spawnParticle(Particle.COMPOSTER, player.getLocation().add(0, 1, 0), 100, 0.5, 0.5, 0.5);


                        bountPlayersOnCooldown.add(player);


                        new BukkitRunnable() {

                            @Override
                            public void run() {
                                bountPlayersOnCooldown.remove(player);
                                if (ClassManager.CLASS_MAP.get(player).equals("bountyhunter"))
                                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "RAPIDBOW " + ChatColor.AQUA + "◆ ◆ ◆ ◆ ◆ ◆ ◆ ◆ ◆ ◆ "));
                                player.getWorld().playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 2, 0);


                            }
                        }.runTaskLater(classBattle, bountCooldown);

                        new BukkitRunnable() {

                            float timeLeft = bountCooldown;

                            @Override
                            public void run() {

                                timeLeft -= 2;

                                if (timeLeft <= 0 || !ClassManager.CLASS_MAP.get(player).equals("bountyhunter")) {
                                    this.cancel();
                                }

                                int percent = 10 - (Math.round((timeLeft / bountCooldown) * 10));

                                String bar = "◇ ◇ ◇ ◇ ◇ ◇ ◇ ◇ ◇ ◇";

                                for (int i = 0; i < percent; i++) {
                                    bar = "◆ " + bar;
                                }

                                bar = bar.substring(0, 19);

                                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "RAPIDBOW " + ChatColor.RESET + bar));

                            }
                        }.runTaskTimer(classBattle, 0L, 2L);
                    }
                    break;

                }
                case "botanist": {

                    if (!botPlayersOnCooldown.contains(player)) {

                        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GREEN + "" + ChatColor.BOLD + "BLESS " + ChatColor.WHITE + "◇ ◇ ◇ ◇ ◇ ◇ ◇ ◇ ◇ ◇ "));

                        World world = player.getWorld();
                        Location loc = player.getLocation();

                        Location cac1 = new Location(world, loc.getX() + 1, loc.getY() - 1, loc.getZ() - 2);
                        Location cac2 = new Location(world, loc.getX() + 1, loc.getY() - 1, loc.getZ() + 2);
                        Location cac3 = new Location(world, loc.getX() - 2, loc.getY() - 1, loc.getZ());
                        Location cac4 = new Location(world, loc.getX() - 1, loc.getY() - 1, loc.getZ() + 2);
                        Location cac5 = new Location(world, loc.getX() - 1, loc.getY() - 1, loc.getZ() - 2);
                        Location cac6 = new Location(world, loc.getX() + 2, loc.getY() - 1, loc.getZ());

                        ArrayList<Location> locs = new ArrayList<>(Arrays.asList(cac1, cac2, cac3, cac4, cac5, cac6));

                        for (Location l : locs) {

                            l.getBlock().setType(Material.SAND);
                            l.add(0, 1, 0).getBlock().setType(Material.CACTUS);
                            l.add(0, 1, 0).getBlock().setType(Material.CACTUS);


                            player.spawnParticle(Particle.FALLING_SPORE_BLOSSOM, l.add(0, 1, 0), 100, 0.5, 0, 0.5, 1);


                        }


                        player.spawnParticle(Particle.COMPOSTER, player.getLocation().add(0, 1, 0), 100, 0.5, 0.5, 0.5);


                        botPlayersOnCooldown.add(player);


                        new BukkitRunnable() {

                            @Override
                            public void run() {
                                botPlayersOnCooldown.remove(player);
                                if (ClassManager.CLASS_MAP.get(player).equals("botanist"))
                                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GREEN + "" + ChatColor.BOLD + "BLESS " + ChatColor.AQUA + "◆ ◆ ◆ ◆ ◆ ◆ ◆ ◆ ◆ ◆ "));
                                player.getWorld().playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 2, 0);


                            }
                        }.runTaskLater(classBattle, botCooldown);

                        new BukkitRunnable() {

                            float timeLeft = botCooldown;

                            @Override
                            public void run() {

                                timeLeft -= 2;

                                if (timeLeft <= 0 || !ClassManager.CLASS_MAP.get(player).equals("botanist")) {
                                    this.cancel();
                                }

                                int percent = 10 - (Math.round((timeLeft / botCooldown) * 10));

                                String bar = "◇ ◇ ◇ ◇ ◇ ◇ ◇ ◇ ◇ ◇";

                                for (int i = 0; i < percent; i++) {
                                    bar = "◆ " + bar;
                                }

                                bar = bar.substring(0, 19);

                                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GREEN + "" + ChatColor.BOLD + "BLESS " + ChatColor.RESET + bar));

                            }
                        }.runTaskTimer(classBattle, 0L, 2L);
                    }
                    break;

                }
                case "scholar": {

                    if (!scholarPlayersOnCooldown.contains(player)) {


                        if (player.getInventory().getItemInMainHand().getType() == Material.ENCHANTED_BOOK && !player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.BLUE + "" + ChatColor.BOLD + "Forbidden Scholar's Book of Blades")) {

                            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.BLUE + "" + ChatColor.BOLD + "SCROLL " + ChatColor.WHITE + "◇ ◇ ◇ ◇ ◇ ◇ ◇ ◇ ◇ ◇ "));


                            if (player.getInventory().getItemInMainHand().getItemMeta().hasEnchant(Enchantment.ARROW_INFINITE)) {


                                Random random = new Random();

                                HashSet<Material> passThroughMaterials = new HashSet<>(Arrays.asList(Material.AIR, Material.WATER, Material.TALL_GRASS, Material.GRASS));
                                Location target = player.getTargetBlock((Set<Material>) passThroughMaterials, 10).getLocation();
                                target = target.add(0, 40, 0);


                                for (int i=0;i<30;i++) {

                                    Location l = new Location(target.getWorld(),target.getX() + (random.nextDouble(-10,21)/5.0),target.getY(), target.getZ() + (random.nextDouble(-10, 21) / 5.0));

                                    Entity e =target.getWorld().spawnEntity(l, EntityType.ARROW);
                                    Arrow arrow = (Arrow) e;
                                    arrow.addCustomEffect(new PotionEffect(PotionEffectType.HARM,1,1),false);
                                    e.setVelocity(new Vector(e.getVelocity().getX(),-2,e.getVelocity().getZ()));
                                }


                            } else if(player.getInventory().getItemInMainHand().getItemMeta().hasEnchant(Enchantment.PROTECTION_FALL)){

                                Random random = new Random();

                                HashSet<Material> passThroughMaterials = new HashSet<>(Arrays.asList(Material.AIR, Material.WATER, Material.TALL_GRASS, Material.GRASS));
                                Location target = player.getTargetBlock((Set<Material>) passThroughMaterials, 15).getLocation();
                                target = new Location(target.getWorld(), target.getX(),target.getWorld().getHighestBlockYAt(target.getBlockX(), target.getBlockZ()),target.getZ());

                                World world = target.getWorld();

                                Location corn = new Location(world, target.getX(), target.getY(), target.getZ());
                                Location corn1 = new Location(world, target.getX() + 1, target.getY(), target.getZ() + 1);
                                Location corn2 = new Location(world, target.getX() + 1, target.getY() , target.getZ() - 1);
                                Location corn3 = new Location(world, target.getX() - 1, target.getY(), target.getZ() + 1);
                                Location corn4 = new Location(world, target.getX() - 1, target.getY(), target.getZ() - 1);
                                Location edge1 = new Location(world, target.getX() + 1, target.getY(), target.getZ());
                                Location edge2 = new Location(world, target.getX() - 1, target.getY(), target.getZ());
                                Location edge3 = new Location(world, target.getX(), target.getY(), target.getZ() - 1);
                                Location edge4 = new Location(world, target.getX(), target.getY(), target.getZ() + 1);


                                ArrayList<Location> locs = new ArrayList<>(Arrays.asList(corn, corn1, corn2, corn3, corn4, edge1, edge2, edge3, edge4));

                                for (Location l : locs) {

                                    l.getBlock().setType(Material.AIR);
                                    l.add(1,0,0).getBlock().setType(Material.AIR);
                                    for (int y=0;y<6;y++) {
                                        l.add(0,-1,0).getBlock().setType(Material.AIR);
                                    }

                                    l.getBlock().setType(Material.POINTED_DRIPSTONE,false);

                                    if (random.nextInt(0,4) > 2) {
                                        l.add(0,1,0).getBlock().setType(Material.POINTED_DRIPSTONE);
                                        l.add(0,-1,0);
                                    }
                                    l.add(0,6,1).getBlock().setType(Material.AIR);
                                    for (int y=0;y<6;y++) {
                                        l.add(0,-1,0).getBlock().setType(Material.AIR);
                                    }

                                    l.getBlock().setType(Material.POINTED_DRIPSTONE,false);
                                    if (random.nextInt(0,4) > 2) {
                                        l.add(0,1,0).getBlock().setType(Material.POINTED_DRIPSTONE);
                                        l.add(0,-1,0);
                                    }
                                    l.add(0,6,-2).getBlock().setType(Material.AIR);
                                    for (int y=0;y<6;y++) {
                                        l.add(0,-1,0).getBlock().setType(Material.AIR);
                                        
                                    }

                                    l.getBlock().setType(Material.POINTED_DRIPSTONE,false);
                                    if (random.nextInt(0,4) > 2) {
                                        l.add(0,1,0).getBlock().setType(Material.POINTED_DRIPSTONE);
                                        l.add(0,-1,0);
                                    }
                                    
                                    
                                    l.add(-1,6,0).getBlock().setType(Material.AIR);
                                    for (int y=0;y<6;y++) {
                                        l.add(0,-1,0).getBlock().setType(Material.AIR);
                                    }

                                    l.getBlock().setType(Material.POINTED_DRIPSTONE,false);
                                    if (random.nextInt(0,4) > 2) {
                                        l.add(0,1,0).getBlock().setType(Material.POINTED_DRIPSTONE);
                                        l.add(0,-1,0);
                                    }
                                    l.add(0,6,1).getBlock().setType(Material.AIR);
                                    for (int y=0;y<6;y++) {
                                        l.add(0,-1,0).getBlock().setType(Material.AIR);
                                    }

                                    l.getBlock().setType(Material.POINTED_DRIPSTONE,false);
                                    if (random.nextInt(0,4) > 2) {
                                        l.add(0,1,0).getBlock().setType(Material.POINTED_DRIPSTONE);
                                        l.add(0,-1,0);
                                    }
                                    l.add(0,6,1).getBlock().setType(Material.AIR);
                                    for (int y=0;y<6;y++) {
                                        l.add(0,-1,0).getBlock().setType(Material.AIR);
                                    }

                                    l.getBlock().setType(Material.POINTED_DRIPSTONE,false);
                                    if (random.nextInt(0,4) > 2) {
                                        l.add(0,1,0).getBlock().setType(Material.POINTED_DRIPSTONE);
                                        l.add(0,-1,0);
                                    }
                                    l.add(-1,6,0).getBlock().setType(Material.AIR);
                                    for (int y=0;y<6;y++) {
                                        l.add(0,-1,0).getBlock().setType(Material.AIR);
                                    }

                                    l.getBlock().setType(Material.POINTED_DRIPSTONE,false);
                                    if (random.nextInt(0,4) > 2) {
                                        l.add(0,1,0).getBlock().setType(Material.POINTED_DRIPSTONE);
                                        l.add(0,-1,0);
                                    }

                                    l.add(0,6,-1).getBlock().setType(Material.AIR);
                                    for (int y=0;y<6;y++) {
                                        l.add(0,-1,0).getBlock().setType(Material.AIR);
                                    }

                                    l.getBlock().setType(Material.POINTED_DRIPSTONE,false);
                                    if (random.nextInt(0,4) > 2) {
                                        l.add(0,1,0).getBlock().setType(Material.POINTED_DRIPSTONE);
                                        l.add(0,-1,0);
                                    }
                                    l.add(0,6,-1).getBlock().setType(Material.AIR);
                                    for (int y=0;y<6;y++) {
                                        l.add(0,-1,0).getBlock().setType(Material.AIR);
                                    }

                                    l.getBlock().setType(Material.POINTED_DRIPSTONE,false);
                                    if (random.nextInt(0,4) > 2) {
                                        l.add(0,1,0).getBlock().setType(Material.POINTED_DRIPSTONE);
                                        l.add(0,-1,0);
                                    }
                                    l.add(1,6,1).getBlock().setType(Material.AIR);
                                    for (int y=0;y<6;y++) {
                                        l.add(0,-1,0).getBlock().setType(Material.AIR);
                                    }

                                    l.getBlock().setType(Material.POINTED_DRIPSTONE,false);
                                    if (random.nextInt(0,4) > 2) {
                                        l.add(0,1,0).getBlock().setType(Material.POINTED_DRIPSTONE);
                                        l.add(0,-1,0);
                                    }

                                    for (int i = 0;i<15;i++) {
                                        l.add(random.nextInt(-1,2),6,random.nextInt(-1,2)).getBlock().setType(Material.AIR);
                                        for (int y=0;y<6;y++) {
                                            l.add(0,-1,0).getBlock().setType(Material.AIR);
                                        }

                                        l.getBlock().setType(Material.POINTED_DRIPSTONE,false);
                                        if (random.nextInt(0,4) > 2) {
                                            l.add(0,1,0).getBlock().setType(Material.POINTED_DRIPSTONE);
                                            l.add(0,-1,0);
                                        }
                                    }

                                }


                            } else if(player.getInventory().getItemInMainHand().getItemMeta().hasEnchant(Enchantment.DAMAGE_UNDEAD)) {

                                player.setHealth(0.5);
                                player.addPotionEffect(new PotionEffect(PotionEffectType.BAD_OMEN,9999,9999));
                                player.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING,9999,9999));


                                for (Player p: Bukkit.getOnlinePlayers()) {

                                    p.sendMessage(player.getDisplayName() + " is at 1 heart! Kill him in 30 seconds or be killed!");

                                }

                                new BukkitRunnable() {

                                    @Override
                                    public void run() {


                                        if (player.hasPotionEffect(PotionEffectType.BAD_OMEN)) {

                                            for (Player p: Bukkit.getOnlinePlayers()) {

                                                p.sendMessage("You didn't kill the scholar! Die!");

                                                if (!p.equals(player)) {
                                                    p.damage(1000000);
                                                }

                                            }

                                        }


                                    }
                                }.runTaskLater(classBattle, 600L);



                            }




                            //player.getWorld().playSound(player.getLocation(), Sound.ENTITY_BLAZE_SHOOT, 2, 1);

                            //player.spawnParticle(Particle.SMALL_FLAME, target.add(0, 0, 0), 200, 0.5, 0.5, 0.5, 0.1)


                            scholarPlayersOnCooldown.add(player);


                            new BukkitRunnable() {

                                @Override
                                public void run() {
                                    scholarPlayersOnCooldown.remove(player);
                                    if (ClassManager.CLASS_MAP.get(player).equals("scholar"))
                                        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.BLUE + "" + ChatColor.BOLD + "SCROLL " + ChatColor.AQUA + "◆ ◆ ◆ ◆ ◆ ◆ ◆ ◆ ◆ ◆ "));
                                    player.getWorld().playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 2, 0);


                                }
                            }.runTaskLater(classBattle, scholarCooldown);

                            new BukkitRunnable() {

                                float timeLeft = scholarCooldown;

                                @Override
                                public void run() {

                                    timeLeft -= 2;

                                    if (timeLeft <= 0 || !ClassManager.CLASS_MAP.get(player).equals("scholar")) {
                                        this.cancel();
                                    }

                                    int percent = 10 - (Math.round((timeLeft / scholarCooldown) * 10));

                                    String bar = "◇ ◇ ◇ ◇ ◇ ◇ ◇ ◇ ◇ ◇";

                                    for (int i = 0; i < percent; i++) {
                                        bar = "◆ " + bar;
                                    }

                                    bar = bar.substring(0, 19);

                                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.BLUE + "" + ChatColor.BOLD + "SCROLL " + ChatColor.RESET + bar));

                                }
                            }.runTaskTimer(classBattle, 0L, 2L);
                        }
                    }
                    break;

                }
                case "poacher": {

                    if (!poacherPlayersOnCooldown.contains(player)) {

                        if (ClassManager.POACHER_MAP.containsKey(player)) {

                            switch (ClassManager.POACHER_MAP.get(player)) {
                                case WARDEN: {

                                    player.getWorld().playSound(player.getLocation(),Sound.ENTITY_WARDEN_SONIC_CHARGE,2,0);
                                    player.getWorld().playSound(player.getLocation(),Sound.ENTITY_WARDEN_AGITATED,2,0);


                                    new BukkitRunnable() {

                                        @Override
                                        public void run() {
                                            player.getWorld().playSound(player.getLocation(),Sound.ENTITY_WARDEN_SONIC_BOOM,2,0);

                                            player.getWorld().playSound(player.getLocation(),Sound.ENTITY_WARDEN_SONIC_BOOM,2,0);
                                            Location start = player.getEyeLocation();
                                            Vector direction = start.getDirection();
                                            direction.multiply(15);
                                            Location destination = start.clone().add(direction);
                                            direction.normalize();
                                            for (int i = 0;i < 15; i++) {
                                                Location l = start.add(direction);
                                                l.getWorld().spawnParticle(Particle.SONIC_BOOM,l,1);
                                                for (Entity p: player.getNearbyEntities(100,100,100)) {
                                                    if (p!= player && p instanceof LivingEntity && p.getLocation().distance(l) <= 2) {
                                                        ((LivingEntity)p).damage(15);
                                                        p.setVelocity(p.getVelocity().multiply(-2));

                                                    }
                                                }
                                            }
                                        }
                                    }.runTaskLater(classBattle,40L);



                                    break;
                                }
                                case ENDERMAN: {
                                    HashSet<Material> passThroughMaterials = new HashSet<>(Arrays.asList(Material.AIR, Material.WATER, Material.TALL_GRASS, Material.GRASS));
                                    Location target = player.getTargetBlock((Set<Material>) passThroughMaterials, 20).getLocation();

                                    if (target.getBlock().getType() == Material.AIR) {

                                        target = new Location(target.getWorld(), target.getX(),target.getWorld().getHighestBlockYAt(target),target.getZ());

                                    }

                                    target = target.add(0, 1, 0);

                                    Float[] direction = {player.getLocation().getYaw(),player.getLocation().getPitch()};

                                    player.teleport(target);

                                    player.setRotation(direction[0],direction[1]);

                                    player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 2, 1);

                                    player.spawnParticle(Particle.DRAGON_BREATH, target.add(0, 0, 0), 50, 0.5, 0.5, 0.5, 0.1);


                                    for (int i=0;i<5;i++) {
                                        player.getWorld().spawnEntity(player.getLocation(),EntityType.ENDERMITE);

                                    }

                                    break;



                                }
                                case BLAZE: {

                                    Location eye = player.getEyeLocation();
                                    Location loc = eye.add(eye.getDirection().multiply(1.2));
                                    Fireball fireball = (Fireball) loc.getWorld().spawnEntity(loc, EntityType.FIREBALL);
                                    fireball.setVelocity(loc.getDirection().normalize().multiply(0.75));
                                    fireball.setShooter(player);
                                    fireball.setIsIncendiary(true);
                                    fireball.setVisualFire(true);
                                    fireball.setShooter(player);
                                    fireball.setYield(3);

                                    break;




                                }
                            }



                        }

                        poacherPlayersOnCooldown.add(player);


                        new BukkitRunnable() {

                            @Override
                            public void run() {
                                poacherPlayersOnCooldown.remove(player);
                                if (ClassManager.CLASS_MAP.get(player).equals("poacher"))
                                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.DARK_RED + "" + ChatColor.BOLD + "MIMIC " + ChatColor.AQUA + "◆ ◆ ◆ ◆ ◆ ◆ ◆ ◆ ◆ ◆ "));
                                player.getWorld().playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 2, 0);


                            }
                        }.runTaskLater(classBattle, poacherCooldown);

                        new BukkitRunnable() {

                            float timeLeft = poacherCooldown;

                            @Override
                            public void run() {

                                timeLeft -= 2;

                                if (timeLeft <= 0 || !ClassManager.CLASS_MAP.get(player).equals("poacher")) {
                                    this.cancel();
                                }

                                int percent = 10 - (Math.round((timeLeft / poacherCooldown) * 10));

                                String bar = "◇ ◇ ◇ ◇ ◇ ◇ ◇ ◇ ◇ ◇";

                                for (int i = 0; i < percent; i++) {
                                    bar = "◆ " + bar;
                                }

                                bar = bar.substring(0, 19);

                                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.DARK_RED + "" + ChatColor.BOLD + "MIMIC " + ChatColor.RESET + bar));

                            }
                        }.runTaskTimer(classBattle, 0L, 2L);


                    }
                    break;

                }
                case "frost": {

                    if (!frostPlayersOnCooldown.contains(player)) {

                        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GREEN + "" + ChatColor.BOLD + "BLESS " + ChatColor.WHITE + "◇ ◇ ◇ ◇ ◇ ◇ ◇ ◇ ◇ ◇ "));

                        World world = player.getWorld();
                        Location loc = player.getLocation();

                        for(Player p : Bukkit.getOnlinePlayers()){
                            if(p != player){
                                p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,100,999));

                            }
                        }
                        frostPlayersOnCooldown.add(player);


                        new BukkitRunnable() {

                            @Override
                            public void run() {
                                frostPlayersOnCooldown.remove(player);
                                if (ClassManager.CLASS_MAP.get(player).equals("frost"))
                                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.AQUA + "" + ChatColor.BOLD + "FREEZE " + ChatColor.AQUA + "◆ ◆ ◆ ◆ ◆ ◆ ◆ ◆ ◆ ◆ "));
                                player.getWorld().playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 2, 0);


                            }
                        }.runTaskLater(classBattle, frostCooldown);

                        new BukkitRunnable() {

                            float timeLeft = botCooldown;

                            @Override
                            public void run() {

                                timeLeft -= 2;

                                if (timeLeft <= 0 || !ClassManager.CLASS_MAP.get(player).equals("botanist")) {
                                    this.cancel();
                                }

                                int percent = 10 - (Math.round((timeLeft / botCooldown) * 10));

                                String bar = "◇ ◇ ◇ ◇ ◇ ◇ ◇ ◇ ◇ ◇";

                                for (int i = 0; i < percent; i++) {
                                    bar = "◆ " + bar;
                                }

                                bar = bar.substring(0, 19);

                                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GREEN + "" + ChatColor.BOLD + "BLESS " + ChatColor.RESET + bar));

                            }
                        }.runTaskTimer(classBattle, 0L, 2L);
                    }
                    break;

                }
            }


        } else if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && ClassManager.CLASS_MAP.containsKey(player)) {

            switch (ClassManager.CLASS_MAP.get(player)) {
                case "scholar": {

                    ArrayList<ItemStack> books = new ArrayList<>();
                    Random random = new Random();


                    books.add(ItemBuilderUtil.generateItemWithMeta(ChatColor.BLUE + "" + ChatColor.BOLD + "Book of Raining Arrows", Material.ENCHANTED_BOOK, 1, null, null, Enchantment.ARROW_INFINITE, 1));
                    books.add(ItemBuilderUtil.generateItemWithMeta(ChatColor.BLUE + "" + ChatColor.BOLD + "Book of Falling", Material.ENCHANTED_BOOK, 1, null, null, Enchantment.PROTECTION_FALL, 1));
                    books.add(ItemBuilderUtil.generateItemWithMeta(ChatColor.BLUE + "" + ChatColor.BOLD + "Book of Suicide", Material.ENCHANTED_BOOK, 1, null, null, Enchantment.DAMAGE_UNDEAD, 1));


                    if (event.getClickedBlock().getType() == Material.BOOKSHELF) {
                        event.getClickedBlock().setType(Material.BEEHIVE);
                        player.closeInventory();
                        player.getWorld().dropItem(player.getLocation(),books.get(random.nextInt(books.size())));

                    }
                    break;
                }
                case "botanist": {

                    if (!botPlayersOnCooldown.contains(player)) {

                        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GREEN + "" + ChatColor.BOLD + "BLESS " + ChatColor.WHITE + "◇ ◇ ◇ ◇ ◇ ◇ ◇ ◇ ◇ ◇ "));

                        World world = player.getWorld();
                        Location loc = player.getLocation();

                        Location cac1 = new Location(world, loc.getX() + 1, loc.getY() - 1, loc.getZ() - 2);
                        Location cac2 = new Location(world, loc.getX() + 1, loc.getY() - 1, loc.getZ() + 2);
                        Location cac3 = new Location(world, loc.getX() - 2, loc.getY() - 1, loc.getZ());
                        Location cac4 = new Location(world, loc.getX() - 1, loc.getY() - 1, loc.getZ() + 2);
                        Location cac5 = new Location(world, loc.getX() - 1, loc.getY() - 1, loc.getZ() - 2);
                        Location cac6 = new Location(world, loc.getX() + 2, loc.getY() - 1, loc.getZ());

                        ArrayList<Location> locs = new ArrayList<>(Arrays.asList(cac1, cac2, cac3, cac4, cac5, cac6));

                        for (Location l : locs) {

                            l.getBlock().setType(Material.SAND);
                            l.add(0, 1, 0).getBlock().setType(Material.CACTUS);
                            l.add(0, 1, 0).getBlock().setType(Material.CACTUS);


                            player.spawnParticle(Particle.FALLING_SPORE_BLOSSOM, l.add(0, 1, 0), 100, 0.5, 0, 0.5, 1);


                        }


                        player.spawnParticle(Particle.COMPOSTER, player.getLocation().add(0, 1, 0), 100, 0.5, 0.5, 0.5);


                        botPlayersOnCooldown.add(player);


                        new BukkitRunnable() {

                            @Override
                            public void run() {
                                botPlayersOnCooldown.remove(player);
                                if (ClassManager.CLASS_MAP.get(player).equals("botanist"))
                                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GREEN + "" + ChatColor.BOLD + "BLESS " + ChatColor.AQUA + "◆ ◆ ◆ ◆ ◆ ◆ ◆ ◆ ◆ ◆ "));
                                player.getWorld().playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 2, 0);


                            }
                        }.runTaskLater(classBattle, botCooldown);

                        new BukkitRunnable() {

                            float timeLeft = botCooldown;

                            @Override
                            public void run() {

                                timeLeft -= 2;

                                if (timeLeft <= 0 || !ClassManager.CLASS_MAP.get(player).equals("botanist")) {
                                    this.cancel();
                                }

                                int percent = 10 - (Math.round((timeLeft / botCooldown) * 10));

                                String bar = "◇ ◇ ◇ ◇ ◇ ◇ ◇ ◇ ◇ ◇";

                                for (int i = 0; i < percent; i++) {
                                    bar = "◆ " + bar;
                                }

                                bar = bar.substring(0, 19);

                                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GREEN + "" + ChatColor.BOLD + "BLESS " + ChatColor.RESET + bar));

                            }
                        }.runTaskTimer(classBattle, 0L, 2L);
                    }
                    break;

                }
            }


        }



    }


}


