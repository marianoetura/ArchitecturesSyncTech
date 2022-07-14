package com.example.synctecharchitectures.mvi.domain

import com.example.synctecharchitectures.mvi.domain.CountryScreenAction.CountriesLoaded
import com.example.synctecharchitectures.mvi.domain.CountryScreenAction.FetchingCountries
import com.example.synctecharchitectures.mvi.redux.Action
import com.example.synctecharchitectures.mvi.redux.Reducer
import com.example.synctecharchitectures.mvi.redux.State
import com.example.synctecharchitectures.mvi.ui.CountryScreenState

/**
 * This is a concrete implementation of a [Reducer] that processes [CountryScreenAction]s and
 * maps those actions to a new [CountryScreenState].
 */
class CountryStateReducer : Reducer {

    override fun reduce(currentState: State, action: Action): State {
        val countryScreenState = currentState as CountryScreenState
        val countryScreenAction = action as CountryScreenAction

        return reduce(countryScreenState, countryScreenAction)
    }

    private fun reduce(
        currentState: CountryScreenState,
        countryScreenAction: CountryScreenAction
    ): CountryScreenState {
        return when (countryScreenAction) {
            is FetchingCountries -> currentState.copy(
                isLoading = true,
                isFailed = false
            )
            is CountriesLoaded -> currentState.copy(
                isLoading = false,
                isFailed = false,
                countries = countryScreenAction.countries
            )
            is CountryScreenAction.FetchCountriesError -> currentState.copy(
                isLoading = false,
                isFailed = false,
            )
            else -> currentState
        }
    }

}
