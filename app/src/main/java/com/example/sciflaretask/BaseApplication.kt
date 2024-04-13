package com.example.sciflaretask

import android.app.Application
import com.example.sciflaretask.database.UserDatabase

class BaseApplication : Application() {
    companion object {
        lateinit var roomDb: UserDatabase
    }

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        roomDb = UserDatabase.getDatabase(applicationContext)
    }
}