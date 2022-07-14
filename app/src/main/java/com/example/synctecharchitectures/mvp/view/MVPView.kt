package com.example.synctecharchitectures.mvp.view

import com.example.synctecharchitectures.model.Country

interface MVPView {
    fun setValues(countries: List<Country>)
    fun onError()
    fun showLoading()
}