package com.example.gitaverse.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.gitaverse.api.NetworkService
import com.example.gitaverse.models.RandomShloka

class ShlokRepository(private  val networkService: NetworkService) {

    private val shlokLiveData = MutableLiveData<RandomShloka>()
    val randomShloka:LiveData<RandomShloka>
    get() = shlokLiveData


    suspend fun getRandomShlok(){
        val result = networkService.getRandomShlok()
        if (result?.body() != null){
            shlokLiveData.postValue(result.body())
        }
    }
}