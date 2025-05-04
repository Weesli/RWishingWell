package net.weesli.rwishingwell.config.serializer;

import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.*;
import lombok.NonNull;
import net.weesli.rwishingwell.model.Reward;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ItemSerializer implements OkaeriSerdesPack {
    @Override
    public void register(@NonNull SerdesRegistry registry) {
        registry.register(new RewardSerializer());
        registry.register(new ItemStackSerializer());
        registry.register(new EnchantmentSerializer());
    }
    static class RewardSerializer implements ObjectSerializer<Reward> {
        @Override
        public boolean supports(@NonNull Class<? super Reward> type) {
            return Reward.class.isAssignableFrom(type);
        }

        @Override
        public void serialize(@NonNull Reward object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
            data.add("itemstack", object.getItemStack());
            data.add("chance", object.getChance());
        }

        @Override
        public Reward deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
            ItemStack itemStack = data.get("itemstack", ItemStack.class);
            int chance = data.get("chance", Integer.class);
            return new Reward(itemStack, chance);
        }
    }

    static class ItemStackSerializer implements ObjectSerializer<ItemStack> {

        @Override
        public boolean supports(@NonNull Class<? super ItemStack> type) {
            return ItemStack.class.isAssignableFrom(type);
        }

        @Override
        public void serialize(@NonNull ItemStack object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
            data.add("material", object.getType().name());
            data.add("amount", object.getAmount());
            if (object.hasItemMeta()) {
                if (Objects.requireNonNull(object.getItemMeta()).hasDisplayName()) {
                    data.add("title", object.getItemMeta().getDisplayName());
                }
                if (object.getItemMeta().hasLore()) {
                    data.add("lore", object.getItemMeta().getLore());
                }
                if (object.getItemMeta().hasCustomModelData()) {
                    data.add("custom-model-data", object.getItemMeta().getCustomModelData());
                }
                if (object.getItemMeta().hasEnchants()) {
                    data.add("enchants", object.getItemMeta().getEnchants());
                }
            }
        }

        @Override
        public ItemStack deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
            Material material = Material.valueOf(data.get("material", String.class));
            int amount = data.get("amount", Integer.class);
            ItemStack itemStack = new ItemStack(material, amount);
            ItemMeta meta = itemStack.getItemMeta();
            if (data.containsKey("title")) {
                String title = data.get("title", String.class);
                meta.setDisplayName(title);
            }
            if (data.containsKey("lore")) {
                List<String> lore = data.get("lore", List.class);
                meta.setLore(lore);
            }
            if (data.containsKey("custom-model-data")) {
                int customModelData = data.get("custom-model-data", Integer.class);
                meta.setCustomModelData(customModelData);
            }
            if (data.containsKey("enchants")) {
                Map<Enchantment, Integer> enchants = data.getAsMap("enchants", Enchantment.class, Integer.class);
                enchants.forEach(itemStack::addUnsafeEnchantment);
            }
            itemStack.setItemMeta(meta);
            return itemStack;
        }
    }

    static class EnchantmentSerializer implements ObjectSerializer<Enchantment> {

        @Override
        public boolean supports(@NonNull Class<? super Enchantment> type) {
            return Enchantment.class.isAssignableFrom(type);
        }

        @Override
        public void serialize(@NonNull Enchantment object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
            data.add("name", object.getKey().getKey());
        }

        @Override
        public Enchantment deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
            String name = data.get("name", String.class);
            return Enchantment.getByKey(NamespacedKey.fromString(name));
        }
    }
}
