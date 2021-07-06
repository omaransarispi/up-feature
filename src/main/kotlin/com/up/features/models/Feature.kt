package com.up.features.models

import java.sql.Timestamp
import java.util.*

data class FeaturesCollection(val features: List<Feature>)
data class Feature(val properties: Properties) {
    companion object {
        fun getInstance(
            uid: UUID,
            missionName: String,
            timestamp: Timestamp,
            beginViewingDate: Timestamp,
            endViewingDate: Timestamp
        ): Feature {
            return Feature(
                Properties(
                    uid,
                    timestamp.time,
                    Acquisition(missionName, beginViewingDate.time, endViewingDate.time)
                )
            )
        }
    }
}

data class Properties(val uid: UUID, val timestamp: Long, val acquisition: Acquisition)
data class Acquisition(val missionName: String, val beginViewingDate: Long, val endViewingDate: Long)