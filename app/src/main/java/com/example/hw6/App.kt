package com.example.hw6

import android.app.Application
import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.example.hw6.data.db.NoteDatabase

class App : Application() {

    companion object {
        lateinit var instance: App
    }

    val database by lazy{
        Room.databaseBuilder(
            this,
            NoteDatabase::class.java,
            "notes.db"
        ).build()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}