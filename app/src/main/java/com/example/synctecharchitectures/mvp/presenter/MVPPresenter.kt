package com.example.synctecharchitectures.mvp.presenter

import com.example.synctecharchitectures.model.CountriesService
import com.example.synctecharchitectures.model.Country
import com.example.synctecharchitectures.mvp.view.MVPView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class MVPPresenter(
    private val view: MVPView,
    private val service: CountriesService = CountriesService()
) {

    fun initPresenter() {
        fetchCountries()
    }

    private fun fetchCountries() {
        service.getCountries()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<List<Country>>() {
                override fun onSuccess(value: List<Country>) {
                    val countryNames: MutableList<String> = ArrayList()
                    for (country in value) {
                        countryNames.add(country.countryName)
                    }
                    view.setValues(countryNames)
                }

                override fun onError(e: Throwable) {
                    view.onError()
                }
            })
    }

    fun onRefresh() {
        fetchCountries()
    }
}