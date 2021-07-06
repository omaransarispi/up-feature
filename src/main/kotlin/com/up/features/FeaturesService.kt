package com.up.features

import com.up.features.providers.FeaturesProvider
import com.up.features.responses.FeatureResponse
import com.up.features.valueObjects.FeatureId
import org.springframework.stereotype.Service

@Service
class FeaturesService(val provider: FeaturesProvider) {
    fun getFeatures(): List<FeatureResponse> {
        return provider.getFeatures().map {
            FeatureResponse(
                it.properties.uid.toString(),
                it.properties.acquisition.missionName,
                it.properties.timestamp,
                it.properties.acquisition.beginViewingDate,
                it.properties.acquisition.endViewingDate
            )
        }
    }

    fun getFeatureById(featureId: FeatureId): FeatureResponse? {
        return provider.getFeatures()
            .filter { it.properties.uid == featureId.id }
            .map {
                FeatureResponse(
                    it.properties.uid.toString(),
                    it.properties.acquisition.missionName,
                    it.properties.timestamp,
                    it.properties.acquisition.beginViewingDate,
                    it.properties.acquisition.endViewingDate
                )
            }.getOrNull(0)
    }
}