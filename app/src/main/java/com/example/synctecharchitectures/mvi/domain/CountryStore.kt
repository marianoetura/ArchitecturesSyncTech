package com.example.synctecharchitectures.mvi.domain

import com.example.synctecharchitectures.model.CountriesFromWebRepository
import com.example.synctecharchitectures.mvi.domain.analytics.AnalyticsMiddleware
import com.example.synctecharchitectures.mvi.domain.analytics.CountryLoadedTracker
import com.example.synctecharchitectures.mvi.redux.Action
import com.example.synctecharchitectures.mvi.redux.Middleware
import com.example.synctecharchitectures.mvi.redux.Store
import com.example.synctecharchitectures.mvi.ui.CountryScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


/**
 * This is a custom implementation of a [Store] specific to the read preferences screen.
 */
class CountryStore(
    initialState: CountryScreenState = CountryScreenState(),
    private val reducer: CountryStateReducer = CountryStateReducer(),
    private val middlewares: List<Middleware> = listOf(
        GetDataMiddleware(CountriesFromWebRepository()),
        AnalyticsMiddleware(CountryLoadedTracker()),
    )
): Store {

    private val _state = MutableStateFlow(initialState)
    private val currentState: CountryScreenState
        get() = state.value

    override val state: StateFlow<CountryScreenState> = _state

    override suspend fun dispatch(action: Action) {
        middlewares.forEach { middleware ->
            middleware.process(action, currentState, this)
        }

        val newState = reducer.reduce(currentState, action)
        _state.value = newState as CountryScreenState
    }
}
