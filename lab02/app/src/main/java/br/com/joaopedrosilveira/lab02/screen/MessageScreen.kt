package br.com.joaopedrosilveira.lab02.screen;

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
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
import androidx.core.content.ContextCompat.startActivity
import br.com.joaopedrosilveira.lab02.TextActivity
import br.com.joaopedrosilveira.lab02.enum.Extra

@Composable
fun MessageScreen(context: Context) {
        var message by rememberSaveable { mutableStateOf("") }
        Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
        ) {
                TextInput(message, onValueChange = {
                        message = it
                })
                Spacer(modifier = Modifier.height(8.dp))
                SendMessageButton(onClick = {
                        sendMessage(context, message)
                })
        }
}

private fun sendMessage(context: Context, message: String) {
        val intent = Intent(context, TextActivity::class.java).apply {
                putExtra(Extra.MESSAGE.name, message)
        }
        startActivity(context, intent, null)
}

@Composable
private fun TextInput(text: String, onValueChange: (String) -> Unit) {
        OutlinedTextField(
                value = text,
                onValueChange = onValueChange,
                label = {
                        Text("Enter a text")
                }
        )
}

@Composable
private fun SendMessageButton(onClick: () -> Unit) {
        OutlinedButton(
                contentPadding = PaddingValues(vertical = 10.dp, horizontal = 16.dp),
                onClick = onClick
        ) {
                Text("Send Message", fontSize = 16.sp)
        }
}