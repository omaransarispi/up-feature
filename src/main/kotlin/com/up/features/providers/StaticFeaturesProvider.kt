package com.up.features.providers

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.up.features.models.Feature
import com.up.features.models.FeatureWithQuicklook
import com.up.features.valueObjects.FeatureId
import org.springframework.stereotype.Component
import java.lang.reflect.Type

@Component
class StaticFeaturesProvider : FeaturesProvider {
    val resource = javaClass.getResource("/static/jsondump")

    override fun getFeatures(): List<Feature> {
        val listType: Type = object : TypeToken<ArrayList<Collection<Feature>?>?>() {}.getType()

        val collection: List<Collection<Feature>> = Gson().fromJson(resource.readText(), listType)
        return collection.flatMap { it.features }
    }

    override fun getQuicklook(id: FeatureId): FeatureWithQuicklook? {
        val listType: Type = object : TypeToken<ArrayList<Collection<FeatureWithQuicklook>?>?>() {}.getType()
        val collection: List<Collection<FeatureWithQuicklook>> = Gson().fromJson(resource.readText(), listType)
        return collection.flatMap {
            it.features
        }.firstOrNull {
            it.properties.uid == id.id
        }
    }
}

private data class Collection<T>(val features: List<T>)
