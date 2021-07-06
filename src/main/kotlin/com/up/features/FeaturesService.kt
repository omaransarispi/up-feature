package com.up.features

import com.up.features.responses.FeatureResponse

class FeaturesService(val provider: FeaturesProvider) {
    fun getFeatures(): List<FeatureResponse> {
        return provider.getFeatures().map {
            FeatureResponse(
                it.id.toString(),
                it.missionName,
                it.timestamp,
                it.acquisitionBeginViewingDate,
                it.acquisitionEndViewingDate
            )
        }
    }
}