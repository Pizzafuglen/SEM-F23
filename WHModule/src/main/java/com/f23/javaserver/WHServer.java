package com.f23.javaserver;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class WHServer {
    public static void StartServer(ThreadPoolExecutor tpe) {
        HttpServer hhs;

        try {
            hhs = HttpServer.create(new InetSocketAddress("localhost",8001),0);
            hhs.createContext("/1/", new WHHandler());

            hhs.setExecutor(tpe);
            hhs.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
        ThreadPoolExecutor tpe = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

        WHServer.StartServer(tpe);
    }
}
