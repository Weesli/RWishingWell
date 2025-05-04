package net.weesli.rwishingwell.util;

import net.weesli.rwishingwell.ColorBuilder;
import net.weesli.rwishingwell.RWishingWell;
import org.bukkit.entity.Player;

public class MessageUtil {

    public static void sendTitle(String type, Player player){
        switch (type){
            case "win":
                player.sendTitle(ColorBuilder.convertColors(RWishingWell.getInstance().getConfig().getStringList("win-title").get(0)),
                        ColorBuilder.convertColors(RWishingWell.getInstance().getConfig().getStringList("win-title").get(1)), 20, 40, 20);
                break;
            case "lose":
                player.sendTitle(ColorBuilder.convertColors(RWishingWell.getInstance().getConfig().getStringList("lose-title").get(0)),
                        ColorBuilder.convertColors(RWishingWell.getInstance().getConfig().getStringList("lose-title").get(1)), 20, 40, 20);
                break;
        }
    }
}
