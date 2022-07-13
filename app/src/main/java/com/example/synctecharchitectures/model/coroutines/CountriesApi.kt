package com.example.synctecharchitectures.model.coroutines

import com.example.synctecharchitectures.model.Country
import retrofit2.Response
import retrofit2.http.GET

interface CountriesApi {
    @GET("all")
    suspend fun getCountries(): Response<List<Country>>
}