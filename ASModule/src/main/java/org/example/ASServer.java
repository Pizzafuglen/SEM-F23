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
            hs.createContext("/1/", new ASHandler());

            hs.setExecutor(tpe);
            hs.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void StartMQTTClient(ThreadPoolExecutor tpe){
        // Set up the MQTT client
        String publisherId = UUID.randomUUID().toString();
        try {
            IMqttClient publisher = new MqttClient("tcp://iot.eclipse.org:1883",publisherId);
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }

        /*
        try {
            //mqttClient.connect();
            //mqttClient.subscribe("tcp://broker.emqx.io:1883");
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }

         */

        // Wait for messages to arrive
        while (true) {}
    }

    public static void main(String[] args) {
        ThreadPoolExecutor tpe = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

        ASServer.StartServer(tpe);
    }
}

