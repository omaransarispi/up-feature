package com.up.features

import java.sql.Timestamp
import java.util.*

interface FeaturesProvider {
    fun getFeatures(): List<Features>
}

data class Features(
    val id: UUID,
    val missionName: String,
    val timestamp: Timestamp,
    val acquisitionBeginViewingDate: Timestamp,
    val acquisitionEndViewingDate: Timestamp
)