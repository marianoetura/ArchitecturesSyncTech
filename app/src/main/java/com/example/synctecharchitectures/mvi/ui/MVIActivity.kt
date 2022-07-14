package com.example.synctecharchitectures.mvi.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.synctecharchitectures.databinding.ArchitectureActivityBinding
import com.example.synctecharchitectures.ui.CountriesView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MVIActivity : AppCompatActivity() {

    private val scope by lazy { CoroutineScope(Job() + Dispatchers.Main) }
    private lateinit var viewModel: MviViewModel
    private lateinit var countriesView: CountriesView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setActivityUI("MVI Activity")
        setViewModel()
    }

    private fun setActivityUI(activityTitle: String) {
        title = activityTitle
        val binding = ArchitectureActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        countriesView = CountriesView(binding, ::onRetry)
    }

    private fun setViewModel() {
        viewModel = ViewModelProvider(this)[MviViewModel::class.java]
        scope.launch {
            viewModel.viewState.collect { screenState ->
                countriesView.updateMviUI(screenState)
            }
        }
    }

    private fun onRetry() {
        viewModel.onRefresh()
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, MVIActivity::class.java)
    }
}