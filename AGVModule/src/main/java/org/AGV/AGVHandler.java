package org.AGV;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;


public class AGVHandler implements HttpHandler {

    int idle = 1;
    int executing = 2;
    int charging = 3;

    public void handle(HttpExchange httpExchange) throws IOException { //This method handles the http exchange, by checking whether it is a GET or a POST request.
        String requestParamValue = null;

        if ("GET".equals(httpExchange.getRequestMethod())) {
            requestParamValue = moveToChargerOperation(httpExchange);

        } else if ("POST".equals(httpExchange)) {


        }
        handleResponse(httpExchange, requestParamValue); //It then uses both the return value of the way we handled our request, and the http exchange, to execute the method that actually handles the response.
    }

    private String moveToChargerOperation(HttpExchange httpexchange) throws IOException {

        return null;
    }

    private void handleResponse(HttpExchange httpExchange, String requestParamValue) throws IOException {
        String encoding = "UTF-8";
        OutputStream outputStream = httpExchange.getResponseBody();
        StringBuilder htmlBuilder = new StringBuilder();

        //htmlBuilder.append(Objects.requireNonNullElse(requestParamValue, "Object/value returned is null"));//This checks to see if requestParamValue is null, and if it is, appends the String instead of the value.


    }



}
