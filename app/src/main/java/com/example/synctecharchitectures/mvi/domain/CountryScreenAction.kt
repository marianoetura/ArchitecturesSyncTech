package com.example.synctecharchitectures.mvi.domain

import com.example.synctecharchitectures.model.dto.Country
import com.example.synctecharchitectures.mvi.redux.Action


/**
 * This is a sealed class of all possible [Action]s that can occur on the countries screen.
 */
sealed class CountryScreenAction : Action {
    /**
     * This [Action] is triggered any time we want to begin fetching the countries.
     */
    object FetchCountries : CountryScreenAction()

    /**
     * This [Action] is triggered once we've began fetching the countries.
     */
    object FetchingCountries : CountryScreenAction()

    /**
     * This [Action] is triggered once we've completed fetching the countries.
     */
    data class CountriesLoaded(val countries: List<Country>) : CountryScreenAction()

    /**
     * This [Action] is triggered once we've get an error fetching the countries.
     */
    object FetchCountriesError : CountryScreenAction()
}
