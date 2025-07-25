package com.deto.notes.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.deto.notes.NotePage
import com.deto.notes.ui.theme.onPrimaryDark
import com.deto.notes.ui.theme.onPrimaryLight


@Composable
fun CustomFloatingActionButtonHome(navController: NavController) {
    FloatingActionButton(
        onClick = { navController.navigate(NotePage()) },
        containerColor = onPrimaryLight,
        contentColor = onPrimaryDark,
        shape = RoundedCornerShape(30.dp)
    ) {
        Icon(
            Icons.Default.Add,
            contentDescription = "navigate",
            modifier = Modifier.size(32.dp)
        )
    }
}