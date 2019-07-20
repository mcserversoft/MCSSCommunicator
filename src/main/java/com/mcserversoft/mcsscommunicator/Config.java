package com.mcserversoft.mcsscommunicator;

import org.bukkit.configuration.file.FileConfiguration;

public class Config {

    private final String url;
    private final String serverGUID;
    private final boolean debug;

    public Config(FileConfiguration config) {
        this.url = config.getString("url");
        this.serverGUID = config.getString("serverGUID");
        this.debug = config.getBoolean("debug");
    }

    public String getUrl() {
        return this.url;
    }

    public String getServerGUID() {
        return this.serverGUID;
    }
    
    public boolean getIsDebugEnabled() {
        return this.debug;
    }
}
