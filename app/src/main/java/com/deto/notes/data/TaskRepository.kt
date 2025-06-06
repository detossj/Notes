package com.deto.notes.data

import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    suspend fun insertTask(task: Task)

    suspend fun updateTask(task: Task)

    suspend fun deleteTaskById(id: Int)

    fun getAllTasksStream(): Flow<List<Task>>
}