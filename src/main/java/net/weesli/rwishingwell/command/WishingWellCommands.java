package net.weesli.rwishingwell.command;

import net.weesli.rwishingwell.RWishingWell;
import net.weesli.rwishingwell.util.RewardUtil;
import net.weesli.rwishingwell.util.ScrollUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WishingWellCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.isOp()){
            if (args.length == 0){
                return false;
            }
            if (args[0].equals("give")){
                if (args.length == 2){
                    String playerName = args[1];
                    Player player = sender.getServer().getPlayer(playerName);
                    if (player == null){
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cPlayer not found"));
                        return false;
                    }
                    ScrollUtil.giveScroll(player, 1);
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aScrolls given to " + player.getName()));
                    return true;
                }else if (args.length == 3){
                    String playerName = args[1];
                    int amount = Integer.parseInt(args[2]);
                    Player player = sender.getServer().getPlayer(playerName);
                    if (player == null){
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cPlayer not found"));
                        return false;
                    }
                    ScrollUtil.giveScroll(player, amount);
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aScrolls given to " + player.getName()));
                    return true;
                }
            }else if (args[0].equals("add")){
                if (args.length == 2){
                    if (sender instanceof Player player){
                        int chance = Integer.parseInt(args[1]);
                        RewardUtil.addReward(player, chance);
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aAdded Item to config with chance " + chance));
                        return true;
                    }
                }
            } else if (args[0].equals("reload")){ {
                RWishingWell.getInstance().getBaseConfig().load();
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aReloaded config"));
            }
            }
        }
        return false;

    }
}
