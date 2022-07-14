package com.example.synctecharchitectures.model

import com.example.synctecharchitectures.model.dto.Country
import retrofit2.Response

interface CountryRepository {

    suspend fun fetchCountries(): Response<List<Country>>
}
