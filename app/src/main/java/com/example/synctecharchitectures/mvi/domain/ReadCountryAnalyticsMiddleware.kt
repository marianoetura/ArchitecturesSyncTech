package com.example.synctecharchitectures.mvi.domain

import com.example.synctecharchitectures.mvi.domain.analytics.AnalyticsTracker
import com.example.synctecharchitectures.mvi.domain.analytics.CountryLoadedEvent
import com.example.synctecharchitectures.mvi.redux.Action
import com.example.synctecharchitectures.mvi.redux.Middleware
import com.example.synctecharchitectures.mvi.redux.State
import com.example.synctecharchitectures.mvi.ui.CountryScreenState

/**
 * This is a custom [Middleware] that processes any [ReadPreferencesAction]s and tracks necessary
 * analytics to the supplied [analyticsTracker].
 */
class ReadCountryAnalyticsMiddleware(
    private val analyticsTracker: AnalyticsTracker,
) : Middleware {

    override suspend fun process(action: Action, currentState: State, store: CountryStore) {
        val countryScreenState = currentState as CountryScreenState
        val countryAction = action as ReadCountryAction
        return process(countryAction, countryScreenState, store)
    }

    private suspend fun process(
        action: ReadCountryAction,
        currentState: CountryScreenState,
        store: CountryStore
    ) {
        when (action) {
            is ReadCountryAction.CountriesLoaded -> {
                analyticsTracker.trackEvent(CountryLoadedEvent())
            }
        }
    }


}

