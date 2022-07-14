package com.example.synctecharchitectures.mvi.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.synctecharchitectures.mvi.domain.CountryScreenAction
import com.example.synctecharchitectures.mvi.domain.CountryStore
import com.example.synctecharchitectures.mvi.redux.Store
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MviViewModel(
    private val store: Store = CountryStore(),
) : ViewModel() {

    val viewState: StateFlow<CountryScreenState> = (store as CountryStore).state

    init {
        onRefresh()
    }

    private fun fetchCountries() = viewModelScope.launch {
        store.dispatch(CountryScreenAction.FetchCountries)
    }

    fun onRefresh() = viewModelScope.launch { fetchCountries() }
}
