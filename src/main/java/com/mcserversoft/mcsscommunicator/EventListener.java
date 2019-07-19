package com.mcserversoft.mcsscommunicator;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class EventListener implements Listener {

    private final EventProcessor eventProcessor;

    public EventListener(Config config) {
        this.eventProcessor = new EventProcessor(config);
    }

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) {
        eventProcessor.playerLogin(event.getPlayer());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        eventProcessor.playerQuit(event.getPlayer());
    }
}
