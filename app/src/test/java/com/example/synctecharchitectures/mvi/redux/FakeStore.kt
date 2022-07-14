package com.example.synctecharchitectures.mvi.redux

import com.example.synctecharchitectures.mvi.ui.CountryScreenState
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * This is a test implementation of a [Store] that keeps track of all historical [Action]s that
 * are dispatches so we can validate what was dispatched from a ViewModel.
 */
class FakeStore(
    private val initialState: CountryScreenState = CountryScreenState(),
) : Store {
    private val allActions: MutableList<Action> = mutableListOf()

    override val state: StateFlow<CountryScreenState>
        get() = MutableStateFlow(initialState)

    override suspend fun dispatch(action: Action) {
        allActions.add(action)
    }

    fun assertActionDispatched(action: Action) {
        assertThat(allActions).contains(action)
    }
}