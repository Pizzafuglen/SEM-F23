package org.example;

import org.eclipse.paho.client.mqttv3.*;
import java.util.UUID;
public class MQTTHandler {
    public static void InitiateProd(String id, MqttClient client) throws MqttException {
        client.connect();
        String content0 = "{\nProcessID:" + id + "\n}";
        MqttMessage message0 = new MqttMessage(content0.getBytes());
        message0.setQos(0);
        client.publish("emulator/operation", message0);

        client.subscribe("emulator/status", 0);

    }

    public static void main(String[] args) {
        try {
            MqttClient client = new MqttClient("tcp://localhost:1883", UUID.randomUUID().toString());
            client.setCallback(new MqttCallback() {

                public void connectionLost(Throwable cause) {
                    System.out.println("connectionLost: " + cause.getMessage());
                }

                public void messageArrived(String topic, MqttMessage message) throws MqttException {
                    String msg = new String(message.getPayload());
                    if (msg.contains("\"State\":0")) {

                    }
                    System.out.println(msg);
                }

                public void deliveryComplete(IMqttDeliveryToken token) {
                    System.out.println("deliveryComplete---------" + token.isComplete());
                }
            });

            InitiateProd("19000", client);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
