package com.up.features.valueObjects

import java.util.*

data class FeatureId(val id: UUID) {
    companion object {
        fun getInstance(id: String): FeatureId? {
            try {
                return FeatureId(UUID.fromString(id))
            } catch (ex: IllegalArgumentException) {
                return null
            }
        }
    }
}