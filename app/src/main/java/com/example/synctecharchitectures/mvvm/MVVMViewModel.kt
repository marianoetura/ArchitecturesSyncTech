package com.example.synctecharchitectures.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.synctecharchitectures.model.Country
import com.example.synctecharchitectures.model.coroutines.CountriesService
import kotlinx.coroutines.launch

class MVVMViewModel(
    private val service: CountriesService = CountriesService()
) : ViewModel() {

    val countries: MutableLiveData<List<Country>> = MutableLiveData()
    val loading: MutableLiveData<Boolean> = MutableLiveData()
    val error: MutableLiveData<Boolean> = MutableLiveData()

    init {
        onRefresh()
    }

    private suspend fun fetchCountries() {
        loading.value = true
        try {
            countries.value = service.getCountries().body()
            error.value = false
            loading.value = false
        } catch (ignored: Exception) {
            loading.value = false
            error.value = true
        }
    }

    fun onRefresh() = viewModelScope.launch { fetchCountries() }
}
