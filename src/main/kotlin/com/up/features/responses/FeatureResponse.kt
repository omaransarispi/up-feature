package com.up.features.responses

import java.sql.Timestamp
import java.time.LocalDateTime

data class FeatureResponse(
    val id: String,
    val timestamp: Timestamp,
    val beginViewingDate: LocalDateTime,
    val endViewingDate: LocalDateTime,
    val missionName: String
)