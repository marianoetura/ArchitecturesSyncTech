package com.example.synctecharchitectures.model.dto

import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("region")
    var countryName: String
)
