package com.example.synctecharchitectures.mvi.domain

import com.example.synctecharchitectures.mvi.redux.Action
import com.example.synctecharchitectures.mvi.redux.Reducer
import com.example.synctecharchitectures.mvi.redux.State
import com.example.synctecharchitectures.mvi.ui.CountryScreenState

/**
 * This is a concrete implementation of a [Reducer] that processes [ReadPreferencesAction]s and
 * maps those actions to a new [DeveloperPreferencesViewState].
 */
class ReadCountriesReducer : Reducer {

    override fun reduce(currentState: State, action: Action): State {
        val countryScreenState = currentState as CountryScreenState
        val countryAction = action as ReadCountryAction

        return reduce(countryScreenState, countryAction)
    }

    private fun reduce(
        currentState: CountryScreenState,
        action: ReadCountryAction
    ): CountryScreenState {
        return when (action) {
            is ReadCountryAction.FetchCountries -> currentState.copy(showLoading = true)
            is ReadCountryAction.CountriesLoaded -> currentState.copy(showLoading = false, countries = action.countries)
            else -> currentState
        }
    }


}
