package com.example.synctecharchitectures.mvi.domain

import android.util.Log
import com.example.synctecharchitectures.model.coroutines.CountriesService
import com.example.synctecharchitectures.mvi.domain.analytics.AnalyticsTracker
import com.example.synctecharchitectures.mvi.domain.analytics.CountryLoadedTracker
import com.example.synctecharchitectures.mvi.redux.Action
import com.example.synctecharchitectures.mvi.redux.BaseStore
import com.example.synctecharchitectures.mvi.redux.Middleware
import com.example.synctecharchitectures.mvi.redux.Reducer
import com.example.synctecharchitectures.mvi.redux.State
import com.example.synctecharchitectures.mvi.ui.CountryScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


/**
 * This is a custom implementation of a [BaseStore] specific to the read preferences screen.
 */
class CountryStore(
    countriesRepository: CountriesService = CountriesService(),
    analyticsTracker: AnalyticsTracker = CountryLoadedTracker(),
    private val reducer: Reducer = ReadCountriesReducer(),
    private val initialState: State = CountryScreenState(mutableListOf(), false),
    private val middlewares: List<Middleware> = listOf(
        ReadCountriesDataMiddleware(countriesRepository),
        ReadCountryAnalyticsMiddleware(analyticsTracker),
    )
) {

    private val _state = MutableStateFlow(initialState as CountryScreenState)
    val state: StateFlow<CountryScreenState> = _state

    private val currentState: State
        get() = state.value

    suspend fun dispatch(action: Action) {
        middlewares.forEach { middleware ->
            middleware.process(action, currentState, this)
        }

        val newState = reducer.reduce(currentState, action)
        _state.value = newState as CountryScreenState
    }
}
