package com.example.synctecharchitectures.mvi.domain.analytics

import android.util.Log
import com.example.synctecharchitectures.domain.analytics.AnalyticsEvent
import com.example.synctecharchitectures.domain.analytics.AnalyticsTracker

class CountryLoadedTracker : AnalyticsTracker {
    override fun trackEvent(event: AnalyticsEvent) {
        Log.d("MviCountryLoadedTracker", "Tracking event: $event")
    }
}