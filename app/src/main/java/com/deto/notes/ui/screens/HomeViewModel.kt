package com.deto.notes.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deto.notes.data.LocalNoteRepository

import com.deto.notes.data.NoteRepository
import kotlinx.coroutines.launch

class HomeViewModel (private val noteRepository: NoteRepository) : ViewModel() {

    val noteList = noteRepository.getAllNotesStream()

}