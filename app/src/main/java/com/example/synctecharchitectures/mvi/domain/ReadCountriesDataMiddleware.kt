package com.example.synctecharchitectures.mvi.domain

import com.example.synctecharchitectures.model.coroutines.CountriesService
import com.example.synctecharchitectures.mvi.redux.Action
import com.example.synctecharchitectures.mvi.redux.Middleware
import com.example.synctecharchitectures.mvi.redux.State
import com.example.synctecharchitectures.mvi.ui.CountryScreenState

/**
 * This is a custom [Middleware] that processes any [ReadPreferencesAction]s and triggers a
 * corresponding data request to our [preferencesRepository] if necessary.
 */
class ReadCountriesDataMiddleware(
    private val countriesService: CountriesService,
) : Middleware {
    suspend fun process(
        action: ReadCountryAction,
        currentState: CountryScreenState,
        store: CountryStore
    ) {
        if (action is ReadCountryAction.FetchCountries) fetchPreferences(store)
    }

    /**
     * Fetches the user's preferences, and emits relevant actions to the [store] to update the state.
     */
    private suspend fun fetchPreferences(store: CountryStore) {
        store.dispatch(ReadCountryAction.FetchingCountries)

        // TODO desde aca revisar errores
        val countries = countriesService.getCountries().body()

        countries?.let {
            store.dispatch(ReadCountryAction.CountriesLoaded(countries))
        } ?: run {
            //TODO ERROR STATE
        }

    }

    override suspend fun process(action: Action, currentState: State, store: CountryStore) {
        val countryScreenState = currentState as CountryScreenState
        val countryAction = action as ReadCountryAction
        process(countryAction, countryScreenState, store)
    }
}
