package fr.bananasmoothii.chunkypause;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

// doing everything at once
public final class ChunkyPausePlugin extends JavaPlugin implements Listener {

    private boolean isRunning;

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        chunkyContinue();
    }

    @EventHandler
    public void on(PlayerJoinEvent event) {
        if (isRunning) {
            chunkyPause();
        }
    }

    @EventHandler
    public void on(PlayerQuitEvent event) {
        Bukkit.getScheduler().runTaskLater(this, () -> {
            if (getServer().getOnlinePlayers().size() == 0) {
                chunkyContinue();
            }
        }, 100);
    }

    public void chunkyContinue() {
        getLogger().info("Continuing Chunky Generation");
        getServer().dispatchCommand(Bukkit.getConsoleSender(), "chunky continue");
        isRunning = true;
    }

    public void chunkyPause() {
        getLogger().info("Pausing Chunky Generation");
        getServer().dispatchCommand(Bukkit.getConsoleSender(), "chunky pause");
        isRunning = false;
    }
}
