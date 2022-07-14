package com.example.synctecharchitectures.mvi.domain

import com.example.synctecharchitectures.model.dto.Country
import com.example.synctecharchitectures.mvi.ui.CountryScreenState
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class CountryStateReducerTest {
    private val defaultViewState = CountryScreenState()
    private val reducer = CountryStateReducer()

    @Test
    fun processFetchingCountries() {
        val newState = reducer.reduce(defaultViewState, CountryScreenAction.FetchingCountries)
        assertThat(newState is CountryScreenState).isTrue()
        assertThat((newState as CountryScreenState).isLoading).isTrue()
    }

    @Test
    fun processCountriesLoaded() {
        val countries = listOf(Country("Argentina"))

        val newState = reducer.reduce(
            defaultViewState,
            CountryScreenAction.CountriesLoaded(countries)
        )

        assertThat((newState as CountryScreenState).isLoading).isFalse()
        assertThat(newState.countries).isEqualTo(countries)
    }
}