package com.example.synctecharchitectures.mvi.domain

import com.example.synctecharchitectures.model.Country
import com.example.synctecharchitectures.mvi.redux.Action


/**
 * This is a sealed class of all possible [Action]s that can occur on the countries screen.
 */
sealed class ReadCountryAction : Action {
    /**
     * This [Action] is triggered any time we want to begin fetching the countries.
     */
    object FetchCountries : ReadCountryAction()

    /**
     * This [Action] is triggered once we've began fetching the countries.
     */
    object FetchingCountries : ReadCountryAction()

    /**
     * This [Action] is triggered once we've completed fetching the countries.
     */
    data class CountriesLoaded(val countries: List<Country>) : ReadCountryAction()
}
