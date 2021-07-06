package com.up.features.models

import java.util.*

data class FeatureWithQuicklook(val properties: FeatureWithQuickLookProperties) {
    companion object {
        fun getInstance(uid: UUID, quicklook: String): FeatureWithQuicklook {
            return FeatureWithQuicklook(FeatureWithQuickLookProperties(uid, quicklook))
        }
    }
    data class FeatureWithQuickLookProperties(val uid: UUID, val quicklook: String)
}

