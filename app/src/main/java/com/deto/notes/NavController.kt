package com.deto.notes

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.deto.notes.ui.screens.HomeScreen
import com.deto.notes.ui.screens.NoteScreen
import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
object Note

@Composable
fun Navigation(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Home) {
        composable<Home> {
            HomeScreen(navController)
        }
        composable<Note> {
            NoteScreen(navController)
        }
    }

}