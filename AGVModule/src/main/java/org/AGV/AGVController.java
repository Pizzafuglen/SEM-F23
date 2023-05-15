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
        //System.out.println(response.body().string());

        String responseBody = response.body().string();
        //String cleanedInput = responseBody.replaceAll("[{}]", "");


        JSONObject jsonObject = new JSONObject(responseBody);

        int battery = jsonObject.getInt("battery");
        String programName = jsonObject.getString("program name");
        int state = jsonObject.getInt("state");

        System.out.println("Battery: " + battery);
        System.out.println("Program Name: " + programName);
        System.out.println("State: " + state);

        DBhandler.setData(programName, state, battery);


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

