package com.deto.notes.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.deto.notes.Notes
import com.deto.notes.ui.screens.HomeViewModel
import com.deto.notes.ui.screens.NewNoteViewModel

import com.deto.tasks.ui.screens.SecondViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(App().container.noteRepository)
        }
        initializer {
            NewNoteViewModel(App().container.noteRepository)
        }
        initializer {
            SecondViewModel(App().container.taskRepository)
        }
    }
}

fun CreationExtras.App(): Notes =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as Notes)