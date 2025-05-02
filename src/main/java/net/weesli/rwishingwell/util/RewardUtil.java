package net.weesli.rwishingwell.util;

import net.weesli.rwishingwell.RWishingWell;
import net.weesli.rwishingwell.model.Reward;
import org.bukkit.entity.Player;

import java.util.Collection;

public class RewardUtil {

    public static void giveReward(Player player) {
        int random = (int) (Math.random() * 100);
        if (random > RWishingWell.getInstance().getBaseConfig().getWin_chance()) {
            MessageUtil.sendTitle("lose", player);
            return;
        }
        MessageUtil.sendTitle("win", player);
        giveRandomItem(player);
    }

    private static void giveRandomItem(Player player) {
        Collection<Reward> rewards = RWishingWell.getInstance().getBaseConfig().getRewardsSettings().getRewards().values();

        int totalWeight = 0;
        for (Reward reward : rewards) {
            totalWeight += reward.chance();
        }

        int random = (int) (Math.random() * totalWeight);
        int cumulative = 0;
        for (Reward reward : rewards) {
            cumulative += reward.chance();
            if (random < cumulative) {
                player.getInventory().addItem(reward.itemStack());
                break;
            }
        }
    }



}
