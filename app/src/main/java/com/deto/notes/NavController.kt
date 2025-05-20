package com.deto.notes

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.deto.notes.ui.screens.HomeScreen
import com.deto.notes.ui.screens.NewNoteScreen
import com.deto.notes.ui.screens.SecondScreen
import kotlinx.serialization.Serializable

@Serializable
object HomePage

@Serializable
object SecondPage

@Serializable
data class NotePage(val noteId: Int? = null)

@Composable
fun Navigation(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = HomePage) {
        composable<HomePage> {
            HomeScreen(navController)
        }
        composable<SecondPage> {
            SecondScreen(navController)
        }
        composable<NotePage> { backStackEntry ->
            val args = backStackEntry.toRoute<NotePage>()
            NewNoteScreen(navController, noteId = args.noteId)
        }
    }

}