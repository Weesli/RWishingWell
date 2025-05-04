package net.weesli.rwishingwell.model;

import eu.okaeri.configs.annotation.CustomKey;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.inventory.ItemStack;

@Getter@Setter
public class Reward {
    @CustomKey("itemstack")
    private ItemStack itemStack;
    @CustomKey("chance")
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
