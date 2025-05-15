package com.deto.notes

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.deto.notes.ui.screens.HomeScreen
import com.deto.notes.ui.screens.NewNoteScreen
import kotlinx.serialization.Serializable

@Serializable
object HomePage

@Serializable
data class NotePage(val noteId: Int)

@Composable
fun Navigation(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = HomePage) {
        composable<HomePage> {
            HomeScreen(navController)
        }
        composable<NotePage> { backStackEntry ->
            val args = backStackEntry.toRoute<NotePage>()
            NewNoteScreen(navController, noteId = args.noteId)
        }
    }

}