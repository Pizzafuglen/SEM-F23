package org.example;

import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.util.UUID;

public class Test {
    public static void main(String[] args) {
        try {
            MqttAsyncClient client = new MqttAsyncClient("tcp://localhost:1883/emulator/status", UUID.randomUUID().toString());

            Callback cb = new Callback();
            client.setCallback(cb);

            IMqttToken t = client.connect();
            t.waitForCompletion();

            client.subscribe("",0 );

        } catch (MqttException e) {
            throw new RuntimeException(e);
        }

    }
}
