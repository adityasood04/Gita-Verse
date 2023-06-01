package com.example.gitaverse.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gitaverse.repository.ShlokRepository

class MainViewModelFactory(private val shlokRepository: ShlokRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(shlokRepository) as T

    }
}