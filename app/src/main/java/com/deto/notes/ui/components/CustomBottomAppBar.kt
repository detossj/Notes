package com.deto.notes.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import com.deto.notes.R
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.deto.notes.HomePage
import com.deto.notes.SecondPage

@Composable
fun CustomBottomAppBar(Navigation: NavController) {

    BottomAppBar(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row (
            modifier = Modifier.fillMaxWidth()
               ,
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Button(
                onClick = {
                    Navigation.navigate(HomePage)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.White
                )
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painterResource(R.drawable.article_24px),
                        contentDescription = "Notas",
                        modifier = Modifier.size(30.dp)
                    )

                    Text("Notas")
                }
            }

            Button(
                onClick = {
                    Navigation.navigate(SecondPage)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.White
                )
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painterResource(R.drawable.assignment_turned_in_24px),
                        contentDescription = "Tareas",
                        modifier = Modifier.size(30.dp)
                    )

                    Text("Tareas")
                }
            }
        }
    }

}