package com.up.features

import com.up.features.providers.FeaturesProvider
import com.up.features.responses.FeatureResponse
import org.springframework.stereotype.Service

@Service
class FeaturesService(val provider: FeaturesProvider) {
    fun getFeatures(): List<FeatureResponse> {
        return provider.getFeatures().map {
            FeatureResponse(
                it.properties.uid.toString(),
                it.acquisition.missionName,
                it.properties.timestamp,
                it.acquisition.beginViewingDate,
                it.acquisition.endViewingDate
            )
        }
    }
}