package com.f23.javaserver;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.apache.commons.lang3.StringEscapeUtils;

import java.io.IOException;
import java.io.OutputStream;
public class FlowHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange he) throws IOException {
        String reVal;

        if (he.getRequestURI().toString().contains("initProdDrone")) {
            System.out.println(FlowHttpClient.AGVMoveToWarehouse());

            System.out.println(FlowHttpClient.WHPickTray(1));
            System.out.println(FlowHttpClient.AGVPickWarehouse());

            System.out.println(FlowHttpClient.WHPickTray(2));
            System.out.println(FlowHttpClient.AGVPickWarehouse());

            System.out.println(FlowHttpClient.WHPickTray(3));
            System.out.println(FlowHttpClient.AGVPickWarehouse());

            System.out.println(FlowHttpClient.AGVMoveToAssemblyStation());

            System.out.println(FlowHttpClient.AGVPutAssemblyStation());
            System.out.println(FlowHttpClient.AGVPutAssemblyStation());
            System.out.println(FlowHttpClient.AGVPutAssemblyStation());

            System.out.println(FlowHttpClient.ASStartProd("19999"));

            System.out.println(FlowHttpClient.AGVPickAssemblyStation());

            System.out.println(FlowHttpClient.AGVMoveToWarehouse());

            System.out.println(FlowHttpClient.AGVPutWarehouse());

            System.out.println(FlowHttpClient.WHPutTray(1, "Complete drone"));

            reVal = FlowHttpClient.WHGetInventory();
        } else if (he.getRequestURI().toString().contains("initProdDrone")) {
            reVal = "Prod complete";
        } else {
            reVal = "Value is null";
        }

        httpResponse(he, reVal);
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
