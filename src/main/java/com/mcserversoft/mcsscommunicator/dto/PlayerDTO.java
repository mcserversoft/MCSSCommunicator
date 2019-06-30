package com.mcserversoft.mcsscommunicator.dto;

public class PlayerDTO {

    private String Username;
    private String UUID;
    private String ServerUUID;

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public String getServerUUID() {
        return ServerUUID;
    }

    public void setServerUUID(String ServerUUID) {
        this.ServerUUID = ServerUUID;
    }

}
