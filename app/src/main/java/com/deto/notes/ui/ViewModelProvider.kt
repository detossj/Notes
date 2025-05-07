package com.deto.notes.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.viewModelFactory
import com.deto.notes.Notes

object AppViewModelProvider {
    val Factory = viewModelFactory {

    }
}

fun CreationExtras.App(): Notes =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as Notes)