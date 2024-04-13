package com.example.sciflaretask.network

import com.example.sciflaretask.dto.UserDetails
import com.terareum.exchange.model.network.APIConfigHelper
import com.terareum.exchange.model.network.APIServices

class APIRepository : APIServices {

    override suspend fun getUser(): List<UserDetails> {
        return APIConfigHelper.getRetrofitInstance().getUser()
    }

}