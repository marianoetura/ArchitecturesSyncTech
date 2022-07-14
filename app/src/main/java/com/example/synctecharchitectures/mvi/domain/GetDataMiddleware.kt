package com.example.synctecharchitectures.mvi.domain

import com.example.synctecharchitectures.model.CountriesFromWebRepository
import com.example.synctecharchitectures.mvi.redux.Action
import com.example.synctecharchitectures.mvi.redux.Middleware
import com.example.synctecharchitectures.mvi.redux.State
import com.example.synctecharchitectures.mvi.redux.Store

/**
 * This is a custom [Middleware] that processes any [CountryScreenAction]s and triggers a
 * corresponding data request to our [CountriesFromWebRepository] if necessary.
 */
class GetDataMiddleware(
    private val countriesFromWebRepository: CountriesFromWebRepository,
) : Middleware {

    override suspend fun process(action: Action, currentState: State, store: Store) {
        val countryScreenAction = action as CountryScreenAction
        val countryStore = store as CountryStore
        process(countryScreenAction, countryStore)
    }

    private suspend fun process(
        action: CountryScreenAction,
        store: CountryStore
    ) {
        if (action is CountryScreenAction.FetchCountries) fetchPreferences(store)
    }

    /**
     * Fetches the user's preferences, and emits relevant actions to the [store] to update the state.
     */
    private suspend fun fetchPreferences(store: CountryStore) {
        store.dispatch(CountryScreenAction.FetchingCountries)

        countriesFromWebRepository.fetchCountries().run {
            val countriesList = body()
            if (isSuccessful && countriesList != null) {
                store.dispatch(CountryScreenAction.CountriesLoaded(countriesList))
            } else {
                store.dispatch(CountryScreenAction.FetchCountriesError)
            }
        }

    }

}
