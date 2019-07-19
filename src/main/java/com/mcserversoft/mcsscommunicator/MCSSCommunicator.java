package com.mcserversoft.mcsscommunicator;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.HandlerList;

public class MCSSCommunicator extends JavaPlugin {

    private EventListener eventListener;

    @Override
    public void onEnable() {
        // Loading config
        this.saveDefaultConfig();
        this.getConfig().options().copyDefaults(true);

        Config config = new Config(this.getConfig());

        // Register EventListener
        this.eventListener = new EventListener(config);
        this.getServer().getPluginManager().registerEvents(eventListener, this);

        // Loading complete
        getLogger().info("Powering your Minecraft Server since Beta 1.5");
        getLogger().info("> Core-element of MC Server Soft. ");
        getLogger().info("> Provides real-time diagnostics and server telemetry.");
        getLogger().info(String.format("For more info visit: %s", getDescription().getWebsite()));
        getLogger().info(String.format("Server version: %s", Bukkit.getVersion()));
        getLogger().info(String.format("MCSSCommunicator version: %s", getDescription().getVersion()));
    }

    @Override
    public void onDisable() {
        // Unregister EventListener
        HandlerList.unregisterAll(eventListener);

        // Cancel any running tasks
        getServer().getScheduler().cancelTasks(this);
    }
}
