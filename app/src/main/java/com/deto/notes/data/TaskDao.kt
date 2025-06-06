package com.deto.notes.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Task)

    @Update
    suspend fun update(item: Task)

    @Query("DELETE FROM tasks WHERE id= :id")
    suspend fun deleteById(id: Int)

    @Query("SELECT * from tasks")
    fun getAllItems(): Flow<List<Task>>
}