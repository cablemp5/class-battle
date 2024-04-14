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
    ItemStack fireWalkerIcon = ItemBuilderUtil.generateItemWithMeta(ChatColor.GOLD + "" + ChatColor.BOLD + "Fire ",Material.BLAZE_ROD,1,Arrays.asList(ChatColor.GRAY + "Magma - Creates a puddle of magma",
        ChatColor.GRAY + "Flame - Creates a trail of fire"),null,null,0);
    classOptions.setItem(2, fireWalkerIcon);

    //assassin
    ItemStack assassinIcon = ItemBuilderUtil.generateItemWithMeta(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Assassin",Material.ENDER_PEARL,1,Arrays.asList(
        ChatColor.GRAY + "Assasinate - Teleport to the nearest enemy and inflict them with",
        ChatColor.GRAY + "blindness and slowness whilst becoming invisible"),null,null,0);
    classOptions.setItem(3, assassinIcon);

    //bounty hunter
    ItemStack bountyHunterIcon = ItemBuilderUtil.generateItemWithMeta(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "Bounty Hunter",Material.CROSSBOW,1,Arrays.asList(ChatColor.GRAY + "Spray - The crossbow becomes rapidfire for 5 seconds"),ItemFlag.HIDE_ATTRIBUTES,null,0);
    classOptions.setItem(4, bountyHunterIcon);

    //botanist
    ItemStack botanistIcon = ItemBuilderUtil.generateItemWithMeta(ChatColor.GREEN + "" + ChatColor.BOLD + "Botanist",Material.JUNGLE_SAPLING, 1, Arrays.asList(ChatColor.GRAY
        + "Bless - Blesses the ground to grow plants that inflict damage and effects"),null,null,0);
    classOptions.setItem(5, botanistIcon);

    //scholar
    ItemStack scholarIcon = ItemBuilderUtil.generateItemWithMeta(ChatColor.BLUE + "" + ChatColor.BOLD + "Scholar",Material.ENCHANTED_BOOK,1,Arrays.asList(ChatColor.GRAY + "Scroll - Use a scroll to have a random effect"),null,null,0);
    classOptions.setItem(6, scholarIcon);

    //hunter
    ItemStack poacherIcon = ItemBuilderUtil.generateItemWithMeta(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Poacher",Material.SKELETON_SKULL,1,Arrays.asList(ChatColor.GRAY
        + "MIMIC - Use the effect of the mob you last killed (Only from the spawn eggs you get)"),null,null,0);
    classOptions.setItem(7, poacherIcon);

    player.openInventory(classOptions);
  }
//
}
