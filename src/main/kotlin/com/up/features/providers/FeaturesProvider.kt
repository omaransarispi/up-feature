package com.up.features.providers

import com.up.features.models.Feature
import com.up.features.models.FeatureWithQuicklook
import com.up.features.valueObjects.FeatureId

interface FeaturesProvider {
    fun getFeatures(): List<Feature>
    fun getQuicklook(id: FeatureId): FeatureWithQuicklook?
}