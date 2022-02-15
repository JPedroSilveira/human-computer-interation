package br.com.joaopedrosilveira.lab02.screen

import android.app.Activity
import android.content.Context
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import br.com.joaopedrosilveira.lab02.listenner.GPSLocationListener
import br.com.joaopedrosilveira.lab02.listenner.LuminositySensorListener
import br.com.joaopedrosilveira.lab02.listenner.TemperatureSensorListener

@Composable
fun SensorScreen(
    activity: Activity,
    luminositySensorListener: LuminositySensorListener,
    temperatureSensorListener: TemperatureSensorListener
) {
    var luminosityValue by rememberSaveable { mutableStateOf(0.0f) }
    var temperatureValue by rememberSaveable { mutableStateOf(0.0f) }
    var latitudeValue by rememberSaveable { mutableStateOf(0.0) }
    var longitudeValue by rememberSaveable { mutableStateOf(0.0) }

    luminositySensorListener.register(onChange = {
        luminosityValue = it.values[0]
    })

    temperatureSensorListener.register(onChange = {
        temperatureValue = it.values[0]
    })

    GPSLocationListener(activity = activity, onChange = {
        latitudeValue = it.latitude
        longitudeValue = it.longitude
    })

    Text("Luminosity: $luminosityValue")
    Text("Ambient temperature: $temperatureValue")
    Text("Latitude: $latitudeValue")
    Text("Longitude: $longitudeValue")
}