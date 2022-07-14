package com.example.synctecharchitectures.mvp.presenter

import com.example.synctecharchitectures.model.CountriesFromWebRepository
import com.example.synctecharchitectures.mvp.view.MVPView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MVPPresenter(
    private val view: MVPView,
    private val service: CountriesFromWebRepository = CountriesFromWebRepository(),
    private val scope: CoroutineScope = CoroutineScope(Job() + Dispatchers.IO)

) {
    fun initPresenter() {
        scope.launch { fetchCountries() }
    }

    private suspend fun fetchCountries() {
        view.showLoading()
        try {
            service.fetchCountries().body()?.let {
                view.setValues(it)
            }
        } catch (ignored: Exception) {
            view.onError()
        }
    }

    fun onRefresh() {
        scope.launch { fetchCountries() }
    }
}