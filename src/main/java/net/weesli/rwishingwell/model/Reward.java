package net.weesli.rwishingwell.model;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.inventory.ItemStack;

@Getter@Setter
public class Reward {
    private ItemStack itemStack;
    private int chance;

    public Reward(){

    }

    public Reward(ItemStack itemStack, int chance) {
        this.itemStack = itemStack;
        this.chance = chance;
    }

    public boolean isWin(int chance) {
        return this.chance >= chance;
    }

    public String serializeItemStack() {
        return itemStack.serialize().toString();
    }
}
