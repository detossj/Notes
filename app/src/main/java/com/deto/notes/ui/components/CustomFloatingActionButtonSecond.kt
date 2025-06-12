package com.deto.notes.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import com.deto.notes.ui.theme.onPrimaryDark
import com.deto.notes.ui.theme.onPrimaryLight


@Composable
fun CustomFloatingActionButtonSecond(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = { onClick() },
        containerColor = onPrimaryLight,
        contentColor = onPrimaryDark
    ) {
        Icon(Icons.Default.Add, contentDescription = "Agregar Tarea")
    }
}