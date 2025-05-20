package com.deto.notes.data

import kotlinx.coroutines.flow.Flow

class LocalTaskRepository(private val itemDao: TaskDao) : TaskRepository {
    override suspend fun insertTask(task: Task) = itemDao.insert(task)

    override suspend fun updateTask(task: Task) = itemDao.update(task)

    override suspend fun deleteTask(task: Task) = itemDao.delete(task)

    override fun getAllTasksStream(): Flow<List<Task>> = itemDao.getAllItems()

}