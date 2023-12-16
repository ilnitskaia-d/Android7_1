package com.example.android7

import android.app.Application
import androidx.room.Room
import com.example.android7.data.database.ItemDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App: Application() {

    companion object {
        lateinit var appDatabase: ItemDatabase
    }

    override fun onCreate() {
        super.onCreate()
        appDatabase = Room.databaseBuilder(applicationContext, ItemDatabase::class.java, "item")
            .allowMainThreadQueries().build()
    }
}