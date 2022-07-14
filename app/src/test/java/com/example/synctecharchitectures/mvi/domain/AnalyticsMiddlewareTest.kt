package com.example.synctecharchitectures.mvi.domain

import com.example.synctecharchitectures.model.dto.Country
import com.example.synctecharchitectures.mvi.data.FakeAnalyticsTracker
import com.example.synctecharchitectures.mvi.domain.analytics.AnalyticsMiddleware
import com.example.synctecharchitectures.mvi.domain.analytics.CountryLoadedEvent
import com.example.synctecharchitectures.mvi.redux.FakeStore
import com.example.synctecharchitectures.mvi.ui.CountryScreenState
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

class AnalyticsMiddlewareTest {
    private val fakeAnalyticsTracker = FakeAnalyticsTracker()
    private val defaultViewState = CountryScreenState()
    private val fakeStore = FakeStore(defaultViewState)

    @Test
    fun trackViewedPreferencesOnceLoaded() = runBlockingTest {
        val middleware = AnalyticsMiddleware(fakeAnalyticsTracker)

        middleware.process(
            CountryScreenAction.CountriesLoaded(listOf(Country("Argentina"))),
            defaultViewState,
            fakeStore,
        )

        fakeAnalyticsTracker.assertEventTracked(CountryLoadedEvent)
    }
}
