package org.example;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class Mqtt_Client2 {
    public static void main(String[] args) {
        String subTopic = "emulator/operation";
        String pubTopic = "emulator/operation";
        String content = "A????3";
        int qos = 2;
        String broker = "mqtt://localhost:1883/";
        String clientId = "client-1";
        MemoryPersistence persistence = new MemoryPersistence();

        try {
            MqttClient client = new MqttClient(broker, clientId);

            // MQTT connection option
            MqttConnectOptions connOpts = new MqttConnectOptions();
            // retain session
            connOpts.setCleanSession(true);
            // set callback
            client.setCallback(new OnMessageCallback()); // ved ikke hvad den gør
            // establish a connection
            System.out.println("Connecting to broker: " + broker);
            client.connect(connOpts);

            System.out.println("Connected");

            // Subscribe
            client.subscribe(subTopic);


            // Required parameters for message publishing
            System.out.println("Publishing message: " + content);
            System.out.println("-----------------------");
            MqttMessage message = new MqttMessage(content.getBytes());
            message.setQos(qos);
            client.publish(pubTopic, message);
            System.out.println("Message published");

            client.disconnect();
            System.out.println("Disconnected");
            client.close();
            System.exit(0);
        } catch (MqttException me) {
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);
            me.printStackTrace();
        }
    }
}
