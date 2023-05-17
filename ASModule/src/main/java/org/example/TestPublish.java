package org.example;

import org.eclipse.paho.client.mqttv3.*;
public class TestPublish implements MqttCallback {
    private String rs;
    public TestPublish(String rs) {
        this.rs = rs;
    }
    @Override
    public void connectionLost(Throwable throwable) {

    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        this.rs = new String(mqttMessage.getPayload());
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }
}
