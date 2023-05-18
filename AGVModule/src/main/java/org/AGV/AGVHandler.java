package org.AGV;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.lang3.StringEscapeUtils;
import org.json.JSONException;


public class AGVHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException { //This method handles the http exchange, by checking whether it is a GET or a POST request.
        String requestParamValue = null;
        try {
            requestParamValue = operation(httpExchange);
        } catch (JSONException e) {
            e.printStackTrace();
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
        } else if (httpExchange.getRequestURI().toString().contains("MoveToChargerOperation")) {
            AGVController.handlePostRequest("{\"State\":1,\"Program name\":\"MoveToChargerOperation\"}");
            return "Charging";
        } else if (httpExchange.getRequestURI().toString().contains("MoveToStorageOperation")) {
            AGVController.handlePostRequest("{\"State\":1,\"Program name\":\"MoveToStorageOperation\"}");
            //int id  = Integer.parseInt(httpExchange.getRequestURI().toString().split("\\?")[1].split("=")[1].split("_")[0]);
            //String program = String(httpExchange.getRequestURI().split("\\?")[1].split("=")[1].split("_")[1]);
            //int C_state = Integer.parseInt(httpExchange.getRequestURI().toString().split("\\?")[1].split("=")[1].split("_")[2]);
            //int battery = Integer.parseInt(httpExchange.getRequestURI().toString().split("\\?")[1].split("=")[1].split("_")[3]);
            //System.out.println(battery+C_state);
            //DBhandler.setData(id, C_state, battery);

            return "moved to storage";
        } else if (httpExchange.getRequestURI().toString().contains("PutAssemblyOperation")) {
            AGVController.handlePostRequest("{\"State\":1,\"Program name\":\"PutAssemblyOperation\"}");
            return "put assem";
        } else if (httpExchange.getRequestURI().toString().contains("PickAssemblyOperation")) {
            AGVController.handlePostRequest("{\"State\":1,\"Program name\":\"PickAssemblyOperation\"}");
            return "pick assem";
        } else if (httpExchange.getRequestURI().toString().contains("PickWarehouseOperation")) {
            AGVController.handlePostRequest("{\"State\":1,\"Program name\":\"PickWarehouseOperation\"}");
            return "pick ware";
        } else if (httpExchange.getRequestURI().toString().contains("PutWarehouseOperation")) {
            AGVController.handlePostRequest("{\"State\":1,\"Program name\":\"PutWarehouseOperation\"}");
            return "put ware";
        } else if (httpExchange.getRequestURI().toString().contains("2")) {
            AGVController.handlePostRequest("{\"State\":2}");
            return "Executing";
        }
        return "Undefined request URL";
    }


    private void handleResponse(HttpExchange httpExchange, String requestParamValue) throws IOException {
        OutputStream outputStream = httpExchange.getResponseBody();

        String htmlResponse = StringEscapeUtils.escapeHtml4(requestParamValue);//Here we encode the String created as HTML content.

        httpExchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*"); //This line needs documentation, but should really be focused on, as it is a huge security breach.
        httpExchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
        httpExchange.sendResponseHeaders(200, htmlResponse.length());

        outputStream.write(htmlResponse.getBytes());
        outputStream.flush();
        outputStream.close();
    }


}
