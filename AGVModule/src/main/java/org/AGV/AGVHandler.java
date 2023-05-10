package org.AGV;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.PreparedStatement;
import java.util.Objects;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;


public class AGVHandler implements HttpHandler {


    //private static final String JSON_TEMPLATE = "{\"State\":1,\"Program name\":\"%s\"}";


    public void handle(HttpExchange httpExchange) throws IOException { //This method handles the http exchange, by checking whether it is a GET or a POST request.
        String requestParamValue = null;

        if ("GET".equals(httpExchange.getRequestMethod())) {
            try {
                requestParamValue = operation(httpExchange);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        } else if ("POST".equals(httpExchange)) {


        }
        handleResponse(httpExchange, requestParamValue); //It then uses both the return value of the way we handled our request, and the http exchange, to execute the method that actually handles the response.
    }

    private String operation(HttpExchange httpExchange) throws IOException, JSONException {

        System.out.println(httpExchange.getRequestURI());
        if (httpExchange.getRequestURI().toString().contains("MoveToAssemblyOperation")) {
            System.out.println("works");

            AGVController.handlePostRequest("{\"State\":1,\"Program name\":\"MoveToAssemblyOperation\"}"); // optimering her
            return "moved to assem"; // wtf
            //return split;
        } else if (httpExchange.getRequestURI().toString().contains("MoveToChargerOperation")){
            AGVController.handlePostRequest("{\"State\":1,\"Program name\":\"MoveToChargerOperation\"}");
            return "Charging";
        } else if (httpExchange.getRequestURI().toString().contains("MoveToStorageOperation")){
            AGVController.handlePostRequest("{\"State\":1,\"Program name\":\"MoveToStorageOperation\"}");
            //int id  = Integer.parseInt(httpExchange.getRequestURI().toString().split("\\?")[1].split("=")[1].split("_")[0]);
            //String program = String(httpExchange.getRequestURI().split("\\?")[1].split("=")[1].split("_")[1]);
            //int C_state = Integer.parseInt(httpExchange.getRequestURI().toString().split("\\?")[1].split("=")[1].split("_")[2]);
            //int battery = Integer.parseInt(httpExchange.getRequestURI().toString().split("\\?")[1].split("=")[1].split("_")[3]);
            //System.out.println(battery+C_state);

            //DBhandler.setData(id, C_state, battery);

            return "moved to storage";
        } else if (httpExchange.getRequestURI().toString().contains("PutAssemblyOperation")){
            AGVController.handlePostRequest("{\"State\":1,\"Program name\":\"PutAssemblyOperation\"}");
            return "put assem";
        } else if (httpExchange.getRequestURI().toString().contains("PickAssemblyOperation")){
            AGVController.handlePostRequest("{\"State\":1,\"Program name\":\"PickAssemblyOperation\"}");
            return "pick assem";
        } else if (httpExchange.getRequestURI().toString().contains("PickWarehouseOperation")){
            AGVController.handlePostRequest("{\"State\":1,\"Program name\":\"PickWarehouseOperation\"}");
            return "pick ware";
        } else if (httpExchange.getRequestURI().toString().contains("PutWarehouseOperation")){
            AGVController.handlePostRequest("{\"State\":1,\"Program name\":\"PutWarehouseOperation\"}");
            return "put ware";
        } else if (httpExchange.getRequestURI().toString().contains("2")){
            AGVController.handlePostRequest("{\"State\":2}");
            return "Executing";
        }
        return "Undefined request URL";
    }



    private void handleResponse(HttpExchange httpExchange, String requestParamValue) throws IOException {
        String encoding = "UTF-8";
        OutputStream outputStream = httpExchange.getResponseBody();
        StringBuilder htmlBuilder = new StringBuilder();


        htmlBuilder.append(Objects.requireNonNullElse(requestParamValue, "Object/value returned is null"));//This checks to see if requestParamValue is null, and if it is, appends the String instead of the value.

        String htmlResponse = StringEscapeUtils.escapeHtml4(htmlBuilder.toString());//Here we encode the String created as HTML content.

        httpExchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*"); //This line needs documentation, but should really be focused on, as it is a huge security breach.
        httpExchange.getResponseHeaders().set("Content-Type", "text/html; charset=" + encoding);
        httpExchange.sendResponseHeaders(200, htmlResponse.length());
        outputStream.write(htmlResponse.getBytes());
        outputStream.flush();
        outputStream.close();
    }


}
