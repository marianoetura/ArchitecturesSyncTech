package com.example.synctecharchitectures.mvi.redux

import kotlinx.coroutines.flow.StateFlow

/**
 * A [Store] is used to manage [State] and dispatch [Action]s that can update the state.
 */
interface Store {

    val state: StateFlow<State>

    suspend fun dispatch(action: Action)
}
