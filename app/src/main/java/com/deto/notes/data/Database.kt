package com.deto.notes.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Note::class, Task::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){
    abstract fun noteDao(): NoteDao
    abstract fun taskDao(): TaskDao

    companion object {

        private var Instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "App_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }

}