package br.com.joaopedrosilveira.lab02

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import br.com.joaopedrosilveira.lab02.component.GoBackButton
import br.com.joaopedrosilveira.lab02.enum.Extra
import br.com.joaopedrosilveira.lab02.enum.Screen
import br.com.joaopedrosilveira.lab02.ui.theme.Lab02Theme

class TextActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val message = intent.getStringExtra(Extra.MESSAGE.name) ?: ""

        setContent {
            Lab02Theme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background)
                {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(28.dp))
                        Text(message)
                        Spacer(modifier = Modifier.height(8.dp))
                        GoBackButton(onClick = {
                            goBack(this@TextActivity)
                        })
                    }
                }
            }
        }
    }
}

private fun goBack(context: Context) {
    val intent = Intent(context, MainActivity::class.java).apply {
        putExtra(Extra.SCREEN.name, Screen.INPUT_TEXT.name)
    }
    ContextCompat.startActivity(context, intent, null)
}