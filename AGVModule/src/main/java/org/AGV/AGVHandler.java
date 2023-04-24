package org.AGV;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;
import org.apache.commons.lang3.StringEscapeUtils;




public class AGVHandler implements HttpHandler {


    //private static final String JSON_TEMPLATE = "{\"State\":1,\"Program name\":\"%s\"}";


    public void handle(HttpExchange httpExchange) throws IOException { //This method handles the http exchange, by checking whether it is a GET or a POST request.
        String requestParamValue = null;

        if ("GET".equals(httpExchange.getRequestMethod())) {
            requestParamValue = moveToChargerOperation(httpExchange);

        } else if ("POST".equals(httpExchange)) {


        }
        handleResponse(httpExchange, requestParamValue); //It then uses both the return value of the way we handled our request, and the http exchange, to execute the method that actually handles the response.
    }

    private String moveToChargerOperation(HttpExchange httpExchange) throws IOException {
        System.out.println(httpExchange.getRequestURI());
        if (httpExchange.getRequestURI().toString().contains("MoveToAssemblyOperation")) {
            System.out.println("works");

            AGVController.handlePostRequest("{\"State\":1,\"Program name\":\"MoveToAssemblyOperation\"}"); // optimering her

            return "wow"; // wtf


            //return split;
        } else {
            return "Undefined request URL";
        }

    }

    private void handleResponse(HttpExchange httpExchange, String requestParamValue) throws IOException {
        String encoding = "UTF-8";
        OutputStream outputStream = httpExchange.getResponseBody();
        StringBuilder htmlBuilder = new StringBuilder();

        /*
        This previous code snippet:
         if (requestParamValue != null) {
            htmlBuilder.append(requestParamValue);
        } else {
            htmlBuilder.append("this is when it is null");
        }
        Is redundant, as there is a better shorter method for doing the same thing (see example hereunder).
         */

        htmlBuilder.append(Objects.requireNonNullElse(requestParamValue, "Object/value returned is null"));//This checks to see if requestParamValue is null, and if it is, appends the String instead of the value.

        String htmlResponse = StringEscapeUtils.escapeHtml4(htmlBuilder.toString());//Here we encode the String created as HTML content.

        httpExchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*"); //This line needs documentation, but should really be focused on, as it is a huge security breach.
        httpExchange.getResponseHeaders().set("Content-Type", "text/html; charset=" + encoding);
        httpExchange.sendResponseHeaders(200, htmlResponse.length());
        outputStream.write(htmlResponse.getBytes());
        outputStream.flush();
        outputStream.close();
    }


}
