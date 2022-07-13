package com.example.synctecharchitectures.mvi.redux

import com.example.synctecharchitectures.mvi.domain.CountryStore

/**
 * A [Middleware] is any class that deals with side effects of actions. This can be logging,
 * triggering network calls, and other examples.
 */
interface Middleware {
    /**
     * This will process the given [action] and [currentState] and determine if we need to
     * perform any side effects, or trigger a new action.
     *
     * @param[store] This is a reference to the [BaseStore] that dispatched this action. We should only
     * call this with a _new_ action, and not trigger the same action again or risk ending up in a
     * loop.
     */
    suspend fun process(
        action: Action,
        currentState: State,
        store: CountryStore,
    )
}
