package com.example.synctecharchitectures.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.synctecharchitectures.model.dto.Country
import com.example.synctecharchitectures.model.CountriesFromWebRepository
import kotlinx.coroutines.launch

class MVVMViewModel(
    private val countryRepository: CountriesFromWebRepository = CountriesFromWebRepository()
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
            countries.value = countryRepository.fetchCountries().body()
            error.value = false
            loading.value = false
        } catch (ignored: Exception) {
            loading.value = false
            error.value = true
        }
    }

    fun onRefresh() = viewModelScope.launch { fetchCountries() }
}
