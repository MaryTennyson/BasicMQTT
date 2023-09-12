package com.ebraratabay.mqttexample.service

import org.eclipse.paho.client.mqttv3.MqttClient
import org.eclipse.paho.client.mqttv3.MqttConnectOptions
import org.eclipse.paho.client.mqttv3.MqttException
import org.eclipse.paho.client.mqttv3.MqttMessage
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence

class MQTTService {
    val topic = "<TOPIC>"
    val msg = "<Message>"
    var qos = 2
    var broker = "ssl://<CLUSTER_URL>.s1.eu.hivemq.cloud:8883"
    var clientId = "KotlinSample"
    var persistence = MemoryPersistence()
    val sampleClient = MqttClient(broker, clientId, persistence)
    val connOpts = MqttConnectOptions()
    val userName = "<USERNAME>"
    val password = "<PASSWORD>"

    fun sendData() {
        try {
            if (sampleClient.isConnected) {
                sendMessage()
            } else {
                connOpts.isCleanSession = true
                connOpts.userName = userName
                connOpts.password = password.toCharArray()
                println("Connecting to broker: $broker")
                sampleClient.connect(connOpts)
                println("Connected")
                sendMessage()
            }
        } catch (e: MqttException) {
            println("reason " + e.getReasonCode())
            println("msg " + e.message)
            println("loc " + e.getLocalizedMessage())
            println("cause " + e.cause)
            println("excep $e")
            e.printStackTrace()
        }
    }

    private fun sendMessage() {
        println("Publishing message: $msg")
        val message = MqttMessage(msg.toByteArray())
        message.qos = qos
        sampleClient.publish(topic, message)
        println("Message published")

    }

    fun disconnectBroker() {
        if (sampleClient.isConnected) {
            sampleClient.disconnect()
            sampleClient.isConnected
            println("Disconnected")
        }
    }
}



