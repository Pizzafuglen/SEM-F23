package com.f23.javaserver;

import com.baeldung.soap.ws.client.generated.IEmulatorService_Service;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.apache.commons.lang3.StringEscapeUtils;

import javax.json.Json;
import java.io.IOException;
import java.io.OutputStream;

public class WHHandler implements HttpHandler {
    public IEmulatorService_Service ies = new IEmulatorService_Service();
    private StringBuilder sb = new StringBuilder();
    private String reVal;
    @Override
    public void handle(HttpExchange he) throws IOException {

        switch (he.getRequestMethod()) {
            case "GET" -> reVal = handleGet(he);
            case "POST" -> reVal = handlePost(he);
        }

        httpResponse(he, reVal);
    }

    private String handleGet(HttpExchange he) {
        return String.valueOf(ies.getBasicHttpBindingIEmulatorService().getInventory());
    }

    private String handlePost(HttpExchange he) {
        return String.valueOf(ies.getBasicHttpBindingIEmulatorService().getInventory());
    }

    private void httpResponse(HttpExchange he, String reVal) throws IOException {
        OutputStream os = he.getResponseBody();
        sb.append(reVal);

        System.out.println(ies.getBasicHttpBindingIEmulatorService().getInventory().toString());

        String hr = StringEscapeUtils.escapeHtml4(sb.toString());

        he.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        he.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
        he.sendResponseHeaders(200, hr.length());

        os.write(hr.getBytes());
        os.flush();
        os.close();
    }
}