package com.mcserversoft.mcsscommunicator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import static org.bukkit.Bukkit.getLogger;

public class HTTPClient {

    private URL baseUrl;
    private final String defaultUrl;

    public HTTPClient(String url) {
        defaultUrl = "http://localhost:9696";

        boolean success = setUrl(url);
        if (!success) {
            setUrl(defaultUrl);
        }
    }

    private boolean setUrl(String url) {
        try {
            this.baseUrl = new URL(url);
        } catch (MalformedURLException ex) {
            getLogger().severe(String.format("Failed to parse url form config File. Using default value '(%s)'", defaultUrl));
            return false;
        }
        return true;
    }

    public void post(String path, String json) {

        try {
            String url = String.format("%s/%s", baseUrl, path);

            getLogger().severe(json);
            getLogger().severe(url);

            HttpClient client = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);

            httpPost.setHeader("User-Agent", "MCSSC");

            StringEntity entity = new StringEntity(json);
            httpPost.setEntity(entity);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            HttpResponse response = client.execute(httpPost);
            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Post parameters : " + httpPost.getEntity());
            System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }

            System.out.println(result.toString());

            //TODO add custom messages when mcss is not running etc.
        } catch (Exception ex) {
            getLogger().severe(String.format("Failed to post data to mcss: ", ex.getMessage()));
        }
    }
}
