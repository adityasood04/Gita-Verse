package com.example.gitaverse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.speech.tts.TextToSpeech
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.gitaverse.api.NetworkService
import com.example.gitaverse.api.RetrofitHelper
import com.example.gitaverse.databinding.ActivityMainBinding
import com.example.gitaverse.repository.ShlokRepository
import com.example.gitaverse.viewmodels.MainViewModel
import com.example.gitaverse.viewmodels.MainViewModelFactory
import java.lang.Exception
import java.util.Locale

class MainActivity : AppCompatActivity(),TextToSpeech.OnInitListener {
    lateinit var binding: ActivityMainBinding
    lateinit var mainViewModel: MainViewModel
    private var tts: TextToSpeech? = null
    lateinit var shlok:String
    lateinit var meaning:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        tts = TextToSpeech(this, this)
        val networkService = RetrofitHelper.getInstance().create(NetworkService::class.java)
        val repository = ShlokRepository(networkService)

        mainViewModel =
            ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)
        binding.pbMain.visibility = View.VISIBLE
        mainViewModel.shlok.observe(this, Observer {
            binding.rShlok = it
            shlok =it.slok
            meaning = it.tej.ht
            binding.chapter.text = it.chapter.toString()
            binding.verse.text = it.verse.toString()
            binding.pbMain.visibility = View.GONE
            binding.llDetails.visibility=View.VISIBLE
        })
        binding.ivSpeakShlok.setOnClickListener {
            speakOutShlok()
        }
        binding.ivSpeakMeaning.setOnClickListener {
            speakOutMeaning()
        }

    }

    private fun speakOutMeaning() {
        try {
            tts!!.speak(meaning, TextToSpeech.QUEUE_FLUSH, null,"")
        }catch (e:Exception){
            Log.i("TTS", "speakOut:${e.message.toString()} ")

        }
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts!!.setLanguage(Locale.forLanguageTag("hin"))

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS","The Language not supported!")
            } else {
                binding.ivSpeakShlok.isEnabled = true
                binding.ivSpeakMeaning.isEnabled=true
            }
        }
    }

    private fun speakOutShlok() {
        try {
            Log.i("TTS", "speakOut:${shlok.toString()} ")
            tts!!.speak(shlok, TextToSpeech.QUEUE_FLUSH, null,"")
        }catch (e:Exception){
            Log.i("TTS", "speakOut:${e.message.toString()} ")

        }

    }
    public override fun onDestroy() {
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }


}