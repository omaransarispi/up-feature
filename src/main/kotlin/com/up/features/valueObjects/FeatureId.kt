package com.up.features.valueObjects

import java.util.*

data class FeatureId(val id: UUID) {
    companion object {
        fun getInstance(id: String): FeatureId {
            return FeatureId(UUID.fromString(id))
        }
    }
}