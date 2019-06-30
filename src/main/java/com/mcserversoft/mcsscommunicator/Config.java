package com.mcserversoft.mcsscommunicator;

import org.bukkit.configuration.file.FileConfiguration;

public class Config {

    private final String url;
    private final String serverGUID;

    public Config(FileConfiguration config) {
        this.url = config.getString("url");
        this.serverGUID = config.getString("serverGUID");
    }

    public String getUrl() {
        return this.url;
    }

    public String getServerGUID() {
        return this.serverGUID;
    }
}
