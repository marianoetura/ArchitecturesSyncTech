package com.example.synctecharchitectures.mvi.ui

import com.example.synctecharchitectures.model.Country
import com.example.synctecharchitectures.mvi.redux.State

data class CountryScreenState(
    var countries: List<Country>? = mutableListOf(),
    var isLoading: Boolean = false,
    var isFailed: Boolean = false,
) : State
