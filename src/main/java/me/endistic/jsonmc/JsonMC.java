package me.endistic.jsonmc;

import com.google.gson.Gson;
import me.endistic.jsonmc.tree.Event;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public final class JsonMC extends JavaPlugin implements Listener {
    public static JsonMC instance;
    @Override
    public void onEnable() {
        // Plugin startup logic
        JsonMC.instance = this;
        if(!this.getDataFolder().exists())
            this.getDataFolder().mkdir();

        for(var file : Objects.requireNonNull(this.getDataFolder().listFiles())) {
            try {
                var contents = Files.readString(Path.of(file.getAbsolutePath()));
                var event = new Gson().fromJson(contents, Event.class);
                Executor.events.add(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        this.getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void join(PlayerJoinEvent e) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(this, () -> {
            Executor.executeEvent(
                    "join",
                    e.getPlayer()
            );
        }, 10);
    }
}
