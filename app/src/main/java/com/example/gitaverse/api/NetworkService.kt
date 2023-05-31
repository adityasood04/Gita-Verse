package com.example.gitaverse.api

import com.example.gitaverse.models.RandomShloka
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface NetworkService {

    @GET("slok/{chapter}/{slok}")
    suspend fun getRandomShlok(
        @Path(value = "chapter", encoded = true) chapter: Int,
        @Path(value = "slok", encoded = true) slok: Int
    ): Response<RandomShloka>

}