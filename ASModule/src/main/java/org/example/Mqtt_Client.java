package org.example;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class Mqtt_Client {

    public static MqttAsyncClient myClient;

    public static void main(String[] args) {

        // setting up the broker ip-adress and port, as well as the client ID, the broker can be seen as the publisher
        String broker = "";
        String clientID = "";

        try {

            //creating a mqtt client with the broker and client id, as well as an onMessageCallback-object
            myClient = new MqttAsyncClient(broker,clientID);
            OnMessageCallback myCallback = new OnMessageCallback();

            myClient.setCallback(myCallback);



        } catch (MqttException e) {
            e.printStackTrace();
        }


    }
}
