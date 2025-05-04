package net.weesli.rwishingwell;

import eu.okaeri.configs.ConfigManager;
import eu.okaeri.configs.yaml.bukkit.serdes.SerdesBukkit;
import eu.okaeri.configs.yaml.snakeyaml.YamlSnakeYamlConfigurer;
import lombok.Getter;
import net.weesli.rwishingwell.command.WishingWellCommands;
import net.weesli.rwishingwell.config.Config;
import net.weesli.rwishingwell.config.serializer.ItemSerializer;
import net.weesli.rwishingwell.event.PlayerEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
@Getter
public final class RWishingWell extends JavaPlugin {

    private Config baseConfig;
    @Getter private static RWishingWell instance;


    @Override
    public void onEnable() {
        instance = this;
        loadConfig();
        getServer().getPluginManager().registerEvents(new PlayerEvent(), this);
        getCommand("wishingwell").setExecutor(new WishingWellCommands());
    }

    private void loadConfig() {
        baseConfig = ConfigManager.create(Config.class, (it) ->{
            it.withConfigurer(new YamlSnakeYamlConfigurer(), new ItemSerializer());
            it.withBindFile(new File(this.getDataFolder(), "config.yml"));
            it.withRemoveOrphans(true);
            it.saveDefaults();
            it.load(true);
        });
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
