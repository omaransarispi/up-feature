package com.up.features

import com.up.features.providers.FeaturesProvider
import com.up.features.responses.FeatureResponse
import org.springframework.stereotype.Service

@Service
class FeaturesService(val provider: FeaturesProvider) {
    fun getFeatures(): List<FeatureResponse> {
        return provider.getFeatures().map {
            FeatureResponse(
                it.id.toString(),
                it.missionName,
                it.timestamp.time,
                it.acquisitionBeginViewingDate.time,
                it.acquisitionEndViewingDate.time
            )
        }
    }
}