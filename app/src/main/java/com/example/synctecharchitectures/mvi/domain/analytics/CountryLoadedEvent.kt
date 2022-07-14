package com.example.synctecharchitectures.mvi.domain.analytics

import com.example.synctecharchitectures.domain.analytics.AnalyticsEvent

class CountryLoadedEvent : AnalyticsEvent {
    override val eventName: String
        get() = "Cargado"
    override val properties: Map<String, Any>
        get() = mutableMapOf("Key" to "Value")
}