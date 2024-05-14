package yellowknife.yellowknifenamemanager;

import java.util.Iterator;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinHandler implements Listener {
    public PlayerJoinHandler() {
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Iterator var2 = getConfig().getStringList("blocked").iterator();

        while(true) {
            String blocked;
            do {
                do {
                    if (!var2.hasNext()) {
                        return;
                    }

                    blocked = (String)var2.next();
                } while(!e.getPlayer().getName().contains(blocked));
            } while(YellowKnifeNameManager.hasPerm(e.getPlayer(), getConfig().getString("perms.bypass")));

            e.getPlayer().kickPlayer(YellowKnifeNameManager.color(getConfig().getString("reason").replace("{player}", e.getPlayer().getName())));
            Iterator var4 = Bukkit.getOnlinePlayers().iterator();

            while(var4.hasNext()) {
                Player admins = (Player)var4.next();
                if (YellowKnifeNameManager.hasPerm(admins, getConfig().getString("perms.notify"))) {
                    admins.sendMessage(YellowKnifeNameManager.color(getConfig().getString("messages.notify").replace("{player}", e.getPlayer().getName())));
                }
            }
        }
    }

    public static FileConfiguration getConfig() {
        return YellowKnifeNameManager.getInstance().getConfig();
    }
}
