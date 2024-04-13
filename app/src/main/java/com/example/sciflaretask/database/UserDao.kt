package com.example.sciflaretask.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.sciflaretask.dto.UserDetails

@Dao
interface UserDao {
    @Insert
    suspend fun insert(user: UserEntityData)

    @Query("DELETE FROM user")
    suspend fun deleteAll()

    @Query("SELECT * FROM user")
    fun getAllUser(): LiveData<List<UserEntityData>>
}