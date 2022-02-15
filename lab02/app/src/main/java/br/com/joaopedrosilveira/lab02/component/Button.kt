package br.com.joaopedrosilveira.lab02.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GoBackButton(onClick: () -> Unit) {
    OutlinedButton(
        contentPadding = PaddingValues(vertical = 10.dp, horizontal = 16.dp),
        onClick = onClick
    ) {
        Text("Go Back", fontSize = 16.sp)
    }
}