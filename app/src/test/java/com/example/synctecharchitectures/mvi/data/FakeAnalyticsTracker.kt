package com.example.synctecharchitectures.mvi.data

import com.example.synctecharchitectures.domain.analytics.AnalyticsEvent
import com.example.synctecharchitectures.domain.analytics.AnalyticsTracker
import com.google.common.truth.Truth

class FakeAnalyticsTracker : AnalyticsTracker {
    private val allEvents: MutableList<AnalyticsEvent> = mutableListOf()

    override fun trackEvent(event: AnalyticsEvent) {
        allEvents.add(event)
    }

    fun assertEventTracked(expectedEvent: AnalyticsEvent) {
        Truth.assertThat(allEvents).contains(expectedEvent)
    }
}
