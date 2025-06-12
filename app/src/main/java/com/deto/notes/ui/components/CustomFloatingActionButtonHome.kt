package com.deto.notes.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.deto.notes.NotePage
import com.deto.notes.ui.theme.onPrimaryDark
import com.deto.notes.ui.theme.onPrimaryLight


@Composable
fun CustomFloatingActionButtonHome(navController: NavController) {
    FloatingActionButton(
        onClick = { navController.navigate(NotePage()) },
        containerColor = onPrimaryLight,
        contentColor = onPrimaryDark
    ) {
        Icon(Icons.Default.Add, contentDescription = "navigate")
    }
}