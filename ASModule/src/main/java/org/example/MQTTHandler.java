package org.example;

import org.eclipse.paho.client.mqttv3.*;

import java.util.UUID;

public class MQTTHandler {
    public static String InitiateProd(String id) throws MqttException {
        MqttClient client = new MqttClient("tcp://localhost:1883", UUID.randomUUID().toString());
        client.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable throwable) {
                System.out.println("Connection lost");
            }

            @Override
            public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
                System.out.println(new String(mqttMessage.getPayload()));
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
                System.out.println("Message is delivered");
            }
        });

        client.connect();
        String content = "{\nProcessID:" + id + "\n}";
        MqttMessage message = new MqttMessage(content.getBytes());
        message.setQos(0);
        client.publish("emulator/operation", message);
        client.disconnect();
        client.close();

        return "Message returned";
    }
}
