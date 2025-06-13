package com.deto.notes.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Note)

    @Update
    suspend fun update(item: Note)

    @Query("DELETE FROM notes WHERE id= :id")
    suspend fun deleteById(id: Int)

    @Query("SELECT * from notes")
    fun getAllItems(): Flow<List<Note>>
}