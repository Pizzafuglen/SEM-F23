ASModule/src/main/java/org/example/OnMessageCallback.java [9:22]:
- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    public void connectionLost(Throwable cause) {
        // After the connection is lost, it usually reconnects here
        System.out.println("disconnect，you can reconnect");
    }

    public void messageArrived(String topic, MqttMessage message) throws Exception {
        // The messages obtained after subscribe will be executed here
        System.out.println("Received message topic:" + topic);
        System.out.println("Received message Qos:" + message.getQos());
        System.out.println("Received message content:" + new String(message.getPayload()));
    }

    public void deliveryComplete(IMqttDeliveryToken token) {
        System.out.println("deliveryComplete---------" + token.isComplete());
- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -



ASModule/src/main/java/org/example/OnMessageCallback2.java [9:23]:
- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    public void connectionLost(Throwable cause) {
        // After the connection is lost, it usually reconnects here
        System.out.println("disconnect，you can reconnect");
    }

    public void messageArrived(String topic, MqttMessage message) throws Exception {
        // The messages obtained after subscribe will be executed here
        System.out.println("Received message topic:" + topic);
        System.out.println("Received message Qos:" + message.getQos());
        System.out.println("Received message content:" + new String(message.getPayload()));

    }

    public void deliveryComplete(IMqttDeliveryToken token) {
        System.out.println("deliveryComplete---------" + token.isComplete());
- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -



