package org.AGV;


import com.google.gson.JsonObject;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import static org.AGV.DBhandler.setData;

public class AGVController {

    AGVHandler AGV = new AGVHandler();


    private static final String SERVER_URL = "http://localhost:8082";

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private static final OkHttpClient client = new OkHttpClient(); //ændre til httpclient

    public static void main(String[] args) throws IOException { // klasse for sig selv
        handleGetRequest();
        //handlePostRequest("{\"State\":1,\"Program name\":\"MoveToStorageOperation\"}");
}


    public static String handlePostRequest(String json) throws IOException, JSONException {

        //String json = "{\"State\":1,\"Program name\":\"MoveToAssemblyOperation\"}";
        System.out.println("landet");
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(SERVER_URL + "/v1/status")
                .put(body)
                .build();

        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());

        String responseBody = response.body().string();
        String cleanedInput = responseBody.replaceAll("[{}]", "");

        String[] keyValuePairs = cleanedInput.split(",");

        for (String pair : keyValuePairs) {

            String[] parts = pair.split(":");
            String key = parts[0].trim().replaceAll("\"", "");
            String value = parts[1].trim().replaceAll("\"", "");

            // Process the desired keys and values
            if (key.equals("battery")) {
                int batteryValue = Integer.parseInt(value);
                System.out.println("Battery: " + batteryValue);
            } else if (key.equals("program name")) {
                System.out.println("Program name: " + value);
            } else if (key.equals("state")) {
                int stateValue = Integer.parseInt(value);
                System.out.println("State: " + stateValue);
            }

        }

        System.out.println("PUT request response: " + response.body().string());

        //setData(json); // Call the setData() method to store the data in the database

        return json;
    }

    // metode til at execute



    //test status method
    public static void handleGetRequest() throws IOException {
        //GET request
        Request request = new Request.Builder()
                .url(SERVER_URL + "/v1/status")
                .build();

        Response response = client.newCall(request).execute();
        System.out.println("GET request response: " + response.body().string());
    }



}

