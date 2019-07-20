package com.mcserversoft.mcsscommunicator;

import java.net.MalformedURLException;
import java.net.URL;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static org.bukkit.Bukkit.getLogger;

public class HTTPClient {

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    private final boolean debug;
    private URL baseUrl;

    public HTTPClient(Config config) {
        this.debug = config.getIsDebugEnabled();

        boolean success = setUrl(config.getUrl());
        if (!success) {
            setUrl("http://localhost:25560/api");
        }
    }

    private boolean setUrl(String url) {
        try {
            this.baseUrl = new URL(url);
        } catch (MalformedURLException ex) {
            getLogger().warning(String.format("Failed to parse url from config File. Will use the default value."));
            return false;
        }
        return true;
    }

    public void post(String path, String json) {

        try {
            String url = String.format("%s/%s", baseUrl, path);

            if (debug) {
                getLogger().info(String.format("[DEBUG] %s", json));
                getLogger().info(String.format("[DEBUG] %s", url));
            }

            OkHttpClient client = new OkHttpClient();

            RequestBody body = RequestBody.create(JSON, json);
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (debug) {
                    getLogger().info(String.format("[DEBUG] %s", response.body().string()));
                    getLogger().info(String.format("[DEBUG] %s", response.code()));
                }
            }

        } catch (Exception ex) {
            getLogger().severe(String.format("Failed to post data to mcss: ", ex.getMessage()));
        }
    }
}
