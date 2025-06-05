package com.deto.notes.data

import kotlinx.coroutines.flow.Flow

class LocalNoteRepository(private val itemDao : NoteDao) : NoteRepository {
    override suspend fun insertNote(note: Note) = itemDao.insert(note)

    override suspend fun updateNote(note: Note) = itemDao.update(note)

    override suspend fun deleteNoteById(id: Int) = itemDao.deleteById(id)

    override fun getAllNotesStream(): Flow<List<Note>> = itemDao.getAllItems()
}