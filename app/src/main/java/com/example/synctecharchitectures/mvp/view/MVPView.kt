package com.example.synctecharchitectures.mvp.view

interface MVPView {
    fun setValues(countries: List<String>)
    fun onError()
}