package net.weesli.rwishingwell.config;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.CustomKey;
import eu.okaeri.configs.annotation.Header;
import lombok.Getter;
import lombok.Setter;
import net.weesli.rwishingwell.model.Reward;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Map;

@Header("########################################")
@Header("#                                       ")
@Header("# Author @Weesli ")
@Header("#")
@Header("########################################")
@Getter@Setter
public class Config extends OkaeriConfig {

    private String prefix = "&7[&6RWishingWell&7] &f";
    @CustomKey("region-name")
    private String region_name = "wishing_well";

    @CustomKey("win-chance")
    private int win_chance = 70;

    @CustomKey("win-title")
    private List<String> win_title = List.of(
            "&3 You got a item in well",
            ""
    );
    @CustomKey("lose-title")
    private List<String> lose_title = List.of(
            "&3You didn't win anything!",
            ""
    );

    @CustomKey("scrolls-item")
    private Item scrolls_item = new Item();

    @CustomKey("rewards")
    private RewardsSettings rewardsSettings = new RewardsSettings();

    @Getter
    public static class Item extends OkaeriConfig{
        private String title = "&eWish Scrolls";
        private String material = "PAPER";
        @CustomKey("custom-model-data")
        private int custom_model_data = 0;
        private List<String> lore = List.of(
                "&aThis is a wishing scroll.",
                "&aIf you wish it from the &c/warp wish &a section.",
                "&aYou will receive a valuable item."
        );
    }
    @Getter
    public static class RewardsSettings extends OkaeriConfig{
        private Map<String, Reward> rewards = Map.of(
                "reward-1", new Reward(new ItemStack(Material.DIAMOND, 2), 50)
        );
    }
}
