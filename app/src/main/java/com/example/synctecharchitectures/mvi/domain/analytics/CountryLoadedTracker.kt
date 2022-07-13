package com.example.synctecharchitectures.mvi.domain.analytics

import android.util.Log

class CountryLoadedTracker :AnalyticsTracker {
    override fun trackEvent(event: AnalyticsEvent) {
        Log.d("DemoAnalyticsTracker", "Tracking event: $event")
    }
}