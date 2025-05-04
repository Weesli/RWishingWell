package net.weesli.rwishingwell.util;

import net.weesli.rwishingwell.RWishingWell;
import net.weesli.rwishingwell.model.Reward;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.Set;

public class RewardUtil {

    public static void giveReward(Player player) {
        int random = (int) (Math.random() * 100);
        if (random > RWishingWell.getInstance().getConfig().getInt("win-chance")) {
            MessageUtil.sendTitle("lose", player);
            return;
        }
        MessageUtil.sendTitle("win", player);
        giveRandomItem(player);
    }

    private static void giveRandomItem(Player player) {
        Set<String> keys = RWishingWell.getInstance().getConfig().getConfigurationSection("rewards").getKeys(false);

        Collection<Reward> rewards = new java.util.ArrayList<>();
        for (String key : keys) {
            ItemStack itemStack = RWishingWell.getInstance().getConfig().getItemStack("rewards." + key + ".itemstack");
            int chance = RWishingWell.getInstance().getConfig().getInt("rewards." + key + ".chance");
            Reward reward = new Reward(itemStack, chance);
            rewards.add(reward);
        }

        int totalWeight = 0;
        for (Reward reward : rewards) {
            totalWeight += reward.getChance();
        }

        int random = (int) (Math.random() * totalWeight);
        int cumulative = 0;
        for (Reward reward : rewards) {
            cumulative += reward.getChance();
            if (random < cumulative) {
                player.getInventory().addItem(reward.getItemStack());
                break;
            }
        }
    }

    public static void addReward(Player sender, int chance) {
        ItemStack itemStack = sender.getInventory().getItemInMainHand();
        if (itemStack.getType().isAir()){
            return;
        }
        Reward reward = new Reward(itemStack, chance);
        int next = RWishingWell.getInstance().getConfig().getConfigurationSection("rewards").getKeys(false).size() + 1;
        ConfigurationSection section = RWishingWell.getInstance().getConfig().createSection("rewards.reward-" + next);
        section.set("itemstack", reward.getItemStack());
        section.set("chance", reward.getChance());
        RWishingWell.getInstance().saveConfig();
    }

}
