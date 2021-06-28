package com.indialone.sensorsurvey_codelab

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class LightAndProximitySensorActivity : AppCompatActivity(), SensorEventListener {

    private var mSensorManager: SensorManager? = null

    private var mSensorLight: Sensor? = null
    private var mSensorProximity: Sensor? = null

    private var mTextSensorLight: TextView? = null
    private var mTextSensorProximity: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_light_sensor)

        // finds textview
        mTextSensorLight = findViewById(R.id.label_light)
        mTextSensorProximity = findViewById(R.id.label_proximity)

        // initialize sensor manager
        mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        // set the light and proximity sensors to the variables
        mSensorLight = mSensorManager!!.getDefaultSensor(Sensor.TYPE_LIGHT)
        mSensorProximity = mSensorManager!!.getDefaultSensor(Sensor.TYPE_PROXIMITY)

        //if sensor is getting blank information then print for respective sensor text view the sensorError text
        val sensorError = resources.getString(R.string.error_no_sensor)
        if (mSensorLight == null) {
            mTextSensorLight!!.text = sensorError
        }

        if (mSensorProximity == null) {
            mTextSensorProximity!!.text = sensorError
        }
    }

    override fun onStart() {
        super.onStart()

        if (mSensorProximity != null) {
            mSensorManager!!.registerListener(
                this, mSensorProximity,
                SensorManager.SENSOR_DELAY_NORMAL
            )
        }
        if (mSensorLight != null) {
            mSensorManager!!.registerListener(
                this, mSensorLight,
                SensorManager.SENSOR_DELAY_NORMAL
            )
        }
    }

    override fun onStop() {
        super.onStop()
        mSensorManager!!.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        val sensorType = event?.sensor?.type
        val currentValue = event!!.values[0]
        when (sensorType) {
            Sensor.TYPE_LIGHT -> {
                mTextSensorLight!!.text = "Light Sensor: $currentValue"
            }
            Sensor.TYPE_PROXIMITY -> {
                mTextSensorProximity!!.text = "Proximity Sensor :$currentValue"
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

}