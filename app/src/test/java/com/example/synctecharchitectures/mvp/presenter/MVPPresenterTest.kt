package com.example.synctecharchitectures.mvp.presenter

import com.example.synctecharchitectures.BaseTest
import com.example.synctecharchitectures.mvp.view.MVPView
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
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
        mvpPresenter = MVPPresenter(mvpView)
    }

    @Test
    fun whenInitPresenterItShouldFetchCountries() = runTest {
        coEvery { countriesService.getCountries() } returns Response.success(countryList)
        mvpPresenter.initPresenter()
        coVerify { countriesService.getCountries() }
    }
}