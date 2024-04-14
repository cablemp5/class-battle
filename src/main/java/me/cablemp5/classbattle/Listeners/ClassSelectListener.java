package me.cablemp5.classbattle.Listeners;

import me.cablemp5.classbattle.ClassBattle;
import me.cablemp5.classbattle.ClassManager;
import me.cablemp5.classbattle.utils.ItemBuilderUtil;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;


public class ClassSelectListener implements Listener {

    private final ClassBattle classBattle;

    public ClassSelectListener(ClassBattle classBattle) {
        this.classBattle = classBattle;
    }

    @EventHandler
    public void onMenuClick(InventoryClickEvent event) {

        if (event.getView().getTitle().equalsIgnoreCase("Classes")) {

            Player p = (Player) event.getWhoClicked();


            if (event.getCurrentItem() != null) {

                p.getInventory().clear();
                for (PotionEffectType t : PotionEffectType.values()) {
                    p.removePotionEffect(t);
                }
                p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
                p.setHealth(20);
                p.setFoodLevel(20);
                ClassManager.CLASS_MAP.remove(p);
                ClassManager.POACHER_MAP.remove(p);


                switch (event.getCurrentItem().getType()) {
                    case IRON_SWORD: {


                        ClassManager.CLASS_MAP.put(p, "gladiator");

                        p.getInventory().addItem(ItemBuilderUtil.generateItemWithMeta(ChatColor.RED + "" + ChatColor.BOLD + "Gladiator's Sword", Material.STONE_SWORD, 1, null, null, null, 0));
                        p.getInventory().addItem(ItemBuilderUtil.generateItemWithMeta(ChatColor.RED + "" + ChatColor.BOLD + "Gladiator's Fishing Rod", Material.FISHING_ROD, 1, null, null, null, 0));

                        p.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE, 3));
                        p.getInventory().setItem(8, new ItemStack(Material.COOKED_BEEF, 10));

                        p.getInventory().setHelmet(ItemBuilderUtil.generateItemWithMeta(ChatColor.RED + "" + ChatColor.BOLD + "Gladiator's Helmet", Material.GOLDEN_HELMET, 1, null, null, null, 0));
                        p.getInventory().setChestplate(ItemBuilderUtil.generateItemWithMeta(ChatColor.RED + "" + ChatColor.BOLD + "Gladiator's Chestplate", Material.CHAINMAIL_CHESTPLATE, 1, null, null, null, 0));
                        p.getInventory().setLeggings(ItemBuilderUtil.generateItemWithMeta(ChatColor.RED + "" + ChatColor.BOLD + "Gladiator's Leggings", Material.CHAINMAIL_LEGGINGS, 1, null, null, null, 0));
                        p.getInventory().setBoots(ItemBuilderUtil.generateItemWithMeta(ChatColor.RED + "" + ChatColor.BOLD + "Gladiator's Boots", Material.GOLDEN_BOOTS, 1, null, null, null, 0));

                        p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
                        p.setHealth(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());

                        p.getInventory().addItem(new ItemStack(Material.DIRT, 64));

                        break;


                    }
                    case NETHERITE_HOE: {

                        ClassManager.CLASS_MAP.put(p, "soulreaper");

                        ItemStack hoe = ItemBuilderUtil.generateItemWithMeta(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Soul Reaper's Scythe", Material.NETHERITE_HOE, 1, null, null, null, 0);
                        ItemMeta hoemeta = hoe.getItemMeta();

                        hoemeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(new UUID(4544545, 3453), "attackDamage", 4, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
                        hoemeta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(new UUID(45445455, 3457), "attackSpeed", -1.5, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
                        hoe.setItemMeta(hoemeta);
                        p.getInventory().addItem(hoe);

                        p.getInventory().addItem(ItemBuilderUtil.generateItemWithMeta(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Soul Reaper's Skulls", Material.WITHER_SKELETON_SKULL, 5, null, null, null, 0));

                        p.getInventory().setItem(8, new ItemStack(Material.ROTTEN_FLESH, 20));
                        p.getInventory().setItemInOffHand(ItemBuilderUtil.generateItemWithMeta(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Soul Reaper's Totem", Material.TOTEM_OF_UNDYING, 1, null, null, null, 0));

//                        ItemStack blackchest = new ItemStack(Material.LEATHER_CHESTPLATE,1);
//                        LeatherArmorMeta chestmeta = (LeatherArmorMeta) blackchest.getItemMeta();
//                        chestmeta.setColor(Color.BLACK);
//                        blackchest.setItemMeta(chestmeta);
//
//                        ItemStack blackleggings = new ItemStack(Material.LEATHER_LEGGINGS,1);
//                        LeatherArmorMeta legmeta = (LeatherArmorMeta) blackleggings.getItemMeta();
//                        legmeta.setColor(Color.BLACK);
//                        blackleggings.setItemMeta(legmeta);

                        p.getInventory().setHelmet(ItemBuilderUtil.generateItemWithMeta(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Soul Reaper's Mask", Material.WITHER_SKELETON_SKULL, 1, null, null, null, 0));
                        p.getInventory().setChestplate(ItemBuilderUtil.generateItemWithMeta(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Soul Reaper's Chestplate", Material.CHAINMAIL_CHESTPLATE, 1, null, null, null, 0));
                        p.getInventory().setLeggings(ItemBuilderUtil.generateItemWithMeta(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Soul Reaper's Leggings", Material.CHAINMAIL_LEGGINGS, 1, null, null, null, 0));

                        p.getInventory().setBoots(ItemBuilderUtil.generateItemWithMeta(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Soul Reaper's Boots", Material.CHAINMAIL_BOOTS, 1, null, null, Enchantment.SOUL_SPEED, 3));

                        p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(16);
                        p.setHealth(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());

                        p.getInventory().addItem(new ItemStack(Material.DIRT, 64));


                        break;


                    }
                    case BLAZE_ROD: {

                        ClassManager.CLASS_MAP.put(p, "firewalker");


                        ItemStack blaze = ItemBuilderUtil.generateItemWithMeta(ChatColor.GOLD + "" + ChatColor.BOLD + "Fire Walker's Staff", Material.BLAZE_ROD, 1, null, null, Enchantment.FIRE_ASPECT, 1);
                        ItemMeta blazemeta = blaze.getItemMeta();
                        blazemeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(new UUID(4544545, 3453), "attackDamage", 5, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
                        blaze.setItemMeta(blazemeta);
                        p.getInventory().addItem(blaze);

                        p.getInventory().addItem(ItemBuilderUtil.generateItemWithMeta(ChatColor.GOLD + "" + ChatColor.BOLD + "Fire Walker's Lighter", Material.FLINT_AND_STEEL, 1, null, null, null, 0));

                        p.getInventory().addItem(new ItemStack(Material.LAVA_BUCKET,1));


                        p.getInventory().setItem(8, new ItemStack(Material.COOKED_SALMON, 10));

                        p.getInventory().setLeggings(ItemBuilderUtil.generateItemWithMeta(ChatColor.GOLD + "" + ChatColor.BOLD + "Fire Walker's Leggings", Material.GOLDEN_LEGGINGS, 1, null, null, null, 0));
                        p.getInventory().setBoots(ItemBuilderUtil.generateItemWithMeta(ChatColor.GOLD + "" + ChatColor.BOLD + "Fire Walker's Boots", Material.GOLDEN_BOOTS, 1, null, null, null, 0));

                        p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 99999, 0));
                        p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 99999, 0));

                        p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
                        p.setHealth(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());

                        p.getInventory().addItem(new ItemStack(Material.DIRT, 64));



                        new BukkitRunnable() {

                            @Override
                            public void run() {

                                ArrayList<Player> firewalkers = new ArrayList<>();

                                for (Player p : Bukkit.getOnlinePlayers()) {
                                    if (ClassManager.CLASS_MAP.get(p).equals("firewalker")) {
                                        firewalkers.add(p);
                                    }
                                }

                                if (firewalkers.size() == 0) {
                                    this.cancel();
                                } else {
                                    for (Player player : firewalkers) {
                                        Location underPlayer = new Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ());
                                        if (underPlayer.getBlock().getType() == Material.AIR) {
                                            player.setFireTicks(0);

                                            underPlayer.getBlock().setType(Material.FIRE);
                                            player.setFireTicks(0);
                                        }
                                    }
                                }

                            }
                        }.runTaskTimer(classBattle, 0L, 2L);

                        break;


                    }
                    case ENDER_PEARL: {


                        ClassManager.CLASS_MAP.put(p, "endassassin");

                        p.getInventory().addItem(ItemBuilderUtil.generateItemWithMeta(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "End Assassin's Sword", Material.IRON_SWORD, 1, null, null, null, 0));
                        p.getInventory().addItem(ItemBuilderUtil.generateItemWithMeta(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "End Assassin's Pearls", Material.ENDER_PEARL, 3, null, null, null, 0));

                        p.getInventory().addItem(ItemBuilderUtil.generateItemWithMeta(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "End Assassin's Launch Pad", Material.END_STONE_BRICK_SLAB, 1, null, null, null, 0));

                        p.getInventory().setItem(8, new ItemStack(Material.COOKED_COD, 10));

                        p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999, 0));

                        p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
                        p.setHealth(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());

                        p.getInventory().addItem(new ItemStack(Material.DIRT, 64));


                        break;

                    }

                    case CROSSBOW: {


                        ClassManager.CLASS_MAP.put(p, "bountyhunter");

                        p.getInventory().addItem(ItemBuilderUtil.generateItemWithMeta(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "Bounty Hunter's Axe", Material.STONE_AXE, 1, null, null, null, 0));

                        p.getInventory().addItem(ItemBuilderUtil.generateItemWithMeta(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "Bounty Hunter's Crossbow", Material.CROSSBOW, 1, null, null, Enchantment.PIERCING, 2));

                        p.getInventory().setItem(3, ItemBuilderUtil.generateItemWithMeta(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "Bounty Hunter's Trap", Material.SPRUCE_PRESSURE_PLATE, 2, null, null, null, 0));


                        p.getInventory().setItem(7, new ItemStack(Material.ARROW, 32));
                        p.getInventory().setItem(8, new ItemStack(Material.COOKED_RABBIT, 10));


                        ItemStack potion = new ItemStack(Material.POTION, 1);
                        PotionMeta potmeta = (PotionMeta) potion.getItemMeta();
                        potmeta.setBasePotionData(new PotionData(PotionType.SPEED, false, false));
                        potion.setItemMeta(potmeta);
                        p.getInventory().addItem(potion);

                        p.getInventory().setChestplate(ItemBuilderUtil.generateItemWithMeta(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "Bounty Hunter's Chestplate", Material.LEATHER_CHESTPLATE, 1, null, null, null, 0));
                        p.getInventory().setLeggings(ItemBuilderUtil.generateItemWithMeta(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "Bounty Hunter's Leggings", Material.LEATHER_LEGGINGS, 1, null, null, null, 0));

                        p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
                        p.setHealth(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());

                        p.getInventory().addItem(new ItemStack(Material.DIRT, 64));


                        new BukkitRunnable() {

                            @Override
                            public void run() {

                                ArrayList<Player> bountiers = new ArrayList<>();

                                for (Player pl : Bukkit.getOnlinePlayers()) {
                                    if (ClassManager.CLASS_MAP.get(pl).equals("bountyhunter")) {
                                        bountiers.add(pl);
                                    }
                                }

                                if (bountiers.size() == 0) {
                                    for (Player player : Bukkit.getOnlinePlayers()) {
                                        player.removePotionEffect(PotionEffectType.GLOWING);
                                    }
                                    this.cancel();


                                } else {
                                    for (Player player : Bukkit.getOnlinePlayers()) {
                                        if (!ClassManager.CLASS_MAP.get(player).equals("bountyhunter")) {

                                            player.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 99999, 10));

                                        }
                                    }
                                }

                            }
                        }.runTaskTimer(classBattle, 0L, 20L);

                        break;

                    }
                    case JUNGLE_SAPLING: {

                        ClassManager.CLASS_MAP.put(p, "botanist");

                        ItemStack shears = ItemBuilderUtil.generateItemWithMeta(ChatColor.GREEN + "" + ChatColor.BOLD + "Sacred Botanist's Shears", Material.SHEARS, 1, null, null, null, 0);
                        ItemMeta shearsmeta = shears.getItemMeta();
                        shearsmeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(new UUID(4544545, 3453), "attackDamage", 7, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
                        shears.setItemMeta(shearsmeta);
                        p.getInventory().addItem(shears);

                        p.getInventory().addItem(ItemBuilderUtil.generateItemWithMeta(ChatColor.GREEN + "" + ChatColor.BOLD + "Sacred Botanist's Saplings", Material.JUNGLE_SAPLING, 3, null, null, null, 2));
                        p.getInventory().addItem(ItemBuilderUtil.generateItemWithMeta(ChatColor.GREEN + "" + ChatColor.BOLD + "Sacred Botanist's Bow", Material.BOW, 1, null, null, Enchantment.ARROW_KNOCKBACK, 2));

                        p.getInventory().setItem(3, new ItemStack(Material.WATER_BUCKET,1));


                        p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 99999, 0));

                        p.getInventory().setItem(7, new ItemStack(Material.ARROW, 30));

                        p.getInventory().setItem(8, new ItemStack(Material.GOLDEN_CARROT, 20));

                        p.getInventory().setHelmet(ItemBuilderUtil.generateItemWithMeta(ChatColor.GREEN + "" + ChatColor.BOLD + "Sacred Botanist's Boots", Material.LEATHER_HELMET, 1, null, null, null, 0));
                        p.getInventory().setBoots(ItemBuilderUtil.generateItemWithMeta(ChatColor.GREEN + "" + ChatColor.BOLD + "Sacred Botanist's Helmet", Material.LEATHER_BOOTS, 1, null, null, null, 0));

                        p.getInventory().setChestplate(ItemBuilderUtil.generateItemWithMeta(ChatColor.GREEN + "" + ChatColor.BOLD + "Sacred Botanist's Chestplate", Material.LEATHER_CHESTPLATE, 1, null, null, null, 0));
                        p.getInventory().setLeggings(ItemBuilderUtil.generateItemWithMeta(ChatColor.GREEN + "" + ChatColor.BOLD + "Sacred Botanist's Leggings", Material.LEATHER_LEGGINGS, 1, null, null, null, 0));

                        p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
                        p.setHealth(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());

                        p.getInventory().addItem(new ItemStack(Material.DIRT, 64));


                        new BukkitRunnable() {

                            @Override
                            public void run() {

                                ArrayList<Player> bots = new ArrayList<>();

                                for (Player p : Bukkit.getOnlinePlayers()) {
                                    if (ClassManager.CLASS_MAP.get(p).equals("botanist")) {
                                        bots.add(p);
                                    }
                                }

                                if (bots.size() == 0) {
                                    this.cancel();
                                } else {
                                    for (Player player : bots) {
                                        Location underPlayer = new Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY()-1, player.getLocation().getZ());
                                        underPlayer.getBlock().applyBoneMeal(BlockFace.UP);
                                    }
                                }

                            }
                        }.runTaskTimer(classBattle, 0L, 20L);

                        break;

                    }
                    case ENCHANTED_BOOK: {

                        ClassManager.CLASS_MAP.put(p, "scholar");

                        ItemStack sharpbook = ItemBuilderUtil.generateItemWithMeta(ChatColor.BLUE + "" + ChatColor.BOLD + "Forbidden Scholar's Book of Blades", Material.ENCHANTED_BOOK, 1, null, null, Enchantment.KNOCKBACK, 2);
                        ItemMeta sharpmeta = sharpbook.getItemMeta();
                        sharpmeta.addEnchant(Enchantment.DAMAGE_ALL,6,true);
                        sharpbook.setItemMeta(sharpmeta);
                        p.getInventory().addItem(sharpbook);

                        ItemStack rangebook = ItemBuilderUtil.generateItemWithMeta(ChatColor.BLUE + "" + ChatColor.BOLD + "Forbidden Scholar's Bookshelf", Material.BOOKSHELF, 2, null, ItemFlag.HIDE_ENCHANTS, Enchantment.DURABILITY, 1);
                        p.getInventory().addItem(rangebook);

                        p.getInventory().setItem(8, new ItemStack(Material.BREAD, 20));

                        p.getInventory().setBoots(ItemBuilderUtil.generateItemWithMeta(ChatColor.BLUE + "" + ChatColor.BOLD + "Forbidden Scholar's Boots", Material.DIAMOND_BOOTS, 1, null, null, null, 0));

                        p.getInventory().setLeggings(ItemBuilderUtil.generateItemWithMeta(ChatColor.BLUE + "" + ChatColor.BOLD + "Forbidden Scholar's Leggings", Material.DIAMOND_LEGGINGS, 1, null, null, null, 0));

                        p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
                        p.setHealth(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());

                        p.getInventory().addItem(new ItemStack(Material.DIRT, 64));



                        break;

                    }
                    case SKELETON_SKULL: {

                        //finish coding later
                        ClassManager.CLASS_MAP.put(p, "poacher");

                        Random random = new Random();

                        ArrayList<Material> eggs = new ArrayList<>(Arrays.asList(Material.WARDEN_SPAWN_EGG,Material.BLAZE_SPAWN_EGG,Material.ENDERMAN_SPAWN_EGG));

                        p.getInventory().addItem(ItemBuilderUtil.generateItemWithMeta(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Unworthy Poacher's Sword",Material.STONE_SWORD,1,null,null,null,0));

                        p.getInventory().addItem(ItemBuilderUtil.generateItemWithMeta(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Unworthy Poacher's Bow",Material.BOW,1,null,null,null,0));

                        p.getInventory().setItem(7,new ItemStack(Material.SPECTRAL_ARROW,32));

                        p.getInventory().addItem(new ItemStack(eggs.get(random.nextInt(eggs.size())),1));


                        p.getInventory().setItem(8, new ItemStack(Material.COOKED_PORKCHOP, 10));


                        p.getInventory().setHelmet(ItemBuilderUtil.generateItemWithMeta(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Unworthy Poacher's Helmet", Material.TURTLE_HELMET, 1, null, null, null, 0));
                        p.getInventory().setChestplate(ItemBuilderUtil.generateItemWithMeta(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Unworthy Poacher's Chestplate", Material.LEATHER_CHESTPLATE, 1, null, null, null, 0));
                        p.getInventory().setLeggings(ItemBuilderUtil.generateItemWithMeta(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Unworthy Poacher's Leggings", Material.GOLDEN_LEGGINGS, 1, null, null, null, 0));

                        //p.getInventory().setBoots(ItemBuilderUtil.generateItemWithMeta(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Unworthy Poacher's Bow", Material.DIAMOND_BOOTS, 1, null, null, null, 0));
                        //p.getInventory().setLeggings(ItemBuilderUtil.generateItemWithMeta(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Unworthy Poacher's Bow", Material.DIAMOND_LEGGINGS, 1, null, null, null, 0));

                        p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
                        p.setHealth(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());

                        p.getInventory().addItem(new ItemStack(Material.DIRT, 64));




                        break;

                    }
                    case ICE:{
                        ClassManager.CLASS_MAP.put(p,"frost");
                        p.getInventory().addItem(ItemBuilderUtil.generateItemWithMeta("frost man weapon", Material.WOODEN_SWORD ,1,null,null,null,0));
                        p.getInventory().addItem(ItemBuilderUtil.generateItemWithMeta(null, Material.COOKED_SALMON ,10,null,null,null,0));
                        p.getInventory().addItem(ItemBuilderUtil.generateItemWithMeta("Mei wall", Material.BLUE_ICE ,2,null,null,null,0));
                        p.getInventory().setHelmet(new ItemStack(Material.LEATHER_HELMET,1));
                        p.getInventory().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE,1));
                        p.getInventory().setLeggings(new ItemStack(Material.LEATHER_LEGGINGS,1));
                        p.getInventory().setBoots(ItemBuilderUtil.generateItemWithMeta(null,Material.LEATHER_BOOTS,1,null,null,Enchantment.FROST_WALKER,2));




                    }


                }
            }


            event.setCancelled(true);
            p.closeInventory();

        }


    }


}


