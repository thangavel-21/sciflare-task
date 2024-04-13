package com.terareum.exchange.model.network

import com.example.sciflaretask.dto.UserDetails
import retrofit2.http.GET


interface APIServices {

    @GET("users")
    suspend fun getUser(): List<UserDetails>
}