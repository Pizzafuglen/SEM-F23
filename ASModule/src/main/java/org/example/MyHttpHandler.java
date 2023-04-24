package org.example;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MyHttpHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        
        if ("GET".equals(exchange.getRequestMethod())){
            OutputStream outputStream = exchange.getResponseBody();
            String responseToBeSentBack = "Hej Mikkel";
            exchange.sendResponseHeaders(200, responseToBeSentBack.length());
        }
            
        }
    }
