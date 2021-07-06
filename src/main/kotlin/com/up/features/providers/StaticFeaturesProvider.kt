package com.up.features.providers

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.up.features.models.Feature
import com.up.features.models.FeatureWithQuicklook
import com.up.features.valueObjects.FeatureId
import java.lang.reflect.Type


class StaticFeaturesProvider : FeaturesProvider {
    val resource = javaClass.getResource("/static/jsondump")

    override fun getFeatures(): List<Feature> {
        val listType: Type = object : TypeToken<ArrayList<Collection<Feature>?>?>() {}.getType()

        val collection: List<Collection<Feature>> = Gson().fromJson(resource.readText(), listType)
        return collection.flatMap { it.features }
    }

    override fun getQuicklook(featureId: FeatureId): FeatureWithQuicklook? {
        val listType: Type = object : TypeToken<ArrayList<Collection<FeatureWithQuicklook>?>?>() {}.getType()
        val collection: List<Collection<FeatureWithQuicklook>> = Gson().fromJson(resource.readText(), listType)
        return collection.flatMap {
            it.features
        }.firstOrNull {
            it.properties.uid == featureId.id
        }
    }
}

private data class Collection<T>(val features: List<T>)
