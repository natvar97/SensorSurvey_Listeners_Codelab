package com.indialone.sensorsurvey_codelab

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {

    private var mSensorManager: SensorManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        val sensorList: List<Sensor> = mSensorManager!!.getSensorList(Sensor.TYPE_ALL)

        val sensorText = StringBuilder()

        for (currentSensor in sensorList) {
            sensorText.append(currentSensor.name).append(
                System.getProperty("line.separator")
            )
        }

        val sensorTextView: TextView = findViewById(R.id.sensor_list)
        sensorTextView.text = sensorText

    }
}