package com.example.synctecharchitectures.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.synctecharchitectures.model.Country
import com.example.synctecharchitectures.model.coroutines.CountriesService
import kotlinx.coroutines.launch
import java.lang.Exception

class MVVMViewModel(
    private val service: CountriesService = CountriesService()
) : ViewModel() {

    val countries: MutableLiveData<List<Country>> = MutableLiveData()
    val error: MutableLiveData<Boolean> = MutableLiveData()

    init {
        viewModelScope.launch {
            fetchPreferences()
        }
    }

    private suspend fun fetchPreferences() {
        try {
            countries.value = service.getCountries().body()
            error.value = false
        } catch (ex: Exception) {
            error.value = true
        }

    }

    fun onRefresh() = viewModelScope.launch { fetchPreferences() }
}