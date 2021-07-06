package com.up.features

import com.up.features.models.Feature

interface FeaturesProvider {
    fun getFeatures(): List<Feature>
}