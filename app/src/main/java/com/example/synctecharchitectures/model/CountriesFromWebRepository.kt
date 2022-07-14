package com.example.synctecharchitectures.model

import com.example.synctecharchitectures.model.dto.Country
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CountriesFromWebRepository : CountryRepository {
    private val countriesService: CountriesService
    override suspend fun fetchCountries(): Response<List<Country>> = countriesService.getCountries()

    companion object {
        const val BASE_URL = "https://restcountries.com/v3.1/"
    }

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        countriesService = retrofit.create(CountriesService::class.java)
    }
}
