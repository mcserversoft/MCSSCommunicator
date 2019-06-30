package com.mcserversoft.mcsscommunicator;

import com.google.gson.Gson;
import com.mcserversoft.mcsscommunicator.dto.PlayerDTO;
import org.bukkit.entity.Player;

public class EventProcessor {

    private final Config config;
    private final HTTPClient client;

    public EventProcessor(Config config) {
        this.config = config;
        this.client = new HTTPClient(config.getUrl());
    }

    public void login(Player player) {
        PlayerDTO playerDTO = createPlayerDTO(player);
        client.post("player/join", new Gson().toJson(playerDTO));
    }

    public void leave(Player player) {
        PlayerDTO playerDTO = createPlayerDTO(player);
        client.post("player/leave", new Gson().toJson(playerDTO));
    }

    private PlayerDTO createPlayerDTO(Player player) {
        String username = player.getDisplayName();
        String uuid = player.getUniqueId().toString();
        String serverGuid = config.getServerGUID();

        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setUsername(username);
        playerDTO.setUUID(uuid);
        playerDTO.setServerUUID(serverGuid);

        return playerDTO;
    }
}
