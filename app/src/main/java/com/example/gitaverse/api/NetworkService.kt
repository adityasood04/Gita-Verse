package com.example.gitaverse.api

import com.example.gitaverse.models.RandomShloka
import retrofit2.Response
import retrofit2.http.GET

interface NetworkService {

    @GET("/slok")
    suspend fun getRandomShlok():Response<RandomShloka>


}