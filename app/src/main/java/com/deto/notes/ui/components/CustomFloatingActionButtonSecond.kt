package com.deto.notes.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


@Composable
fun CustomFloatingActionButtonSecond(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = { onClick() },
        containerColor = Color.Yellow,
        contentColor = Color.White
    ) {
        Icon(Icons.Default.Add, contentDescription = "Agregar Tarea")
    }
}