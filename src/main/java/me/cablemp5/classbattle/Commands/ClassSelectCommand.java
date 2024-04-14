package me.cablemp5.classbattle.Commands;

import java.util.Collections;
import me.cablemp5.classbattle.ClassBattle;
import me.cablemp5.classbattle.utils.ItemBuilderUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClassSelectCommand implements TabExecutor {

  private final ClassBattle classBattle;

  public ClassSelectCommand(ClassBattle classBattle) {
    this.classBattle = classBattle;
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

    if (sender instanceof Player) {

      Player player = (Player) sender;

      classSelect(player);

      return true;

    }
    return false;
  }

  @Override
  public List<String> onTabComplete(CommandSender sender, Command command, String label,
      String[] args) {
    return null;
  }

  public static void classSelect(Player player) {
    Inventory classOptions = Bukkit.createInventory(player, 9, "Classes");

    for (Player p : Bukkit.getOnlinePlayers()) {
      p.setHealthScaled(false);
    }

    //gladiator
    ItemStack gladiatorIcon = ItemBuilderUtil.generateItemWithMeta(ChatColor.RED + "" + ChatColor.BOLD + "Gladiator",Material.IRON_SWORD,1,
        Collections.singletonList(
            ChatColor.GRAY + "Rage - Gives you Strength and Speed for 3 seconds"),ItemFlag.HIDE_ATTRIBUTES,null,0);
    classOptions.setItem(0, gladiatorIcon);

    //soul reaper
    ItemStack reaperIcon = ItemBuilderUtil.generateItemWithMeta(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Soul Reaper",Material.NETHERITE_HOE,1,Arrays.asList(ChatColor.GRAY + "Summon - Summons 3 wither skeletons ",
        ChatColor.GRAY + "and 3 skeletons, and restores 4 hearts"),ItemFlag.HIDE_ATTRIBUTES,null,0);
    classOptions.setItem(1, reaperIcon);

    //fire walker
    ItemStack fireWalkerIcon = ItemBuilderUtil.generateItemWithMeta(ChatColor.GOLD + "" + ChatColor.BOLD + "Fire Walker",Material.BLAZE_ROD,1,Arrays.asList(ChatColor.GRAY + "Magma - Creates a puddle of magma",
        ChatColor.GRAY + "Flame - Creates a trail of fire"),null,null,0);
    classOptions.setItem(2, fireWalkerIcon);

    //end assassin
    ItemStack endAssassinIcon = ItemBuilderUtil.generateItemWithMeta(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "End Assassin",Material.ENDER_PEARL,1,Arrays.asList(
        ChatColor.GRAY + "Assasinate - Teleport to the nearest enemy and inflict them with",
        ChatColor.GRAY + "blindness and slowness whilst becoming invisible"),null,null,0);
    classOptions.setItem(3, endAssassinIcon);

    //bounty hunter
    ItemStack bountyHunterIcon = ItemBuilderUtil.generateItemWithMeta()
    ItemStack bounticon = new ItemStack(Material.CROSSBOW, 1);
    ItemMeta bountmeta = bounticon.getItemMeta();
    bountmeta.setLore(new ArrayList<>(
        Arrays.asList(ChatColor.GRAY + "Spray - The crossbow becomes rapidfire for 5 seconds")));

    bountmeta.setDisplayName(
        ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "Bounty Hunter \uD83C\uDFF9");
    bounticon.setItemMeta(bountmeta);
    classOptions.setItem(4, bounticon);

    //botanist
    ItemStack boticon = new ItemStack(Material.JUNGLE_SAPLING, 1);
    ItemMeta botmeta = boticon.getItemMeta();
    botmeta.setLore(new ArrayList<>(Arrays.asList(ChatColor.GRAY
        + "Bless - Blesses the ground to grow plants that inflict damage and effects")));

    botmeta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Sacred Botanist ☀");
    boticon.setItemMeta(botmeta);
    classOptions.setItem(5, boticon);

    //scholar
    ItemStack scholaricon = new ItemStack(Material.ENCHANTED_BOOK, 1);
    ItemMeta scholarmeta = scholaricon.getItemMeta();
    scholarmeta.setLore(new ArrayList<>(
        Arrays.asList(ChatColor.GRAY + "Scroll - Use a scroll to have a random effect")));

    scholarmeta.setDisplayName(ChatColor.BLUE + "" + ChatColor.BOLD + "Forbidden Scholar Ξ");
    scholaricon.setItemMeta(scholarmeta);
    classOptions.setItem(6, scholaricon);

    //hunter
    ItemStack huntericon = new ItemStack(Material.SKELETON_SKULL, 1);
    ItemMeta huntermeta = huntericon.getItemMeta();
    huntermeta.setLore(new ArrayList<>(Arrays.asList(ChatColor.GRAY
        + "MIMIC - Use the effect of the mob you last killed (Only from the spawn eggs you get)")));

    huntermeta.setDisplayName(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Unworthy Poacher ⚐");
    huntericon.setItemMeta(huntermeta);
    classOptions.setItem(7, huntericon);

    player.openInventory(classOptions);
  }

}
