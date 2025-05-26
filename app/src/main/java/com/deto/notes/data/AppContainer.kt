package com.deto.notes.data

import android.content.Context

interface AppContainer {
    val noteRepository: NoteRepository
    val taskRepository: TaskRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val noteRepository: NoteRepository by lazy {
        LocalNoteRepository(AppDatabase.getDatabase(context).noteDao())
    }

    override val taskRepository: TaskRepository by lazy {
        LocalTaskRepository(AppDatabase.getDatabase(context).taskDao())
    }

}