package net.weesli.rwishingwell.model;

import org.bukkit.inventory.ItemStack;

public record Reward(ItemStack itemStack, int chance) {

    public boolean isWin(int chance) {
        return this.chance >= chance;
    }
}
