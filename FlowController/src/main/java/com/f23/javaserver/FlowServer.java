package com.f23.javaserver;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class FlowServer {
    public static void StartServer(ThreadPoolExecutor tpe) {
        HttpServer hs;

        try {
            hs = HttpServer.create(new InetSocketAddress("localhost",8001),0);
            hs.createContext("/", new FlowHandler());

            hs.setExecutor(tpe);
            hs.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
        ThreadPoolExecutor tpe = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

        FlowServer.StartServer(tpe);
    }
}
