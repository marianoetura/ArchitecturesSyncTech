package com.example.synctecharchitectures.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.synctecharchitectures.mvi.domain.CountryStore
import com.example.synctecharchitectures.mvi.domain.ReadCountryAction
import com.example.synctecharchitectures.mvi.ui.CountryScreenState
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CountryViewModel(
    private val store: CountryStore = CountryStore(),
) : ViewModel() {

    val viewState: StateFlow<CountryScreenState> = store.state

    init {
        fetchPreferences()
    }

    private fun fetchPreferences() {
        viewModelScope.launch {
            store.dispatch(ReadCountryAction.FetchCountries)
        }
    }
}
