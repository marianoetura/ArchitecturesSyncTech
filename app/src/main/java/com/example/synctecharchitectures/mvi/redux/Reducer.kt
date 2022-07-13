package com.example.synctecharchitectures.mvi.redux

interface Reducer {

    /**
     * Given a [currentState] and some [action] that the user took, produce a new [State].
     *
     * This will give us clear and predictable state management, that ensures each state is associated
     * with some specific user intent or action.
     */
    fun reduce(currentState: State, action: Action): State
}
