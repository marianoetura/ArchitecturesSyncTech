package com.example.synctecharchitectures.mvi.ui

import com.example.synctecharchitectures.mvi.redux.Action
import com.example.synctecharchitectures.mvi.redux.FakeStore

class MviViewModelRobot {
    private val fakeStore = FakeStore(
        initialState = CountryScreenState(),
    )
    private lateinit var viewModel: MviViewModel

    fun buildViewModel() = apply {
        viewModel = MviViewModel(
            store = fakeStore,
        )
    }

    fun assertActionDispatched(expectedAction: Action) = apply {
        fakeStore.assertActionDispatched(expectedAction)
    }
}