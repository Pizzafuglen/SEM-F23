package com.f23.javaserver;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.apache.commons.lang3.StringEscapeUtils;

import java.io.IOException;
import java.io.OutputStream;

public class WHHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange he) throws IOException {
        String reVal;

        if (he.getRequestURI().toString().contains("getInventory")) {
            reVal = WSDLClient.getWHInventory();
        } else if (he.getRequestURI().toString().contains("pickSpecificTray")) {
            reVal = WSDLClient.pickSpecificTray(Integer.parseInt(he.getRequestURI().toString().split("\\?")[1]));
        } else if (he.getRequestURI().toString().contains("putSpecificTray")) {
            reVal = WSDLClient.putSpecificTray(Integer.parseInt(he.getRequestURI().toString().split("\\?")[1]), he.getRequestURI().toString().split("\\?")[2]);
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
