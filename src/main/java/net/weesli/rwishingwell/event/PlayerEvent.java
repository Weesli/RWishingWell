package net.weesli.rwishingwell.event;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.math.BlockVector2;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import net.weesli.rwishingwell.RWishingWell;
import net.weesli.rwishingwell.util.ItemUtil;
import net.weesli.rwishingwell.util.RewardUtil;
import org.bukkit.Location;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerEvent implements Listener {

    RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();

    @EventHandler
    public void onDrop(PlayerDropItemEvent e){
        Player player = e.getPlayer();
        Item item = e.getItemDrop();
        boolean isScrollsItem = ItemUtil.isScrollsItem(item.getItemStack());
        if (!isScrollsItem) return;
        RegionManager regions = container.get(BukkitAdapter.adapt(player.getWorld()));
        ProtectedRegion region = regions.getRegion(RWishingWell.getInstance().getBaseConfig().getRegion_name());
        if (region == null) {
            throw new RuntimeException("Wishing well region is not set, please set with WorldGuard!");
        };
        new BukkitRunnable() {
            private int time = 100;
            @Override
            public void run() {
                if (time == 0){
                    cancel();
                }
                time--;
                Location location = item.getLocation();
                if (location.getWorld() == null) return;
                if (!region.contains(location.getBlockX(), location.getBlockY(), location.getBlockZ())) return;
                RewardUtil.giveReward(player);
                item.remove();
                cancel();
            }
        }.runTaskTimer(RWishingWell.getInstance(), 0,10);
    }
}
