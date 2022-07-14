package com.example.synctecharchitectures.mvi.data

import com.example.synctecharchitectures.model.dto.Country
import com.example.synctecharchitectures.model.CountryRepository
import retrofit2.Response

class FakeCountryRepository: CountryRepository {
    override suspend fun fetchCountries(): Response<List<Country>> {
        TODO("Not yet implemented")
    }
}