package com.f23.javaserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.net.http.HttpRequest;

public class FlowHttpClient {
    public static String WHPutTray(int trayId, String itemName) {
        URL url;
        StringBuilder content;
        try {
            url = new URL("http://localhost:8001/1/putSpecificTray?" + trayId + "?" + itemName);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            con.disconnect();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return "Put into tray" + trayId + " item " + itemName + " successfully";
    }
    public static String WHPickTray(int trayId) {
        URL url;
        StringBuilder content;
        try {
            url = new URL("http://localhost:8001/1/pickSpecificTray?" + trayId);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            con.disconnect();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return "Picked tray" + trayId + " successfully";
    }
    public static String WHGetInventory() {
        URL url;
        StringBuilder content;
        try {
            url = new URL("http://localhost:8001/1/getInventory");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            con.disconnect();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return content.toString();
    }
}
