package com.example.gitaverse.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gitaverse.models.RandomShloka
import com.example.gitaverse.repository.ShlokRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val shlokRepository: ShlokRepository) : ViewModel() {
    init {
        viewModelScope.launch(Dispatchers.IO) {
            shlokRepository.getRandomShlok()
        }
    }


    val shlok: LiveData<RandomShloka>
        get() = shlokRepository.randomShloka
    val nextShlok: LiveData<RandomShloka>
        get() = shlokRepository.randomShloka
}