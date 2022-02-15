package br.com.joaopedrosilveira.lab02.listenner

import android.content.Context
import android.hardware.Sensor

class LuminositySensorListener(
    context: Context
) : BaseSensorListener(context = context, sensor = Sensor.TYPE_LIGHT)