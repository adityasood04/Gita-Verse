package com.example.gitaverse.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.gitaverse.api.NetworkService
import com.example.gitaverse.models.RandomShloka

class ShlokRepository(private  val networkService: NetworkService) {

    private val shlokLiveData = MutableLiveData<RandomShloka>()
    val randomShloka:LiveData<RandomShloka>
    get() = shlokLiveData


    suspend fun getRandomShlok(){
        val chapter = (1..18).random()
        val verse = (1..20).random()
        val result = networkService.getRandomShlok(chapter,verse)
        if (result?.body() != null){
            shlokLiveData.postValue(result.body())
        }
    }
}