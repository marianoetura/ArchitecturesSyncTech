package com.example.synctecharchitectures.mvvm

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.synctecharchitectures.databinding.ArchitectureActivityBinding
import com.example.synctecharchitectures.ui.CountriesView

class MVVMActivity : AppCompatActivity() {

    private lateinit var viewModel: MVVMViewModel
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
        viewModel = ViewModelProvider(this)[MVVMViewModel::class.java].apply {
            countries.observe(this@MVVMActivity) { countries ->
                countriesView.showMvpData(countries)
            }
            error.observe(this@MVVMActivity) { isFailed ->
                if (isFailed) countriesView.showMvpError()
            }
            loading.observe(this@MVVMActivity) { isLoading ->
                if (isLoading) countriesView.showMvpLoading()
            }
        }
    }

    private fun onRetry() {
        viewModel.onRefresh()
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, MVVMActivity::class.java)
    }
}