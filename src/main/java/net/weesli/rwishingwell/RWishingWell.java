package net.weesli.rwishingwell;

import lombok.Getter;
import net.weesli.rwishingwell.command.WishingWellCommands;
import net.weesli.rwishingwell.event.PlayerEvent;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class RWishingWell extends JavaPlugin {

    @Getter private static RWishingWell instance;


    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        getConfig().options().copyDefaults(true);
        saveConfig();
        getServer().getPluginManager().registerEvents(new PlayerEvent(), this);
        getCommand("wishingwell").setExecutor(new WishingWellCommands());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
