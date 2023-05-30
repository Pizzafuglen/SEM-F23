package org.AGV;


import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;


public class AGVServer {
    public static void main(String[] args) {

        try {
            HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8002), 0);
            ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

            server.createContext("/2/", new AGVHandler());//Here we create the context for the URLs that are used in the HTTP data transfer
            server.setExecutor(threadPoolExecutor);

            server.start(); //This is the heart of the system, where we assign the server-object a thread-pool, to keep it running

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

