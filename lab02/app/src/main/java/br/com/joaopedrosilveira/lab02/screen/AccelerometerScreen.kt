package br.com.joaopedrosilveira.lab02.screen

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import br.com.joaopedrosilveira.lab02.AccelerometerChangeActivity
import br.com.joaopedrosilveira.lab02.listenner.AccelerometerSensorListener
import kotlin.math.abs

const val sensibility = 1.0f;

@Composable
fun AccelerometerScreen(
    context: Context,
    accelerometerSensorListener: AccelerometerSensorListener
) {
    var x by rememberSaveable { mutableStateOf(0.0f) }
    var y by rememberSaveable { mutableStateOf(0.0f) }
    var z by rememberSaveable { mutableStateOf(0.0f) }
    var firstRead by rememberSaveable { mutableStateOf(true) }

    accelerometerSensorListener.register(onChange = {
        val newX = it.values[0]
        val newY = it.values[1]
        val newZ = it.values[2]

        val wasChangedAbruptly = !firstRead && (abs(newX - x) > sensibility
                || abs(newY - y) > sensibility
                || abs(newZ - z) > sensibility)

        if (wasChangedAbruptly) {
            accelerometerSensorListener.unregister()
            changeScreen(context)
        } else {
            x = newX
            y = newY
            z = newZ
            firstRead = false
        }
    })

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center) {
            Text("X: $x")
            Spacer(modifier = Modifier.width(4.dp))
            Text("Y: $y")
            Spacer(modifier = Modifier.width(4.dp))
            Text("Z: $z")
        }
    }
}

private fun changeScreen(context: Context) {
    val intent = Intent(context, AccelerometerChangeActivity::class.java)
    ContextCompat.startActivity(context, intent, null)
}