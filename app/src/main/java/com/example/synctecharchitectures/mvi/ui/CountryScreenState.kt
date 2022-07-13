package com.example.synctecharchitectures.mvi.ui

import com.example.synctecharchitectures.model.Country
import com.example.synctecharchitectures.mvi.redux.State

data class CountryScreenState(
    val countries: List<Country>?,
    val showLoading: Boolean,
) : State
