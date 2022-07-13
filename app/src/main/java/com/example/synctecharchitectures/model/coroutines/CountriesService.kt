package com.example.synctecharchitectures.model.coroutines

import com.example.synctecharchitectures.model.Country
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CountriesService {
    private val api: CountriesApi
    suspend fun getCountries(): Response<List<Country>> = api.getCountries()

    companion object {
        const val BASE_URL = "https://restcountries.com/v3.1/"
    }

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api = retrofit.create(CountriesApi::class.java)
    }
}
