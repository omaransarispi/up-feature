package com.up.features.providers

import com.google.gson.Gson
import com.up.features.models.Feature
import com.up.features.models.FeaturesCollection
import java.util.*

class StaticFeaturesProvider : FeaturesProvider {
    override fun getFeatures(): List<Feature> {
        val resource = javaClass.getResource("/static/jsondump").readText()
        val features: List<FeaturesCollection> =
            Gson().fromJson(resource, Array<FeaturesCollection>::class.java).asList()
        return features.flatMap { it.features }
    }
}