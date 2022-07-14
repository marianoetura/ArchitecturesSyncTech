package com.example.synctecharchitectures.mvp.presenter

import com.example.synctecharchitectures.BaseTest
import com.example.synctecharchitectures.domain.analytics.AnalyticsEvent
import com.example.synctecharchitectures.mvi.domain.analytics.CountryLoadedEvent
import com.example.synctecharchitectures.mvp.view.MVPView
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockkConstructor
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class MVPPresenterTest : BaseTest() {
    private lateinit var mvpPresenter: MVPPresenter

    @MockK
    private lateinit var mvpView: MVPView

    @Before
    override fun setup() {
        super.setup()
        mvpPresenter = MVPPresenter(mvpView, countryRepository, analyticsTracker = analyticsTracker)
        coEvery { mvpView.showLoading() } returns Unit
    }

    @Test
    fun whenFetchValuesIsSuccess() = runTest {
        coEvery { countryRepository.fetchCountries() } returns Response.success(countryList)
        coEvery { mvpView.setValues(countryList) } returns Unit
        coEvery { analyticsTracker.trackEvent(CountryLoadedEvent) } returns Unit
        mvpPresenter.initPresenter()
        coVerify { mvpView.showLoading() }
        coVerify { countryRepository.fetchCountries() }
        coVerify { mvpView.setValues(countryList) }
        coVerify { analyticsTracker.trackEvent(CountryLoadedEvent) }
    }

    @Test
    fun whenFetchValuesIsFailed() = runTest {
        coEvery { countryRepository.fetchCountries() } throws Exception()
        coEvery { mvpView.onError() } returns Unit
        mvpPresenter.initPresenter()
        coVerify { mvpView.showLoading() }
        coVerify { countryRepository.fetchCountries() }
        coVerify { mvpView.onError() }
    }

    @Test
    fun whenSuccessFetchValuesOnRefresh() = runTest {
        coEvery { countryRepository.fetchCountries() } returns Response.success(countryList)
        coEvery { mvpView.setValues(countryList) } returns Unit
        coEvery { analyticsTracker.trackEvent(CountryLoadedEvent) } returns Unit
        mvpPresenter.onRefresh()
        coVerify { mvpView.showLoading() }
        coVerify { countryRepository.fetchCountries() }
        coVerify { mvpView.setValues(countryList) }
        coVerify { analyticsTracker.trackEvent(CountryLoadedEvent) }
    }
}