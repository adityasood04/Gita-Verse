package com.example.gitaverse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.gitaverse.api.NetworkService
import com.example.gitaverse.api.RetrofitHelper
import com.example.gitaverse.databinding.ActivityMainBinding
import com.example.gitaverse.repository.ShlokRepository
import com.example.gitaverse.viewmodels.MainViewModel
import com.example.gitaverse.viewmodels.MainViewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var  mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        val networkService = RetrofitHelper.getInstance().create(NetworkService::class.java)
        val repository = ShlokRepository(networkService)


        mainViewModel = ViewModelProvider(this,MainViewModelFactory(repository)).get(MainViewModel::class.java)
        mainViewModel.shlok.observe(this, Observer {
            Log.i("adi", "onCreate:${it.slok.toString()} ")
        })

    }
}