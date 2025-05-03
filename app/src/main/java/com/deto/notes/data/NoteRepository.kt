package com.deto.notes.data

import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    suspend fun insertNote(note: Note)

    suspend fun updateNote(note: Note)

    suspend fun deleteNote(note: Note)

    fun getNoteStream(title: String, content: String): Flow<Note?>

    fun getAllNotesStream(): Flow<List<Note>>

}