package net.weesli.rwishingwell.util;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.inventory.ItemStack;

public class ItemUtil {
    public static boolean isScrollsItem(ItemStack item) {
        NBTItem nbtItem = new NBTItem(item);
        if (nbtItem.hasTag("isScroll")){
            return nbtItem.getBoolean("isScroll");
        }
        return false;
    }
}
