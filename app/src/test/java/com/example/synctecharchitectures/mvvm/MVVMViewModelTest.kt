package com.example.synctecharchitectures.mvvm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.synctecharchitectures.BaseTest
import com.example.synctecharchitectures.domain.analytics.AnalyticsTracker
import com.example.synctecharchitectures.model.dto.Country
import com.example.synctecharchitectures.mvi.domain.analytics.CountryLoadedEvent
import io.mockk.Called
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class MVVMViewModelTest : BaseTest() {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var mvvmViewModel: MVVMViewModel

    @MockK
    private lateinit var countriesObserver: Observer<List<Country>>

    @MockK
    private lateinit var loadingObserver: Observer<Boolean>

    @MockK
    private lateinit var errorObserver: Observer<Boolean>

    private val coroutineDispatcher = UnconfinedTestDispatcher()

    @Before
    override fun setup() {
        super.setup()
        Dispatchers.setMain(coroutineDispatcher)
        mvvmViewModel = MVVMViewModel(countryRepository, analyticsTracker)
        mvvmViewModel.countries.observeForever(countriesObserver)
        mvvmViewModel.error.observeForever(errorObserver)
        mvvmViewModel.loading.observeForever(loadingObserver)
    }

    @After
    override fun onClear() {
        super.onClear()
        Dispatchers.resetMain()
    }

    @Test
    fun whenFetchCountriesIsSuccess() = runTest {
        coEvery { countryRepository.fetchCountries() } returns Response.success(countryList)
        coEvery { analyticsTracker.trackEvent(CountryLoadedEvent) } returns Unit
        mvvmViewModel.onRefresh()
        coVerify { loadingObserver.onChanged(true) }
        coVerify { countryRepository.fetchCountries() }
        coVerify { countriesObserver.onChanged(countryList) }
        coVerify { loadingObserver.onChanged(false) }
        coVerify { errorObserver.onChanged(false) }
        coVerify { analyticsTracker.trackEvent(CountryLoadedEvent) }
    }

    @Test
    fun whenFetchCountriesIsFailed() = runTest {
        coEvery { countryRepository.fetchCountries() } throws Exception()
        mvvmViewModel.onRefresh()
        coVerify { countryRepository.fetchCountries() }
        coVerify { loadingObserver.onChanged(true) }
        coVerify { loadingObserver.onChanged(false) }
        coVerify { errorObserver.onChanged(true) }
        coVerify { countriesObserver wasNot Called }
        coVerify { analyticsTracker wasNot Called }
    }
}