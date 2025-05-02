package net.weesli.rwishingwell.config.serializer;

import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.*;
import lombok.NonNull;
import net.weesli.rwishingwell.model.Reward;
import org.bukkit.inventory.ItemStack;

public class ItemSerializer implements OkaeriSerdesPack {
    @Override
    public void register(@NonNull SerdesRegistry registry) {
        registry.register(new RewardSerializer());
    }
    static class RewardSerializer implements ObjectSerializer<Reward> {
        @Override
        public boolean supports(@NonNull Class<? super Reward> type) {
            return type.equals(Reward.class);
        }

        @Override
        public void serialize(@NonNull Reward object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
            data.add("itemStack", object.itemStack().serialize());
            data.add("chance", object.chance());
        }

        @Override
        public Reward deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
            ItemStack itemStack = ItemStack.deserialize(
                    data.getAsMap("itemStack", String.class, Object.class)
            );
            int chance = data.get("chance", Integer.class);
            return new Reward(itemStack, chance);
        }
    }
}
