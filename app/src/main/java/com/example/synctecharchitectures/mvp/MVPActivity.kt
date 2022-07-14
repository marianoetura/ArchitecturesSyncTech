package com.example.synctecharchitectures.mvp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.synctecharchitectures.databinding.ArchitectureActivityBinding
import com.example.synctecharchitectures.model.dto.Country
import com.example.synctecharchitectures.mvp.presenter.MVPPresenter
import com.example.synctecharchitectures.mvp.view.MVPView
import com.example.synctecharchitectures.ui.CountriesView

class MVPActivity : AppCompatActivity(), MVPView {

    private lateinit var presenter: MVPPresenter
    private lateinit var countriesView: CountriesView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setActivityUI("MVP Activity")
        setPresenter()
    }

    override fun onStart() {
        super.onStart()
        presenter.initPresenter()
    }

    private fun setActivityUI(activityTitle: String) {
        title = activityTitle
        val binding = ArchitectureActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        countriesView = CountriesView(binding, ::onRetry)

    }

    private fun setPresenter() {
        presenter = MVPPresenter(this)
    }

    override fun setValues(countries: List<Country>) {
        runOnUiThread {
            countriesView.showMvpData(countries)
        }
    }

    override fun onError() {
        runOnUiThread {
            countriesView.showMvpError()
        }
    }

    override fun showLoading() {
        runOnUiThread {
            countriesView.showMvpLoading()
        }
    }

    private fun onRetry() {
        presenter.onRefresh()
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, MVPActivity::class.java)
    }
}