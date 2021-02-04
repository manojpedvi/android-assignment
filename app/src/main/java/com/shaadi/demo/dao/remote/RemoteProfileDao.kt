package com.shaadi.demo.dao.remote

import com.shaadi.demo.model.ProfileResponse
import retrofit2.http.GET

interface RemoteProfileDao {
    @GET("api?results=10")
    suspend fun getProfiles() : ProfileResponse
}