package org.AGV;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

class JsonExtractor {
    public static void main(String[] args) {
        // Create an instance of HttpClient
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // Create an instance of HttpGet request with the URL
        HttpGet httpGet = new HttpGet("http://localhost:8082/v1/status/");

        try {
            // Execute the request and get the response
            CloseableHttpResponse response = httpClient.execute(httpGet);

            // Extract the JSON content from the response body
            String json = EntityUtils.toString(response.getEntity());

            // Print the JSON content to the console
            System.out.println(json);

            // Close the response
            response.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
