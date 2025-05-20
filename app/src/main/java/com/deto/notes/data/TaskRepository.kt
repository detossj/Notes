package com.deto.notes.data

import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    suspend fun insertTask(task: Task)

    suspend fun updateTask(task: Task)

    suspend fun deleteTask(task: Task)

    fun getAllTasksStream(): Flow<List<Task>>
}