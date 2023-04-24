package org.AGV;


import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;

public class AGVController {

    AGVHandler AGV = new AGVHandler();


    private static final String SERVER_URL = "http://localhost:8082";

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private static final OkHttpClient client = new OkHttpClient(); //ændre til httpclient

    public static void main(String[] args) throws IOException { // klasse for sig selv
        handleGetRequest();
        handlePostRequest("{\"State\":1,\"Program name\":\"MoveToAssemblyOperation\"}");
}


    public static String handlePostRequest(String json) throws IOException {


            //String json = "{\"State\":1,\"Program name\":\"MoveToAssemblyOperation\"}";

        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(SERVER_URL + "/v1/status")
                .put(body)
                .build();

        Response response = client.newCall(request).execute();
        System.out.println("PUT request response: " + response.body().string());

        // kald en anden metode til sidst som executer
        return json;
    }

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

