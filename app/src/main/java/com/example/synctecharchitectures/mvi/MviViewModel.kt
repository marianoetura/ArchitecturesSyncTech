package com.example.synctecharchitectures.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.synctecharchitectures.mvi.domain.CountryStore
import com.example.synctecharchitectures.mvi.domain.CountryScreenAction
import com.example.synctecharchitectures.mvi.ui.CountryScreenState
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MviViewModel(
    private val store: CountryStore = CountryStore(),
) : ViewModel() {

    val viewState: StateFlow<CountryScreenState> = store.state

    init {
        onRefresh()
    }

    private fun fetchCountries() = viewModelScope.launch {
        store.dispatch(CountryScreenAction.FetchCountries)
    }

    fun onRefresh() = viewModelScope.launch { fetchCountries() }
}
