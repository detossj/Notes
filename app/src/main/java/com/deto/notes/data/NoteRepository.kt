package com.deto.notes.data

import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    suspend fun insertNote(note: Note)

    suspend fun updateNote(note: Note)

    suspend fun deleteNoteById(id: Int)

    fun getAllNotesStream(): Flow<List<Note>>

}