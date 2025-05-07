package com.deto.notes

import android.app.Application
import com.deto.notes.data.AppContainer
import com.deto.notes.data.AppDataContainer

class Notes : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}