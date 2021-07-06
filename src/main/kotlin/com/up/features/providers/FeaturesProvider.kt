package com.up.features.providers

import com.up.features.models.Feature

interface FeaturesProvider {
    fun getFeatures(): List<Feature>
}