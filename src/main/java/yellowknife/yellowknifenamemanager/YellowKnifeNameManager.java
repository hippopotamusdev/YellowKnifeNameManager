package yellowknife.yellowknifenamemanager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class YellowKnifeNameManager extends JavaPlugin {
    static YellowKnifeNameManager instance;

    public YellowKnifeNameManager() {
    }

    public static YellowKnifeNameManager getInstance() {
        return instance;
    }

    public void onEnable() {
        instance = this;
        this.saveDefaultConfig();
        Bukkit.getPluginManager().registerEvents(new PlayerJoinHandler(), this);
    }

    public static boolean hasPerm(CommandSender sender, String path) {
        return sender.hasPermission(path);
    }

    public static String color(String path) {
        return ChatColor.translateAlternateColorCodes('&', path);
    }
}
