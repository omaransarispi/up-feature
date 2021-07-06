package com.up.features.responses

data class FeatureResponse(
    val id: String,
    val missionName: String,
    val timestamp: Long,
    val beginViewingDate: Long,
    val endViewingDate: Long
)