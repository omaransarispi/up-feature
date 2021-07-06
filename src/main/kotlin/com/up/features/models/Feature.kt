package com.up.features.models

import java.sql.Timestamp
import java.util.*

data class Feature(val properties: FeatureProperties) {
    companion object {
        fun getInstance(
            uid: UUID,
            missionName: String,
            timestamp: Timestamp,
            beginViewingDate: Timestamp,
            endViewingDate: Timestamp
        ): Feature {
            return Feature(
                FeatureProperties(
                    uid,
                    timestamp.time,
                    Acquisition(missionName, beginViewingDate.time, endViewingDate.time)
                )
            )
        }
    }
    data class FeatureProperties(val uid: UUID, val timestamp: Long, val acquisition: Acquisition)
    data class Acquisition(val missionName: String, val beginViewingDate: Long, val endViewingDate: Long)
}

