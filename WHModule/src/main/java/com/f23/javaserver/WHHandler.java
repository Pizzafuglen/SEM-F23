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
        System.out.println(he.getRequestMethod());
        switch (he.getRequestMethod()) {
            case "GET" -> reVal = handleGet(he);
            case "POST" -> reVal = handlePost(he);
        }

        httpResponse(he, reVal);
    }

    private String handleGet(HttpExchange he) {
        String getReturnValue = null;
        switch (he.getRequestURI().toString()) {
            case "http://localhost:8001/1/getInventory":
                getReturnValue = String.valueOf(WSDLClient.getWHInventory());
                break;
            default:
                getReturnValue = "Value is null";
        }
        System.out.println("Made it to get");
        return getReturnValue;
    }

    private String handlePost(HttpExchange he) {
        String postReturnValue = null;
        switch (he.getRequestURI().toString()) { //Potentially the switch case is what is messing it up
            case "http://localhost:8001/1/InsertItemInventory":
                System.out.println(he.getRequestURI());
                System.out.println("test");
                postReturnValue = String.valueOf(ies.getBasicHttpBindingIEmulatorService().getInventory());
                break;

            case "localhost:8001/1/PickItemInventory":
                System.out.println(he.getRequestBody().toString());
                break;

            default:
                System.out.println("i got to default");
                postReturnValue = "Value is null";
        }
        System.out.println("made it to post");
        return postReturnValue;
    }

    private void httpResponse(HttpExchange he, String reVal) throws IOException {
        OutputStream os = he.getResponseBody();
        sb.append(reVal);

        String hr = StringEscapeUtils.escapeHtml4(sb.toString());

        he.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        he.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
        he.sendResponseHeaders(200, hr.length());

        os.write(hr.getBytes());
        os.flush();
        os.close();
    }
}
