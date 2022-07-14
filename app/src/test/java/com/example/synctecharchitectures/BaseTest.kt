package com.example.synctecharchitectures

import com.example.synctecharchitectures.domain.analytics.AnalyticsTracker
import com.example.synctecharchitectures.model.CountriesFromWebRepository
import com.example.synctecharchitectures.model.dto.Country
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.impl.annotations.MockK
import org.junit.After
import org.junit.Before

open class BaseTest {
    @MockK
    lateinit var countryRepository: CountriesFromWebRepository

    @MockK
    lateinit var analyticsTracker: AnalyticsTracker

    val argentina = Country(ARGENTINA)
    val uruguay = Country(URUGUAY)
    val peru = Country(PERU)
    val brasil = Country(BRASIL)
    val chile = Country(CHILE)

    val countryList = listOf(argentina, uruguay, peru, brasil, chile)

    @Before

    /** You should call this super before anything else*/
    open fun setup() {
        MockKAnnotations.init(this, relaxed = true)
    }

    @After
    /** You should call this super before anything else*/
    open fun onClear() {
        clearAllMocks()
    }

    protected companion object {
        const val ARGENTINA = "Argentina"
        const val URUGUAY = "Uruguay"
        const val CHILE = "Chile"
        const val PERU = "Peru"
        const val BRASIL = "Brasil"
    }
}