package br.com.joaopedrosilveira.lab02.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SumScreen() {
    var result by rememberSaveable { mutableStateOf("0") }
    var entryOne by rememberSaveable { mutableStateOf("") }
    var entryTwo by rememberSaveable { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NumberInput(entryOne, onValueChange = {
            entryOne = it
        })
        NumberInput(entryTwo, onValueChange = {
            entryTwo = it
        })
        Spacer(modifier = Modifier.height(16.dp))
        SumButton(onClick = {
            if (entryOne.isNotBlank() && entryTwo.isNotBlank()) {
                val sum = entryOne.filter { !it.isWhitespace() }.toInt() +
                        entryTwo.filter { !it.isWhitespace() }.toInt()
                result = sum.toString()
            }
        })
        Spacer(modifier = Modifier.height(16.dp))
        Result(result)
    }
}

@Composable
private fun Result(value: String) {
    Card(
        border = BorderStroke(1.dp, Color.Black)
    ){
        if (value.isNotBlank()) {
            Text(
                text = "Result: $value",
                fontSize = 16.sp,
                modifier = Modifier.padding(20.dp)
            )
        }
    }
}

@Composable
private fun NumberInput(text: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        value = text,
        onValueChange = onValueChange,
        label = {
            Text("Enter a number")
        }
    )
}

@Composable
private fun SumButton(onClick: () -> Unit) {
    OutlinedButton(
        contentPadding = PaddingValues(vertical = 10.dp, horizontal = 16.dp),
        onClick = onClick
    ) {
        Text("SUM", fontSize = 16.sp)
    }
}