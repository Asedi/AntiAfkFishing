package pl.chilleraiutopia.AntiAfkFishing;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;


public class FishEventListener implements Listener {
    /** Checks if player caught a fish
     and inserts appropriate informations into fishermans.db
     **/
    DatabaseHandler dbhandler= new DatabaseHandler();
    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    public void onPlayerFish(PlayerFishEvent event) {
        if(event.getCaught()!=null){
            dbhandler.insert(event.getPlayer().getName(),(int)event.getPlayer().getLocation().getX(),(int)event.getPlayer().getLocation().getZ());
            event.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.DARK_GREEN+""+ChatColor.BOLD+"[AntiAfkFishing]"+ChatColor.RESET+ChatColor.GREEN+"Odejdz stad 7 kratek aby moc nadal lowic!"));
        }
    }
}
