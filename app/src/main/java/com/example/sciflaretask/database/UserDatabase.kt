package com.example.sciflaretask.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.terareum.exchange.utills.Constants

@Database(entities = [UserEntityData::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase {
            synchronized(this) {
                var instance: UserDatabase? = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UserDatabase::class.java,
                        Constants.USER_DATABASE
                    )
                        .build()
                }
                return instance
            }
        }
    }
}