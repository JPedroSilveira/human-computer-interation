package br.com.joaopedrosilveira.lab02

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.joaopedrosilveira.lab02.enum.Extra
import br.com.joaopedrosilveira.lab02.enum.Screen
import br.com.joaopedrosilveira.lab02.listenner.AccelerometerSensorListener
import br.com.joaopedrosilveira.lab02.listenner.LuminositySensorListener
import br.com.joaopedrosilveira.lab02.listenner.TemperatureSensorListener
import br.com.joaopedrosilveira.lab02.screen.AccelerometerScreen
import br.com.joaopedrosilveira.lab02.screen.MessageScreen
import br.com.joaopedrosilveira.lab02.screen.SensorScreen
import br.com.joaopedrosilveira.lab02.screen.SumScreen
import br.com.joaopedrosilveira.lab02.ui.theme.Lab02Theme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val accelerometerSensorListener = AccelerometerSensorListener(this)
        val luminositySensorListener = LuminositySensorListener(this)
        val temperatureSensorListener = TemperatureSensorListener(this)

        val screen = intent.getStringExtra(Extra.SCREEN.name) ?: ""

        setContent {
            Lab02Theme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background)
                {
                    Menu(
                        this,
                        screen,
                        accelerometerSensorListener,
                        luminositySensorListener,
                        temperatureSensorListener,
                        cleanListeners = {
                            accelerometerSensorListener.unregister()
                            luminositySensorListener.unregister()
                            temperatureSensorListener.unregister()
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun Menu(
    activity: Activity,
    screenName: String,
    accelerometerSensorListener: AccelerometerSensorListener,
    luminositySensorListener: LuminositySensorListener,
    temperatureSensorListener: TemperatureSensorListener,
    cleanListeners: () -> Unit
) {
    var selectedScreen by rememberSaveable {
        val screen = Screen.fromName(screenName) ?: Screen.SUM
        mutableStateOf(screen)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(28.dp))
        when(selectedScreen) {
            Screen.SUM -> SumScreen()
            Screen.INPUT_TEXT -> MessageScreen(activity)
            Screen.ACCELEROMETER -> AccelerometerScreen(activity, accelerometerSensorListener)
            Screen.SENSOR -> SensorScreen(
                activity, luminositySensorListener, temperatureSensorListener
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        MenuButton(Screen.SUM.value, onClick = {
            cleanListeners()
            selectedScreen = Screen.SUM
        })
        Spacer(modifier = Modifier.height(4.dp))
        MenuButton(Screen.INPUT_TEXT.value, onClick = {
            cleanListeners()
            selectedScreen = Screen.INPUT_TEXT
        })
        Spacer(modifier = Modifier.height(4.dp))
        MenuButton(Screen.ACCELEROMETER.value, onClick = {
            cleanListeners()
            selectedScreen = Screen.ACCELEROMETER
        })
        Spacer(modifier = Modifier.height(4.dp))
        MenuButton(Screen.SENSOR.value, onClick = {
            cleanListeners()
            selectedScreen = Screen.SENSOR
        })
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
private fun MenuButton(text: String, onClick: () -> Unit) {
    OutlinedButton(
        contentPadding = PaddingValues(vertical = 10.dp, horizontal = 16.dp),
        onClick = onClick
    ) {
        Text(text, fontSize = 16.sp)
    }
}