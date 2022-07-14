package com.example.synctecharchitectures.mvi.domain.analytics

import com.example.synctecharchitectures.domain.analytics.AnalyticsTracker
import com.example.synctecharchitectures.mvi.domain.CountryScreenAction
import com.example.synctecharchitectures.mvi.domain.CountryScreenAction.CountriesLoaded
import com.example.synctecharchitectures.mvi.domain.CountryStore
import com.example.synctecharchitectures.mvi.redux.Action
import com.example.synctecharchitectures.mvi.redux.Middleware
import com.example.synctecharchitectures.mvi.redux.State

/**
 * This is a custom [Middleware] that processes any [CountryScreenAction]s and tracks necessary
 * analytics to the supplied [analyticsTracker].
 */
class AnalyticsMiddleware(
    private val analyticsTracker: AnalyticsTracker,
) : Middleware {

    override suspend fun process(action: Action, currentState: State, store: CountryStore) {
        val countryScreenAction = action as CountryScreenAction
        return process(countryScreenAction)
    }

    private fun process(action: CountryScreenAction) {
        when (action) {
            is CountriesLoaded -> {
                analyticsTracker.trackEvent(CountryLoadedEvent())
            }
        }
    }
}
