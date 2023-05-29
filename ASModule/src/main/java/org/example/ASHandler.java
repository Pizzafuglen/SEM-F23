package org.example;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.apache.commons.lang3.StringEscapeUtils;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.io.IOException;
import java.io.OutputStream;

public class ASHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange he) throws IOException {
        String reVal;
        if (he.getRequestURI().toString().contains("startProd")) {
            try {
                reVal = MQTTHandler.InitiateProd(he.getRequestURI().toString().split("\\?")[1]);
            } catch (MqttException e) {
                throw new RuntimeException(e);
            }
        } else {
            reVal = "Value is null";
        }
        System.out.println(reVal);

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
