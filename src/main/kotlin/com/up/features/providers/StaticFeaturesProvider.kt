package com.up.features.providers

import com.up.features.models.Feature
import java.sql.Timestamp
import java.util.*

class StaticFeaturesProvider : FeaturesProvider {
    override fun getFeatures(): List<Feature> {
        val timestamp = Timestamp(System.currentTimeMillis())

        return listOf(
            Feature(UUID.randomUUID(), "name", timestamp, timestamp, timestamp),
            Feature(UUID.randomUUID(), "name", timestamp, timestamp, timestamp)
        )
    }
}