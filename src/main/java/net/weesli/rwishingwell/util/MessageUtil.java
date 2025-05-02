package net.weesli.rwishingwell.util;

import net.weesli.rwishingwell.ColorBuilder;
import net.weesli.rwishingwell.RWishingWell;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class MessageUtil {

    public static void sendTitle(String type, Player player){
        switch (type){
            case "win":
                player.sendTitle(ColorBuilder.convertColors(RWishingWell.getInstance().getBaseConfig().getWin_title().get(0)),
                        ColorBuilder.convertColors(RWishingWell.getInstance().getBaseConfig().getWin_title().get(1)), 20, 40, 20);
                break;
            case "lose":
                player.sendTitle(ColorBuilder.convertColors(RWishingWell.getInstance().getBaseConfig().getLose_title().get(0)),
                        ColorBuilder.convertColors(RWishingWell.getInstance().getBaseConfig().getLose_title().get(1)), 20, 40, 20);
                break;
        }
    }
}
