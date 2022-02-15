package br.com.joaopedrosilveira.lab02.listenner

import android.content.Context
import android.hardware.Sensor

class TemperatureSensorListener(context: Context) : BaseSensorListener(
    context = context,
    sensor = Sensor.TYPE_AMBIENT_TEMPERATURE
)