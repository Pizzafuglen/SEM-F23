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
        httpResponse(httpExchange, requestParamValue); //It then uses both the return value of the way we handled our request, and the http exchange, to execute the method that actually handles the response.
    }

    private String operation(HttpExchange httpExchange) throws IOException, JSONException {
        if (httpExchange.getRequestURI().toString().contains("moveToAssemblyStationOperation")) {
            AGVController.handlePostRequest("{\"State\":1,\"Program name\":\"MoveToAssemblyOperation\"}"); // optimering her
            return "Moved to Assembly";
            //return split;
        } else if (httpExchange.getRequestURI().toString().contains("moveToChargerOperation")) {
            AGVController.handlePostRequest("{\"State\":1,\"Program name\":\"MoveToChargerOperation\"}");
            return "Moved to Charging";
        } else if (httpExchange.getRequestURI().toString().contains("moveToWarehouseOperation")) {
            AGVController.handlePostRequest("{\"State\":1,\"Program name\":\"MoveToStorageOperation\"}");

            /*
            int id  = Integer.parseInt(httpExchange.getRequestURI().toString().split("\\?")[1].split("=")[1].split("_")[0]);
            String program = String(httpExchange.getRequestURI().split("\\?")[1].split("=")[1].split("_")[1]);
            int C_state = Integer.parseInt(httpExchange.getRequestURI().toString().split("\\?")[1].split("=")[1].split("_")[2]);
            int battery = Integer.parseInt(httpExchange.getRequestURI().toString().split("\\?")[1].split("=")[1].split("_")[3]);
            System.out.println(battery+C_state);
            DBhandler.setData(id, C_state, battery);
             */

            return "Moved to Warehouse";
        } else if (httpExchange.getRequestURI().toString().contains("putAssemblyOperation")) {
            AGVController.handlePostRequest("{\"State\":1,\"Program name\":\"PutAssemblyOperation\"}");
            return "Put AssemblyStation";
        } else if (httpExchange.getRequestURI().toString().contains("pickAssemblyOperation")) {
            AGVController.handlePostRequest("{\"State\":1,\"Program name\":\"PickAssemblyOperation\"}");
            return "Pick AssemblyStation";
        } else if (httpExchange.getRequestURI().toString().contains("pickWarehouseOperation")) {
            AGVController.handlePostRequest("{\"State\":1,\"Program name\":\"PickWarehouseOperation\"}");
            return "Pick Warehouse";
        } else if (httpExchange.getRequestURI().toString().contains("putWarehouseOperation")) {
            AGVController.handlePostRequest("{\"State\":1,\"Program name\":\"PutWarehouseOperation\"}");
            return "Put Warehouse";
        } else if (httpExchange.getRequestURI().toString().contains("2")) {
            AGVController.handlePostRequest("{\"State\":2}");
            return "Executing";
        }
        return "Undefined request URL";
    }
    private void httpResponse(HttpExchange he, String reVal) throws IOException {
        OutputStream os = he.getResponseBody();

        String hr = StringEscapeUtils.escapeHtml4(reVal);

        he.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        he.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
        he.sendResponseHeaders(200, hr.length());

        os.write(hr.getBytes());
        os.flush();
        os.close();
    }
}
