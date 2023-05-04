package com.f23.javaserver;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.apache.commons.lang3.StringEscapeUtils;
import java.io.IOException;
import java.io.OutputStream;

public class WHHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange he) throws IOException {
        System.out.println(he.getRequestURI().toString().split("\\?")[1]);

        String reVal;

        if (he.getRequestURI().toString().contains("getInventory")) {
            reVal = String.valueOf(WSDLClient.getWHInventory());
        } else if (he.getRequestURI().toString().contains("pickSpecificTray")){
            WSDLClient.pickSpecificTray(Integer.parseInt(he.getRequestURI().toString().split("\\?")[1]));
            reVal = he.getRequestURI().toString().split("\\?")[1];
        } else {
            reVal = "Value is null";
        }

        httpResponse(he, reVal);
    }

    private void httpResponse(HttpExchange he, String reVal) throws IOException {
        OutputStream os = he.getResponseBody();
        StringBuilder sb = new StringBuilder(reVal);

        String hr = StringEscapeUtils.escapeHtml4(sb.toString());

        he.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        he.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
        he.sendResponseHeaders(200, hr.length());

        os.write(hr.getBytes());
        os.flush();
        os.close();
    }
}
