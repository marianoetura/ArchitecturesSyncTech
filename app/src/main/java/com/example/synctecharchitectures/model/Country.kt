package com.example.synctecharchitectures.model

import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("name")
    var countryName: String? = null
)
