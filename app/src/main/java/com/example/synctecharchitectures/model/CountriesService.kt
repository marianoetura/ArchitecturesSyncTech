package com.example.synctecharchitectures.model

import com.example.synctecharchitectures.model.dto.Country
import retrofit2.Response
import retrofit2.http.GET

interface CountriesService {
    @GET("all")
    suspend fun getCountries(): Response<List<Country>>
}
