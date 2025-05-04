package com.deto.notes.data

import kotlinx.coroutines.flow.Flow

class LocalNoteRepository(private val itemDao : NoteDao) : NoteRepository {
    override suspend fun insertNote(note: Note) = itemDao.insert(note)

    override suspend fun updateNote(note: Note) = itemDao.update(note)

    override suspend fun deleteNote(note: Note) = itemDao.delete(note)

    override fun getNoteStream(title: String, content: String): Flow<Note?> = itemDao.getItem(title,content)

    override fun getAllNotesStream(): Flow<List<Note>> = itemDao.getAllItems()
}