package com.example.sciflaretask.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.sciflaretask.dto.UserDetails

@Entity(tableName = "user")
data class UserEntityData(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val mail: String,
    val gender: String,
    val mobile: String,
)

fun UserEntityData.toUserDetails(): UserDetails {
    return UserDetails(
        name = name,
        email = mail,
        gender = gender,
        mobile = mobile
    )
}
