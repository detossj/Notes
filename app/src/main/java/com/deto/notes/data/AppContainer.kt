package com.deto.notes.data

import android.content.Context

interface AppContainer {
    val noteRepository: NoteRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val noteRepository: NoteRepository by lazy {
        LocalNoteRepository(AppDatabase.getDatabase(context).noteDao())
    }

}