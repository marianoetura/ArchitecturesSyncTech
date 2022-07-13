package com.example.synctecharchitectures.mvi

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.synctecharchitectures.databinding.ActivityMviactivityBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MVIActivity : AppCompatActivity() {

    private val scope by lazy { CoroutineScope(Job() + Dispatchers.Main) }
    private lateinit var binding: ActivityMviactivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMviactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val readViewModel = ViewModelProvider(this)[CountryViewModel::class.java]

        scope.launch {
            readViewModel.viewState.collect { screenState ->
                binding.apply {
                    mviLoading.isVisible = screenState.showLoading
                    mviData.isVisible = !screenState.showLoading
                    var countries = ""
                    screenState.countries?.forEach { country ->
                        countries = "$countries, ${country.countryName}"
                    }
                    mviData.text = countries
                }
            }
        }

    }

    companion object {
        fun getIntent(context: Context) = Intent(context, MVIActivity::class.java)
    }
}