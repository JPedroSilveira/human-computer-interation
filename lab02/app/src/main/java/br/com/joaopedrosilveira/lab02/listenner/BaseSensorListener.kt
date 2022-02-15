package br.com.joaopedrosilveira.lab02.listenner

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager

abstract class BaseSensorListener(
    context: Context,
    sensor: Int
) : SensorEventListener {
    private var onChange: ((SensorEvent) -> Unit)? = null;
    private val sensorManager: SensorManager =
        context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private val accelerometer: Sensor =
        sensorManager.getDefaultSensor(sensor)

    override fun onSensorChanged(event: SensorEvent?) {
        if (event != null) {
            onChange?.invoke(event)
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {}

    fun register(onChange: (SensorEvent) -> Unit) {
        this.onChange = onChange;

        sensorManager.registerListener(
            this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)
    }

    fun unregister() {
        this.onChange = null;

        sensorManager.unregisterListener(this)
    }
}