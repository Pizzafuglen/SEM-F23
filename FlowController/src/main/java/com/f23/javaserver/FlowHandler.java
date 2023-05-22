package com.f23.javaserver;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class FlowHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange he) throws IOException {
        String reVal;

        if (he.getRequestURI().toString().contains("initProdDrone")) {

        }

    }
}
