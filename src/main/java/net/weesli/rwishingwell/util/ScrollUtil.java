package net.weesli.rwishingwell.util;

import net.weesli.rwishingwell.ColorBuilder;
import net.weesli.rwishingwell.RWishingWell;
import net.weesli.rwishingwell.model.Reward;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ScrollUtil {

    public static void giveScroll(Player player, int amount) {
        ItemStack itemStack = getScrollsItem();
        itemStack.setAmount(amount);
        player.getInventory().addItem(itemStack);
    }


    private static ItemStack getScrollsItem() {
        ItemStack itemStack = new ItemStack(Material.getMaterial(RWishingWell.getInstance().getBaseConfig().getScrolls_item().getMaterial()));
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(ColorBuilder.convertColors(RWishingWell.getInstance().getBaseConfig().getScrolls_item().getTitle()));
        meta.setLore(RWishingWell.getInstance().getBaseConfig().getScrolls_item().getLore().stream().map(ColorBuilder::convertColors).toList());
        meta.setCustomModelData(RWishingWell.getInstance().getBaseConfig().getScrolls_item().getCustom_model_data());
        itemStack.setItemMeta(meta);
        return itemStack;
    }
}
