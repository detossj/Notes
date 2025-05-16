package com.deto.notes.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.deto.notes.data.Note
import com.deto.notes.data.NoteRepository

class NewNoteViewModel(private val noteRepository: NoteRepository) : ViewModel() {

    var newNoteUiState by mutableStateOf(NewNoteUIState())
        private set

    fun updateUiState(newNote: NewNote){
        newNoteUiState =
            NewNoteUIState(newNote = newNote, isEntryValid = validateInput(newNote))
    }

    suspend fun saveItem() {
        if(validateInput()){
            val note = newNoteUiState.newNote.toElement()
            if (note.id != 0) {
                noteRepository.updateNote(note)
            } else {
                noteRepository.insertNote(note)
            }
        }
    }

    private fun validateInput(uiState: NewNote = newNoteUiState.newNote) : Boolean {
        return with(uiState) {
            title.isNotBlank() || content.isNotBlank()
        }
    }

}
data class NewNoteUIState(
    val newNote: NewNote = NewNote(),
    val isEntryValid: Boolean = false
)

data class NewNote(
    val id: Int = 0,
    val title: String = "",
    val content: String = "",
    //val date: LocalDateTime
)

fun NewNote.toElement(): Note = Note(
    id = id,
    title = title,
    content = content,
    //val date: LocalDateTime
)

fun Note.toItemUiState(isEntryValid: Boolean = false): NewNoteUIState = NewNoteUIState(
    newNote = this.toItemDetails(),
    isEntryValid = isEntryValid
)

fun Note.toItemDetails(): NewNote = NewNote(
    id = id,
    title = title,
    content = content,
    //val date: LocalDateTime
)