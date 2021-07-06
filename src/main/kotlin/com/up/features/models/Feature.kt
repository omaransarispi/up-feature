package com.up.features.models

import java.sql.Timestamp
import java.util.*

data class Feature(
    val id: UUID,
    val missionName: String,
    val timestamp: Timestamp,
    val acquisitionBeginViewingDate: Timestamp,
    val acquisitionEndViewingDate: Timestamp
)