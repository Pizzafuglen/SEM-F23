package org.example;

import com.sun.net.httpserver.HttpServer;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;


public class ASServer {
    public static void StartServer(ThreadPoolExecutor tpe) {
        HttpServer hs;

        try {
            hs = HttpServer.create(new InetSocketAddress("localhost", 8001), 0);
            hs.createContext("/3/", new ASHandler());

            hs.setExecutor(tpe);
            hs.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        ThreadPoolExecutor tpe = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

        ASServer.StartServer(tpe);
    }
}

