package com.mcserversoft.mcsscommunicator;

import java.net.MalformedURLException;
import java.net.URL;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

import static org.bukkit.Bukkit.getLogger;

public class HTTPClient {

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    private URL baseUrl;

    public HTTPClient(String url) {
        boolean success = setUrl(url);

        if (!success) {
            setUrl("http://localhost:9696/api");
        }
    }

    private boolean setUrl(String url) {
        try {
            this.baseUrl = new URL(url);
        } catch (MalformedURLException ex) {
            getLogger().severe(String.format("Failed to parse url from config File. Will use the default value."));
            return false;
        }
        return true;
    }

    public void post(String path, String json) {

        try {
            String url = String.format("%s/%s", baseUrl, path);

            /*
             logger.info(json);
             logger.info(url);
             */
            OkHttpClient client = new OkHttpClient();

            RequestBody body = RequestBody.create(JSON, json);
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
            /*
             try (Response response = client.newCall(request).execute()) {
             getLogger().info(response.body().string());
             }
             */

            //TODO add custom messages when mcss is not running etc.
            //TODO add debug option
        } catch (Exception ex) {
            getLogger().severe(String.format("Failed to post data to mcss: ", ex.getMessage()));
        }
    }
}
