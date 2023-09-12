package com.ebraratabay.mqttexample.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.ebraratabay.mqttexample.R
import com.ebraratabay.mqttexample.service.MQTTService

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClicked(view: View) {
        MQTTService().sendData()
    }
}