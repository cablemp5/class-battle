package me.cablemp5.classbattle.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ItemBuilderUtil {

    public static ItemStack generateItemWithMeta(String name, Material mat, int amount, List<String> lore, ItemFlag flag, Enchantment enchantment, int level) {

        ItemStack item = new ItemStack(mat, amount);
        ItemMeta meta = item.getItemMeta();
        if (name != null) {
            meta.setDisplayName(name);
        }
        if (lore != null) {
            meta.setLore(lore);

        }
        if (flag != null) {

            meta.addItemFlags(flag);
        } else {
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        }

        if (enchantment != null) {
            meta.addEnchant(enchantment, level, true);


        }
        item.setItemMeta(meta);
        return item;

    }

}
