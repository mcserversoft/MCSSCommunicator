package com.mcserversoft.mcsscommunicator.dto;

/* Deliberately violating naming conventions because this DTO needs to be read by a C# application */
public class PlayerDTO {

    private String Username;
    private String UUID;
    private String ServerUUID;

    public String getUsername() {
        return Username;
    }

    public String getUUID() {
        return UUID;
    }

    public String getServerUUID() {
        return ServerUUID;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public void setServerUUID(String ServerUUID) {
        this.ServerUUID = ServerUUID;
    }
}
