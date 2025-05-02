package net.weesli.rwishingwell.util;

import net.weesli.rwishingwell.ColorBuilder;
import net.weesli.rwishingwell.RWishingWell;
import org.bukkit.inventory.ItemStack;

public class ItemUtil {
    public static boolean isScrollsItem(ItemStack item) {
        String title = ColorBuilder.convertColors(RWishingWell.getInstance().getBaseConfig().getScrolls_item().getTitle());
        String material = RWishingWell.getInstance().getBaseConfig().getScrolls_item().getMaterial();
        int custom_model_data = RWishingWell.getInstance().getBaseConfig().getScrolls_item().getCustom_model_data();

        return item.hasItemMeta() && item.getItemMeta().hasDisplayName() && item.getItemMeta().getDisplayName().equals(title) &&
                item.getType().name().equals(material) && item.getItemMeta().getCustomModelData() == custom_model_data;
    }
}
