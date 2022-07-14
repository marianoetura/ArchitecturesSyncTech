package com.example.synctecharchitectures.mvi.domain

import com.example.synctecharchitectures.BaseTest
import com.example.synctecharchitectures.mvi.ui.CountryScreenState
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.junit.Test
import retrofit2.Response


class GetDataMiddlewareTest : BaseTest() {

    private val defaultViewState = CountryScreenState()

    @MockK
    private lateinit var fakeStore: CountryStore

    @Test
    fun processFetchPreferencesEvent() = runTest {
        coEvery { countryRepository.fetchCountries() } returns Response.success(countryList)
        val middleware = GetDataMiddleware(countryRepository)


        middleware.process(
            CountryScreenAction.FetchCountries,
            defaultViewState,
            fakeStore,
        )

        coVerify { fakeStore.dispatch(CountryScreenAction.FetchingCountries) }
        coVerify { fakeStore.dispatch(CountryScreenAction.CountriesLoaded(countryList)) }

    }
}
