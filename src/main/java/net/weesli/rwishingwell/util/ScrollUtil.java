package net.weesli.rwishingwell.util;

import de.tr7zw.nbtapi.NBT;
import net.weesli.rwishingwell.ColorBuilder;
import net.weesli.rwishingwell.RWishingWell;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ScrollUtil {

    public static void giveScroll(Player player, int amount) {
        ItemStack itemStack = getScrollsItem();
        itemStack.setAmount(amount);
        NBT.modify(itemStack, nbt ->{
            nbt.setBoolean("isScroll", true);
        });
        player.getInventory().addItem(itemStack);
    }


    private static ItemStack getScrollsItem() {
        return RWishingWell.getInstance().getConfig().getItemStack("scroll-item");
    }
}
