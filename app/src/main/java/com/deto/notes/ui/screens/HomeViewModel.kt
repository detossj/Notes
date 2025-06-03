package com.deto.notes.ui.screens

import androidx.lifecycle.ViewModel
import com.deto.notes.data.NoteRepository


class HomeViewModel (private val noteRepository: NoteRepository) : ViewModel() {

    val noteList = noteRepository.getAllNotesStream()

}