package com.up.features.providers

import com.google.gson.Gson
import com.up.features.models.Feature

class StaticFeaturesProvider : FeaturesProvider {
    override fun getFeatures(): List<Feature> {
        val resource = javaClass.getResource("/static/jsondump").readText()
        val features = Gson().fromJson(resource, ArrayList<Feature>().javaClass)
        return features
    }
}